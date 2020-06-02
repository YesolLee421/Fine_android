package com.example.fine.presenter

import com.example.fine.view.BaseView


interface CaseDetailContract {
    interface View : BaseView{}
    interface Presenter : BasePresenter<View>{}
}