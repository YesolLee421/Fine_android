package com.example.fine.presenter

import com.example.fine.model.ChangeTimePrefered
import com.example.fine.view.BaseView
import org.json.JSONArray

interface ChangeCounselorTimePreferedContract {
    interface View : BaseView {
        fun showInfo(day: JSONArray?, time: JSONArray?)
    }
    interface Presenter : BasePresenter<View>{
        fun saveInfo(changeTimePrefered: ChangeTimePrefered)
    }
}