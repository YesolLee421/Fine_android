package com.example.fine.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fine.R
import com.example.fine.model.createCase
import com.example.fine.presenter.CheckPaymentContract
import com.example.fine.presenter.CheckPaymentPresenter
import kotlinx.android.synthetic.main.activity_check_payment.*
import java.lang.StringBuilder
import java.text.NumberFormat
import java.util.*

class CheckPaymentActivity : BaseActivity() , CheckPaymentContract.View {

    fun setCaseTitle(){
        lateinit var str: String
        when (totalCount){
            1-> str = "1회기권"
            2-> str = "2주 프로그램"
            4-> str = "4주 프로그램"
            10-> str = "10주 프로그램"
        }
        check_payment_tv_title.text = str
    }

    fun getCurrency(price: Int): String{
        val nf = NumberFormat.getCurrencyInstance(Locale.KOREA)
        return nf.format(price)
    }

    override fun showInfo() {
        setCaseTitle()
        check_payment_tv_basic_price.text = getCurrency(price*totalCount)
        presenter.executionLog(TAG, "discountRate=${discountRate}")
        check_payment_tv_discount_rate.text = discountRate.toString()
        check_payment_tv_total_price.text = getCurrency(price*totalCount*(100-discountRate)/100)
    }

    override val TAG: String = "CheckPaymentActivity"

    override fun initPresenter() {
        presenter = CheckPaymentPresenter()
        presenter.mContext = this
        presenter.mView = this
    }

    var counselor_id: String? = null
    var price: Int = 0
    var totalCount: Int = 0
    var discountRate: Int = 0

    lateinit var presenter: CheckPaymentPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_payment)

        counselor_id = intent.getStringExtra("counselor_id")
        price = intent.getIntExtra("price", 20000)
        totalCount = intent.getIntExtra("totalCount", 0)
        discountRate = intent.getIntExtra("discountRate", 0)

        check_payment_btn_create_case.setOnClickListener {
            if(counselor_id!=null) {
                presenter.createCase(createCase(counselor_id!!, totalCount, price, discountRate))
            }

        }
    }

    override fun onStart() {
        super.onStart()
        showInfo()
    }




}
