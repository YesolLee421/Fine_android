package com.example.fine.presenter

import com.example.fine.model.ChangePassword
import com.example.fine.view.BaseView

interface ChangePasswordContract {
    interface View : BaseView {
        //fun showInfo(pre_password: String, new_password: String, new_password_2: String)
    }
    interface Presenter : BasePresenter<View>{
        fun saveInfo(changePassword: ChangePassword)
    }
}