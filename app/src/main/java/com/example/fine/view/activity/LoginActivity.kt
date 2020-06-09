package com.example.fine.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.fine.R
import com.example.fine.presenter.LoginContract
import com.example.fine.presenter.LoginPresenter
import com.example.fine.presenter.RegisterContract
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), LoginContract.View {
    val TAG = "LoginActivity"

    // LoginActivity와 함께 생성될 LoginPresenter를 지연 초기화
    private lateinit var loginPresenter: LoginPresenter

    override fun initPresenter() {
        loginPresenter = LoginPresenter()
        loginPresenter.mContext = this
        loginPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_btn_login.setOnClickListener {
            var email = login_et_email.text.toString()
            var password = login_et_password.text.toString()
            Log.d(TAG,"email="+email+" / password="+password)
            loginPresenter.login(email, password)
        }

        login_btn_register.setOnClickListener {
            loginPresenter.startRegisterActivity()
        }
    }
}
