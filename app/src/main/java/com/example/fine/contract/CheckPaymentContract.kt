package com.example.fine.presenter

import com.example.fine.model.createCase
import com.example.fine.view.BaseView

interface CheckPaymentContract {
    interface View : BaseView {
        fun showInfo()
    }
    interface Presenter : BasePresenter<View>{
        fun createCase(createCase: createCase)

    }
}