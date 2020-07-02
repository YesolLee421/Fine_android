package com.example.fine.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fine.R
import com.example.fine.model.Paper
import com.example.fine.presenter.CheckCounselPaperContract
import com.example.fine.presenter.CheckCounselPaperPresenter
import kotlinx.android.synthetic.main.activity_check_counsel_paper.*

class CheckCounselPaperActivity : BaseActivity(), CheckCounselPaperContract.View {
    fun setGender(gender: Int): String{
        when (gender){
            1-> return "여성"
            2-> return "남성"
            else -> return "기타"
        }
    }
    // DB에 paper정보 저장을 그냥 줄글로 해버리는건 어떨까?
    fun setJob(job: Int): String{
        when (job){
            1-> return "여성"
            2-> return "남성"
            else -> return "기타"
        }
    }

    override fun showInfo(paper: Paper) {
        // 1. 성별 / 출생연도 / 직업
        check_paper_tv_gender.text = setGender(paper.gender!!)
        check_paper_tv_birth_year.text = paper.birth_year!!.toString()



        // 2. 상담 경험 / 병원진료경험 / 나의상황(주요문제) / 심리적어려움(증상)

        // 3. 종교 / 최종학력 / 경제생활수준 / 결혼여부 / 동거인유무 / 원가족관계

        // 4. 상담사 참고사항
    }

    override val TAG: String = "CheckCounselPaperActivity"
    lateinit var presenter: CheckCounselPaperPresenter
    var paper_id: Int = -1

    override fun initPresenter() {
        presenter = CheckCounselPaperPresenter()
        presenter.mContext = this
        presenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_counsel_paper)

        // 유저정보 받기
        presenter.getUser()

        check_paper_info1_btn.setOnClickListener {
            presenter.startWritePaperActivity(1)
        }
        check_paper_info2_btn.setOnClickListener {
            presenter.startWritePaperActivity(2)
        }
        check_paper_info3_btn.setOnClickListener {
            presenter.startWritePaperActivity(3)
        }
        check_paper_info4_btn.setOnClickListener {
            presenter.startWritePaperActivity(3)
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.loadData(paper_id)
    }
}
