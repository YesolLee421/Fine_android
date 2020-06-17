package com.example.fine.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fine.R
import com.example.fine.model.ChangePassword
import com.example.fine.presenter.ChangePasswordContract
import com.example.fine.presenter.ChangePasswordPresenter
import kotlinx.android.synthetic.main.activity_change_password.*

class ChangePasswordActivity : BaseActivity() , ChangePasswordContract.View{
    override val TAG: String = "ChangePasswordActivity"
    lateinit var changePasswordPresenter: ChangePasswordPresenter


    override fun initPresenter() {
        changePasswordPresenter = ChangePasswordPresenter()
        changePasswordPresenter.mContext = this
        changePasswordPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        change_password_btn_save.setOnClickListener {
            val pre_password = change_password_et_pre_password.text.toString()
            val new_password = change_password_et_new_password.text.toString()
            val new_password_2 = change_password_et_new_password_2.text.toString()
            if(pre_password=="" || new_password=="" || new_password_2==""){
                changePasswordPresenter.showMessage("비밀번호를 전부 입력해 주십시오.")
            } else {
                changePasswordPresenter.saveInfo(ChangePassword(pre_password, new_password, new_password_2))
            }

        }
    }
}
