package com.example.fine.presenter

import android.content.Context
import com.example.fine.model.ChangeProfile
import com.example.fine.model.CounselorData
import com.example.fine.model.ServerData_mypage
import com.example.fine.network.RetrofitClient
import okhttp3.MediaType
import okhttp3.OkHttpClient
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import okhttp3.RequestBody



class ChangeCounselorProfilePresenter : ChangeCounselorProfileContract.Presenter {

    fun makePartMap(name_formal: String, description: String?, gender: Int, picture: String?){
        // create a map of data to pass along

        val description = RequestBody.create(MediaType.parse("multipart/data"), description)
        val name_formal = RequestBody.create(MediaType.parse("multipart/data"), name_formal)
        //val picture = RequestBody.create("multipart/data", null)

    }
    
    fun makeFile(picture: String?){
        executionLog(TAG, "추후 사진 파일 생성 넣기")
    }
    override fun saveInfo(
        name_formal: String,
        description: String?,
        gender: Int,
        picture: String?
    ) {
        // 사진파일 생성
         makeFile(picture)

        // 서버로 상담사 정보 요청 보내기
        val client: OkHttpClient = RetrofitClient.getClient(mContext, "addCookie")
        val apiService = RetrofitClient.serviceAPI(client)

        // requestBody 생성
        val description_rq = RequestBody.create(MediaType.parse("multipart/data"), description!!)
        val name_formal_rq = RequestBody.create(MediaType.parse("multipart/data"), name_formal)

        val changeTimePrefered_request : Call<ServerData_mypage> = apiService.changeProfile(name_formal_rq, description_rq, gender,null)
        changeTimePrefered_request.enqueue(object : Callback<ServerData_mypage> {
            override fun onFailure(call: Call<ServerData_mypage>, t: Throwable) {
                executionLog(TAG, "changeTimePrefered_request 실패")
            }
            override fun onResponse(call: Call<ServerData_mypage>, response: Response<ServerData_mypage>) {
                if(response.isSuccessful){
                    executionLog(TAG, "changeTimePrefered_request 성공")
                    val data: ServerData_mypage = response.body()!!
                    if(data.data!=null){
                        showMessage(data.message)
                        val mypage = data.data
                        val counselor: CounselorData? = mypage?.counselor
                        if(counselor!=null){
                            mView.showInfo(
                                counselor.name_formal,
                                counselor.description,
                                counselor.count,
                                counselor.isVerified,
                                counselor.gender!!,
                                counselor.picture
                            )
                        }
                    }
                }
            }
        })
    }

    override lateinit var mView: ChangeCounselorProfileContract.View
    override lateinit var mContext: Context
    override val TAG: String = "ChangeCounselorProfilePresenter"
}