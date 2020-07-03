package com.example.fine.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ViewFlipper
import com.example.fine.R
import com.example.fine.model.Paper
import com.example.fine.presenter.WriteCounselPaperContract
import com.example.fine.presenter.WriteCounselPaperPresenter
import kotlinx.android.synthetic.main.activity_write_counsel_paper1.*

class WriteCounselPaperActivity : BaseActivity(), WriteCounselPaperContract.View {

    override val TAG: String = "WriteCounselPaperActivity"

    lateinit var mViewFlipper : ViewFlipper
    lateinit var presenter : WriteCounselPaperPresenter

    override fun initPresenter() {
        presenter = WriteCounselPaperPresenter()
        presenter.mContext = this
        presenter.mView = this
    }

    override fun showInfo(paper: Paper) {
        // 성별
        setGender(paper.gender)
        // 출생연도
        write_counsel_paper_et_birth_year.setText(paper.birth_year.toString())
        // 직업
        setJob(paper.job)

        // 상담 경험
        setCounselBefore(paper.counselBefore)
        // 정신과 경험
        setClinicBefore(paper.clinicBefore)
        // 주요문제
        // 증상
        // 종교
        setReligion(paper.religion)
        // 최종학력
        setEducation(paper.education)
        // 경제적 생활수준
        setLivingCondition(paper.livingCondition)
        // 결혼 여부
        setIsMarried(paper.isMarried)
        // 동거인 유무
        setHasMate(paper.hasMate)
        // 원가족 관계: 줄글 자유입력
        write_counsel_paper_et_family.setText(paper.family.toString())
        // 상담사에게 전달사항: 줄글 자유입력
        write_counsel_paper_et_request.setText(paper.request.toString())

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_counsel_paper1)

        mViewFlipper = findViewById(R.id.write_counsel_paper1_vf)

        write_counsel_paper1_tv_btn_back.setOnClickListener {
            mViewFlipper.showPrevious()
        }
        write_counsel_paper1_tv_btn_save.setOnClickListener{
            presenter.saveInfo(savePaper())
        }
        write_counsel_paper1_tv_btn_next.setOnClickListener {
            mViewFlipper.showNext()
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.loadData()
    }

    fun savePaper() : Paper {
        // paper 객체 만들기
        var paper: Paper
        return paper
    }

    // 성별: 여, 남, 기타
    fun setGender(gender: Int?){
        if(gender!=null){
            write_counsel_paper_rg_gender.check(gender)
        }
    }
    // 직업: 학생, 취업준비생, 직장인, 자영업/프리랜서, 무직, 기타
    fun setJob(job: Int?){
        if(job!=null){
            write_counsel_paper_rg_job.check(job)
        }
    }
    // 심리상담 경험 여부: 있다/없다
    fun setCounselBefore (counselBefore: Int?){
        if(counselBefore!=null) {
            write_counsel_paper_rg_counselBefore.check(counselBefore)
        }
    }
    // 신경정신과 경험 여부: 있다(상담만), 있다(약물처방), 없다
    fun setClinicBefore (clinicBefore: Int?) {
        if(clinicBefore!=null) {
            write_counsel_paper_rg_clinicBefore.check(clinicBefore)
        }
    }
    /* 상담 원하는 주요문제: 일반, 연애, 대인관계, 정신건강, 자아/성격,
    가족, 취업/진로, 학업/고시, 성추행, 직장, 외모, 결혼/육아, 금전/사업,
    신체건강, 이별/이혼, 중독/집착, LGBT
     */
    /* 심리적 어려움(증상): 스트레스, 조울증, 우울, 불안, 화병, 공황, 강박, 
    트라우마, 자존감, 콤플렉스, 신체화, 불면, 섭식, 충동/폭력, 자살, 조현병, 기타
     */
    // 종교: 무교, 기독교, 천주교, 불교, 기타
    fun setReligion (religion: Int?) {
        if(religion!=null) {
            write_counsel_paper_rg_religion.check(religion)
        }
    }
    // 최종 학력: 중졸 이하, 고졸, 대학 재학, 대학 졸업, 석사 재학 이상
    fun setEducation (education: Int?) {
        if(education!=null) {
            write_counsel_paper_rg_education.check(education)
        }
    }
    // 경제생활수준: 1~5
    fun setLivingCondition (livingCondition: Int?) {
        if(livingCondition!=null) {
            write_counsel_paper_rg_livingCondition.check(livingCondition)
        }
    }
    // 결혼여부: 미혼, 기혼, 이혼, 사별
    fun setIsMarried ( isMarried: Int?) {
        if(isMarried!=null) {
            write_counsel_paper_rg_isMarried.check(isMarried)
        }
    }
    // 동거인 유무: 있다, 없다
    fun setHasMate (hasMate: Int?) {
        if(hasMate!=null){
            write_counsel_paper_rg_isMarried.check(hasMate)
        }
    }

}
