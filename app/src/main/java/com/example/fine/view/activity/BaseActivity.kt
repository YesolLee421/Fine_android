package com.example.fine.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initPresenter()
    }

    // initPresenter : View와 상호작용할 Presenter를 주입하기 위한 함수
    abstract fun initPresenter()
}
