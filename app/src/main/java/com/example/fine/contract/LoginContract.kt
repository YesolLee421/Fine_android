package com.example.fine.presenter

import com.example.fine.view.BaseView

interface LoginContract {
    interface View : BaseView {}
    interface Presenter : BasePresenter<View>{
        fun login(email: String, password: String)

        fun startRegisterActivity()

        fun startSearchCounselorActivity()

    }
}