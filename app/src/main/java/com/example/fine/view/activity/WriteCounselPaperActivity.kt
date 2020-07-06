package com.example.fine.view.activity

import android.os.Bundle
import android.widget.CheckBox
import android.widget.ViewFlipper
import com.example.fine.R
import com.example.fine.model.ChangePaperAll
import com.example.fine.model.Paper
import com.example.fine.presenter.WriteCounselPaperContract
import com.example.fine.presenter.WriteCounselPaperPresenter
import kotlinx.android.synthetic.main.activity_write_counsel_paper1.*
import kotlinx.coroutines.joinAll
import org.json.JSONArray

class WriteCounselPaperActivity : BaseActivity(), WriteCounselPaperContract.View {

    override val TAG: String = "WriteCounselPaperActivity"

    lateinit var mViewFlipper : ViewFlipper
    lateinit var presenter : WriteCounselPaperPresenter
    var problem = listOf<String>("일반", "연애", "대인관계", "정신건강"," 자아/성격", "가족",
        "취업/진로", "학업/고시", "성추행", "직장", "외모", "결혼/육아", "금전/사업", "신체건강",
        "이별/이혼", "중독/집착", "LGBT" )
    var symptom = listOf<String>("스트레스", "조울증", "우울", "불안", "화병", "공황", "강박", "트라우마", "자존감",
        "콤플렉스", "신체화(몸 이상)", "불면", "섭식", "충동/폭력", "자살", "조현병", "기타")

    lateinit var checkboxList_problem: ArrayList<CheckBox>
    lateinit var checkboxList_symptom: ArrayList<CheckBox>

    var checkboxId_problem : List<Int> = listOf(
        R.id.write_counsel_paper_problem1,
        R.id.write_counsel_paper_problem2,
        R.id.write_counsel_paper_problem3,
        R.id.write_counsel_paper_problem4,
        R.id.write_counsel_paper_problem5,
        R.id.write_counsel_paper_problem6,
        R.id.write_counsel_paper_problem7,
        R.id.write_counsel_paper_problem8,
        R.id.write_counsel_paper_problem9,
        R.id.write_counsel_paper_problem10,
        R.id.write_counsel_paper_problem11,
        R.id.write_counsel_paper_problem12,
        R.id.write_counsel_paper_problem13,
        R.id.write_counsel_paper_problem14,
        R.id.write_counsel_paper_problem15,
        R.id.write_counsel_paper_problem16,
        R.id.write_counsel_paper_problem17
        )
    var checkboxId_symptom = listOf<Int>(
        R.id.write_counsel_paper_symptom1,
        R.id.write_counsel_paper_symptom2,
        R.id.write_counsel_paper_symptom3,
        R.id.write_counsel_paper_symptom4,
        R.id.write_counsel_paper_symptom5,
        R.id.write_counsel_paper_symptom6,
        R.id.write_counsel_paper_symptom7,
        R.id.write_counsel_paper_symptom8,
        R.id.write_counsel_paper_symptom9,
        R.id.write_counsel_paper_symptom10,
        R.id.write_counsel_paper_symptom11,
        R.id.write_counsel_paper_symptom12,
        R.id.write_counsel_paper_symptom13,
        R.id.write_counsel_paper_symptom14,
        R.id.write_counsel_paper_symptom15,
        R.id.write_counsel_paper_symptom16,
        R.id.write_counsel_paper_symptom17
    )

    fun setCheckBox() {
        checkboxList_problem = ArrayList<CheckBox>(checkboxId_problem.size)
        checkboxList_symptom = ArrayList<CheckBox>(checkboxId_symptom.size)

        presenter.executionLog(TAG, "id_problem size=${checkboxId_problem.size}" +
                " / id_symptom size =  ${checkboxId_symptom.size}")

        // problem
//        for (index in problem.indices){
//            val checkBox: CheckBox = findViewById(checkboxId_problem[index])
//            checkBox.text = problem[index]
//            checkboxList_problem.add(checkBox)
//        }
//        // symptom
//        for (index in symptom.indices){
//            val checkBox: CheckBox = findViewById(checkboxId_symptom[index])
//            checkBox.text = symptom[index]
//            checkboxList_problem.add(checkBox)
//        }
    }
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
//        setProblem(paper.problem)
//        // 증상
//        setSymptom(paper.symptom)
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

        setCheckBox()

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

    fun savePaper() : ChangePaperAll {
        // paper 객체 만들기
        val paper: ChangePaperAll = ChangePaperAll(-1, -1, -1, -1, -1, null, null,
            -1, -1, -1, -1, -1, null, null)

        // 성별, 출생연도, 직업
        paper.gender = write_counsel_paper_rg_gender.checkedRadioButtonId
        paper.birth_year = Integer.parseInt(write_counsel_paper_et_birth_year.text.toString())
        paper.job = write_counsel_paper_rg_job.checkedRadioButtonId

        // 심리상담 경험여부, 정신과 경험 여부
        paper.counselBefore = write_counsel_paper_rg_counselBefore.checkedRadioButtonId
        paper.clinicBefore = write_counsel_paper_rg_clinicBefore.checkedRadioButtonId
        // 주요문제
//        val jsonArray_problem = JSONArray()
//        for (i in checkboxList_problem.indices) {
//            if(checkboxList_problem[i].isChecked) {
//                jsonArray_problem.put(i)
//            }
//        }
//        if(jsonArray_problem.length()!=0) paper.problem = jsonArray_problem.toString()
//        // 주요증상
//        val jsonArray_symptom = JSONArray()
//        for(i in checkboxList_symptom.indices) {
//            if(checkboxList_symptom[i].isChecked) {
//                jsonArray_symptom.put(i)
//            }
//        }
//        if(jsonArray_symptom.length()!=0) paper.symptom = jsonArray_symptom.toString()

        // 종교, 최종학력, 경제생활수준, 결혼여부, 동거인유무, 원가족관계(String), 전할말
        paper.religion = write_counsel_paper_rg_religion.checkedRadioButtonId
        paper.education = write_counsel_paper_rg_education.checkedRadioButtonId
        paper.livingCondition = write_counsel_paper_rg_livingCondition.checkedRadioButtonId
        paper.isMarried = write_counsel_paper_rg_isMarried.checkedRadioButtonId
        paper.hasMate = write_counsel_paper_rg_hasMate.checkedRadioButtonId
        paper.family = write_counsel_paper_et_family.text.toString()
        paper.request = write_counsel_paper_et_request.text.toString()

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
    fun setProblem (json: String?) {
        if(json!==null) {
            val problem = JSONArray(json)
            if(problem.length()!=0) {
                for (element in 0 until problem.length()){
                    checkboxList_problem[element].isChecked = true
                }
            }
        }

    }
    /* 심리적 어려움(증상): 스트레스, 조울증, 우울, 불안, 화병, 공황, 강박, 
    트라우마, 자존감, 콤플렉스, 신체화, 불면, 섭식, 충동/폭력, 자살, 조현병, 기타
     */
    fun setSymptom (json:String?) {
        if(json!==null) {
            val symptom = JSONArray(json)
            if(symptom.length()!=0) {
                for (element in 0 until symptom.length()){
                    checkboxList_symptom[element].isChecked = true
                }
            }
        }
    }
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
