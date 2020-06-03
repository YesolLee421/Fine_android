package com.example.fine.presenter

import com.example.fine.view.BaseView

interface CounselListContract {
    interface View : BaseView {}
    interface Presenter : BasePresenter<View>{}
}