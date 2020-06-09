package com.example.fine.view.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.fine.R
import com.example.fine.presenter.LoginContract
import com.example.fine.presenter.RegisterContract
import com.example.fine.presenter.RegisterPresenter
import kotlinx.android.synthetic.main.activity_register.*
import kotlin.properties.Delegates

class RegisterActivity : BaseActivity(), RegisterContract.View {
    val TAG = "RegisterActivity"

    // LoginActivity와 함께 생성될 LoginPresenter를 지연 초기화
    private lateinit var registerPresenter: RegisterPresenter

    override fun initPresenter() {
        registerPresenter = RegisterPresenter()
        registerPresenter.mContext = this
        registerPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        register_btn_register.setOnClickListener {
            var email = register_et_email.text.toString()
            var password = register_et_password_2.text.toString()
            var nickname = register_et_nickname.text.toString()
            var type:Int = 0

            if(register_rbtn_2.isChecked){
                type = 2
            } else if(register_rbtn_3.isChecked){
                type = 3
            }

            Log.d(TAG,"email="+email+" / password="+password+" / nickname="+ nickname+" / type="+type)
            registerPresenter.register(email, password, nickname, type)
        }
    }
}
