package com.example.fine.view.activity

import android.os.Bundle
import com.example.fine.R
import com.example.fine.model.ChangeExperience
import com.example.fine.presenter.ChangeCounselorCareerContract
import com.example.fine.presenter.ChangeCounselorCareerPresenter
import kotlinx.android.synthetic.main.activity_change_counselor_career.*

class ChangeCounselorCareerActivity : BaseActivity(), ChangeCounselorCareerContract.View {
    override fun showInfo(certificate: String?, career: String?, education: String?) {
        change_counselor_experience_et_certificate.setText(certificate)
        change_counselor_experience_et_career.setText(career)
        change_counselor_experience_et_education.setText(education)
    }

    override val TAG: String = "ChangeCounselorCareerActivity"
    lateinit var changeExperiencePresenter: ChangeCounselorCareerPresenter
    lateinit var changeExperience: ChangeExperience

    override fun initPresenter() {
        changeExperiencePresenter = ChangeCounselorCareerPresenter()
        changeExperiencePresenter.mContext = this
        changeExperiencePresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_counselor_career)
        var certificate = intent.getStringExtra("certificate")
        var career = intent.getStringExtra("career")
        var education = intent.getStringExtra("education")

        showInfo(certificate, career, education)

        change_counselor_experience_btn_save.setOnClickListener {
            certificate = change_counselor_experience_et_certificate.text.toString()
            career = change_counselor_experience_et_career.text.toString()
            education = change_counselor_experience_et_education.text.toString()
            changeExperience = ChangeExperience(certificate, career, education)
            changeExperiencePresenter.saveInfo(changeExperience)

        }
    }
}
