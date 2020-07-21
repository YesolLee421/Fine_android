package com.example.fine.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fine.R
import com.example.fine.presenter.RequestCounselingContract
import com.example.fine.presenter.RequestCounselingPresenter
import kotlinx.android.synthetic.main.activity_request_counseling.*
import java.text.NumberFormat
import java.util.*

class RequestCounselingActivity : BaseActivity(), RequestCounselingContract.View {

    override val TAG: String = "RequestCounselingActivity"
    lateinit var presenter: RequestCounselingPresenter

    var discountRate_4w: Int = 0
    var discountRate_10w: Int = 0


    override fun showInfo(price: Int, discount_4w: Int, discount_10w: Int) {

        if(discount_4w>0){
            request_counseling_tv_title_4.text = "4주 프로그램 (${discount_4w}% 할인)"
            discountRate_4w = discount_4w
        }
        if(discount_10w>0){
            request_counseling_tv_title_10.text = "10주 프로그램 (${discount_10w}% 할인)"
            discountRate_10w = discount_10w
        }

        request_counseling_tv_price_1.text = getCurrency(price)
        request_counseling_tv_price_2.text = getCurrency(price*2)
        request_counseling_tv_price_4.text = getCurrency(price*4*(100-discount_4w)/100)
        request_counseling_tv_price_10.text = getCurrency(price*10*(100-discount_10w)/100)
    }

    fun getCurrency(price: Int): String{
        val nf = NumberFormat.getCurrencyInstance(Locale.KOREA)
        return nf.format(price)
    }

    override fun initPresenter() {
        presenter = RequestCounselingPresenter()
        presenter.mContext = this
        presenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_counseling)


        // 상담 선택하면 이동
        request_counseling_cv_1.setOnClickListener {
            presenter.startPaymentActivity(1,0)
        }
        request_counseling_cv_2.setOnClickListener {
            presenter.startPaymentActivity(2,0)
        }
        request_counseling_cv_4.setOnClickListener {
            presenter.startPaymentActivity(4, discountRate_4w)
        }
        request_counseling_cv_10.setOnClickListener {
            presenter.startPaymentActivity(10, discountRate_10w)
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.loadData(intent.getStringExtra("counselor_id")!!)
    }
}
