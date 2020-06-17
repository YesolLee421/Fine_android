package com.example.fine.presenter

import com.example.fine.model.ChangePrice
import com.example.fine.view.BaseView

interface ChangeCounselorPriceContract {
    interface View : BaseView {
        fun showInfo(price: Int, discount_4w: Int, discount_10w: Int)
    }
    interface Presenter : BasePresenter<View>{
        fun saveInfo(changePrice: ChangePrice)
    }
}