package com.example.fine.presenter

import android.content.Context
import com.example.fine.model.Register
import com.example.fine.model.userData
import com.example.fine.view.BaseView

interface RegisterContract {
    interface View : BaseView {
    }
    interface Presenter : BasePresenter<View>{
        fun emailCheck(email: String)

        fun nicknameCheck(nickname: String)

        fun register(email: String, password: String, nickname: String, type: Int)

        fun startLoginActivity()

        fun startSearchCounselorActivity()
    }
}
