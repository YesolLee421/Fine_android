package com.example.fine.presenter

import com.example.fine.model.ChangeIntro
import com.example.fine.view.BaseView

interface ChangeCounselorIntroContract {
    interface View : BaseView {
        fun showInfo(intro_1: String?, intro_2: String?, intro_3: String?)
    }
    interface Presenter : BasePresenter<View>{
        fun saveInfo(changeIntro: ChangeIntro)
    }
}