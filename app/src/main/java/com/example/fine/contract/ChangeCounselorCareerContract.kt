package com.example.fine.presenter

import com.example.fine.view.BaseView

interface ChangeCounselorCareerContract {
    interface View : BaseView {}
    interface Presenter : BasePresenter<View>{}
}