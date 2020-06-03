package com.example.fine.presenter

import com.example.fine.view.BaseView

interface ChatRoomContract {
    interface View : BaseView {}
    interface Presenter : BasePresenter<View>{}
}