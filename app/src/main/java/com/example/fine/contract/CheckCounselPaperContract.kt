package com.example.fine.presenter

import com.example.fine.view.BaseView

interface CheckCounselPaperContract {
    interface View : BaseView {}
    interface Presenter : BasePresenter<View>{}
}