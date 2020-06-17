package com.example.fine.presenter

import android.content.Context
import com.example.fine.model.ChangePrice
import com.example.fine.model.ServerData_mypage
import com.example.fine.network.RetrofitClient
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangeCounselorPricePresenter() : ChangeCounselorPriceContract.Presenter {
    
    fun checkPrice(changePrice: ChangePrice) : Boolean{
        if(changePrice.price<12000){
            showMessage("상담 가격은 반드시 12,000원 이상이어야 합니다.")
            return false

        }
        if(changePrice.discount_10w<=0 || changePrice.discount_4w<=0){
            showMessage("상담 가격 및 할인율은 반드시 0 이상이어야 합니다.")
            return false
        }
        return true
    }
    override fun saveInfo(changePrice: ChangePrice) {
        
        if(checkPrice(changePrice)){ // 가격, 할인율 값 검사
            // 서버로 상담사 정보 요청 보내기
            val client: OkHttpClient = RetrofitClient.getClient(mContext, "addCookie")
            val apiService = RetrofitClient.serviceAPI(client)
            val changePrice_request : Call<ServerData_mypage> = apiService.changePrice(changePrice)
            changePrice_request.enqueue(object : Callback<ServerData_mypage> {
                override fun onFailure(call: Call<ServerData_mypage>, t: Throwable) {
                    executionLog(TAG, "changePrice_request 실패")
                }
                override fun onResponse(call: Call<ServerData_mypage>, response: Response<ServerData_mypage>) {
                    if(response.isSuccessful){
                        executionLog(TAG, "changePrice_request 성공")
                        val data: ServerData_mypage = response.body()!!
                        if(data.data!=null){
                            executionLog(TAG, "changePrice_request 성공")
                            showMessage(data.message)
                            val mypage = data.data
                            mView.showInfo(mypage?.counselor?.price!!, mypage.counselor?.discount_4w!!, mypage.counselor?.discount_10w!!)
                        }
                    }
                }
            })
        }
        
           
    }

    override lateinit var mView: ChangeCounselorPriceContract.View
    override lateinit var mContext: Context
    override val TAG: String = "ChangeCounselorPricePresenter"
}