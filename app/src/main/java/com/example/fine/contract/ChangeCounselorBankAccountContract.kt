package com.example.fine.presenter

import com.example.fine.view.BaseView

interface ChangeCounselorBankAccountContract {
    interface View : BaseView {}
    interface Presenter : BasePresenter<View>{}
}