package com.example.fine.presenter

import com.example.fine.model.ChangePaperAll
import com.example.fine.model.Paper
import com.example.fine.view.BaseView

interface WriteCounselPaperContract {
    interface View : BaseView {
        fun showInfo(paper: Paper)
    }
    interface Presenter : BasePresenter<View>{
        fun saveInfo(paper: ChangePaperAll)
        fun loadData()
    }
}