package com.example.fine.presenter

import com.example.fine.view.BaseView

interface RequestCounselingContract {
    interface View : BaseView {
        fun showInfo(price: Int, discount_4w: Int, discount_10w: Int)

    }
    interface Presenter : BasePresenter<View>{
        fun loadData (counselor_id: String)
        fun startPaymentActivity(totalCount: Int, discountRate: Int)

    }
}