package com.example.fine.presenter

import com.example.fine.model.ChangeBankAccount
import com.example.fine.view.BaseView

interface ChangeCounselorBankAccountContract {
    interface View : BaseView {
        fun showInfo(bank_name: String?, account_number: String?)
    }
    interface Presenter : BasePresenter<View>{
        fun saveInfo(changeBankAccount: ChangeBankAccount)
    }
}