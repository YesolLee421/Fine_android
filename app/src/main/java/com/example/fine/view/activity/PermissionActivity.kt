package com.example.fine.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fine.R
import kotlinx.android.synthetic.main.activity_permission.*

class PermissionActivity : BaseActivity() {
    override fun initPresenter() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission)

        permission_btn.setOnClickListener {
            startActivity(Intent(this@PermissionActivity, LoginActivity::class.java))
        }


    }
}
