package com.example.fine.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fine.R

class LoadingActivity : BaseActivity(){
    override fun initPresenter() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        startActivity(Intent(this@LoadingActivity, PermissionActivity::class.java))

    }
}
