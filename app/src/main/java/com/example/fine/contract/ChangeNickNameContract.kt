package com.example.fine.presenter

import com.example.fine.model.ChangeNickName
import com.example.fine.view.BaseView

interface ChangeNickNameContract {
    interface View : BaseView {
        fun showInfo(nickname: String)
    }
    interface Presenter : BasePresenter<View>{
        fun saveInfo(changeNickName: ChangeNickName)
    }
}