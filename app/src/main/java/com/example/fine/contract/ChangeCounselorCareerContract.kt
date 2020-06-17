package com.example.fine.presenter

import com.example.fine.model.ChangeExperience
import com.example.fine.view.BaseView

interface ChangeCounselorCareerContract {
    interface View : BaseView {
        fun showInfo(certificate: String?, career: String?, education: String?)
    }
    interface Presenter : BasePresenter<View>{
        fun saveInfo(changeExperience: ChangeExperience)
    }
}