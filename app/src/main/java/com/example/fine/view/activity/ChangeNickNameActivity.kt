package com.example.fine.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fine.R
import com.example.fine.model.ChangeExperience
import com.example.fine.model.ChangeNickName
import com.example.fine.presenter.ChangeNickNameContract
import com.example.fine.presenter.ChangeNickNamePresenter
import kotlinx.android.synthetic.main.activity_change_nick_name.*

class ChangeNickNameActivity : BaseActivity() ,ChangeNickNameContract.View{
    override fun showInfo(nickname: String) {
        if(nickname!=""){
            change_nickname_et_nickname.setText(nickname)
        }
    }

    override val TAG: String = "ChangeNickNameActivity"
    lateinit var changeNickNamePresenter: ChangeNickNamePresenter
    lateinit var nickname: String

    override fun initPresenter() {
        changeNickNamePresenter = ChangeNickNamePresenter()
        changeNickNamePresenter.mContext = this
        changeNickNamePresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_nick_name)
        nickname = intent.getStringExtra("nickname")!!
        showInfo(nickname)

        change_nickname_btn_save.setOnClickListener {
            if(change_nickname_et_nickname.text.toString()!=""){
                nickname = change_nickname_et_nickname.text.toString()
                changeNickNamePresenter.saveInfo(ChangeNickName(nickname))
            } else {
                changeNickNamePresenter.showMessage("닉네임은 반드시 입력해야 합니다.")
            }
        }
    }
}
