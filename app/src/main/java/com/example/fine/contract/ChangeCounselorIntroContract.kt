package com.example.fine.presenter

import com.example.fine.view.BaseView

interface ChangeCounselorIntroContract {
    interface View : BaseView {}
    interface Presenter : BasePresenter<View>{}
}