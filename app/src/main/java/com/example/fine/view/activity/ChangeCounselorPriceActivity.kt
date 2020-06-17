package com.example.fine.view.activity

import android.os.Bundle
import com.example.fine.R
import com.example.fine.model.ChangePrice
import com.example.fine.presenter.ChangeCounselorPriceContract
import com.example.fine.presenter.ChangeCounselorPricePresenter
import kotlinx.android.synthetic.main.activity_change_counselor_price.*
import java.text.NumberFormat
import java.util.*

class ChangeCounselorPriceActivity : BaseActivity(), ChangeCounselorPriceContract.View {

    val PRICE_DEFAILT = 20000
    val DISCOUNT_DEFAULT = 0


    fun applyDiscount(price:Int, discount: Int): Int{ // 할인가격 구해주는 함수
        return (price*(100-discount)/100)
    }

    override fun showInfo(price: Int, discount_4w: Int, discount_10w: Int) {
        val nf = NumberFormat.getCurrencyInstance(Locale.KOREA)

        change_price_et_price.setText(price.toString()) // 1회기 기본값

        change_price_tv_4w.setText((price*4).toString()) // 4주 기본값
        change_price_et_discount_4w.setText(discount_4w.toString())
        change_price_tv_4w_final.setText(applyDiscount(price*4,discount_4w).toString())

        change_price_tv_10w.setText((price*10).toString()) // 10주 기본값
        change_price_et_discount_10w.setText(discount_10w.toString())
        change_price_tv_10w_final.setText(applyDiscount(price*10, discount_10w).toString())
    }

    override val TAG: String = "ChangeCounselorPriceActivity"
    lateinit var changePricePresenter: ChangeCounselorPricePresenter

    override fun initPresenter() {
        changePricePresenter = ChangeCounselorPricePresenter()
        changePricePresenter.mContext = this
        changePricePresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_counselor_price)

        var price = intent.getIntExtra("price", PRICE_DEFAILT)
        var discount_4w = intent.getIntExtra("discount_4w", DISCOUNT_DEFAULT)
        var discount_10w = intent.getIntExtra("discount_10w", DISCOUNT_DEFAULT)

        showInfo(price, discount_4w, discount_10w)

        change_price_btn_save.setOnClickListener {

            if(change_price_et_price.text.toString()==""){
                changePricePresenter.showMessage("가격 미입력: 기본값 20,000원 입력")
                price = PRICE_DEFAILT
            } else {
                price = Integer.parseInt((change_price_et_price.text.toString()))
            }
            if(change_price_et_discount_4w.text.toString()==""){
                changePricePresenter.showMessage("4주 프로그램 할인율 미입력: 기본값 0% 입력")
                discount_4w = DISCOUNT_DEFAULT
            } else {
                discount_4w = Integer.parseInt((change_price_et_discount_4w.text.toString()))
            }
            if(change_price_et_discount_10w.text.toString()==""){
                changePricePresenter.showMessage("10주 프로그램 할인율 미입력: 기본값 0% 입력")
                discount_10w = DISCOUNT_DEFAULT
            } else {
                discount_10w = Integer.parseInt((change_price_et_discount_10w.text.toString()))
            }
            changePricePresenter.saveInfo(ChangePrice(price, discount_4w, discount_10w))
        }
    }
}
