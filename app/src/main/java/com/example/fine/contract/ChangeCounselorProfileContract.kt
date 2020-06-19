package com.example.fine.presenter

import com.example.fine.model.ChangeProfile
import com.example.fine.model.CounselorData
import com.example.fine.view.BaseView

interface ChangeCounselorProfileContract {
    interface View : BaseView {
        fun showInfo(
            name_formal: String?,
            description: String?,
            count: Int,
            isVerified: Boolean,
            gender: Int,
            picture: String?
        )
    }
    interface Presenter : BasePresenter<View>{
        fun saveInfo(
            name_formal: String,
            description: String?,
            gender: Int,
            picture: String?
        )
    }
}