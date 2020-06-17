package com.example.fine.view.activity

import android.os.Bundle
import com.example.fine.R
import com.example.fine.model.ChangeIntro
import com.example.fine.presenter.ChangeCounselorIntroContract
import com.example.fine.presenter.ChangeCounselorIntroPresenter
import kotlinx.android.synthetic.main.activity_change_counselor_intro.*

class ChangeCounselorIntroActivity : BaseActivity() , ChangeCounselorIntroContract.View{
    override val TAG: String = "ChangeCounselorIntroActivity"
    lateinit var changeCounselorPresenter: ChangeCounselorIntroPresenter
    lateinit var changeIntro: ChangeIntro

    override fun showInfo(intro_1: String?, intro_2: String?, intro_3: String?) {
        change_counselor_intro_et_intro_1.setText(intro_1)
        change_counselor_intro_et_intro_2.setText(intro_2)
        change_counselor_intro_et_intro_3.setText(intro_3)
    }

    override fun initPresenter() {
        changeCounselorPresenter = ChangeCounselorIntroPresenter()
        changeCounselorPresenter.mContext = this
        changeCounselorPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_counselor_intro)
        var intro_1 = intent.getStringExtra("intro_1")
        var intro_2 = intent.getStringExtra("intro_2")
        var intro_3 = intent.getStringExtra("intro_3")

        showInfo(intro_1, intro_2, intro_3)

        change_counselor_intro_btn_save.setOnClickListener {
            intro_1 = change_counselor_intro_et_intro_1.text.toString()
            intro_2 = change_counselor_intro_et_intro_2.text.toString()
            intro_3 = change_counselor_intro_et_intro_3.text.toString()
            changeIntro = ChangeIntro(intro_1, intro_2, intro_3)
            changeCounselorPresenter.saveInfo(changeIntro)
        }




    }
}
