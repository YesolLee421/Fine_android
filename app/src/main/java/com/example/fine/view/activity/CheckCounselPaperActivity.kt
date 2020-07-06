package com.example.fine.view.activity

import android.os.Bundle
import com.example.fine.R
import com.example.fine.model.Paper
import com.example.fine.presenter.CheckCounselPaperContract
import com.example.fine.presenter.CheckCounselPaperPresenter
import kotlinx.android.synthetic.main.activity_check_counsel_paper.*
import org.json.JSONArray
import java.lang.StringBuilder

class CheckCounselPaperActivity : BaseActivity(), CheckCounselPaperContract.View {
    val problem = listOf<String>("일반", "연애", "대인관계", "정신건강"," 자아/성격", "가족",
        "취업/진로", "학업/고시", "성추행", "직장", "외모", "결혼/육아", "금전/사업", "신체건강",
        "이별/이혼", "중독/집착", "LGBT" )
    val symptom = listOf<String>("스트레스", "조울증", "우울", "불안", "화병", "공황", "강박", "트라우마", "자존감",
        "콤플렉스", "신체화(몸 이상)", "불면", "섭식", "충동/폭력", "자살", "조현병", "기타")
    val religion = listOf<String>("종교 없음", "기독교", "천주교", "불교", "기타")
    val education = listOf<String>("중졸 이하", "고졸", "대학 재학", "대학 졸업", "석사 재학 이상")
    val livingCondition = listOf<String>("아주 힘들다", "조금 힘들다", "보통이다", "여유있다", "충분히 여유있다")
    val isMarried = listOf<String>("미혼", "기혼", "이혼","사별")
    val job = listOf("학생", "취업준비생", "직장인", "자영업/프리랜서", "무직", "기타")


    fun setGender(gender: Int?): String{
        when (gender){
            1-> return "여성"
            2-> return "남성"
            null -> return "선택 안함"
            else -> return "기타"
        }
    }
    // DB에 paper정보 저장을 그냥 줄글로 해버리는건 어떨까?

    fun setJsonArrayToString(list: List<String>, jsonArray: JSONArray?) : StringBuilder{
        val str: StringBuilder = StringBuilder()
        if(jsonArray!==null && jsonArray.length()!=0) {
            for (element in 0 until  jsonArray.length()) {
                str.append(list[element])
                    .append(", ")
            }
        } else {
            str.append("선택 안함")
        }
        return str
    }
    fun setIntToString(list: List<String>, value: Int?): String {
        if(value!==null) {
            return list[value]
        } else {
            return "선택 안함"
        }
    }
    fun yesOrNo(value: Int?) : String{
        when(value) {
            0-> return "없습니다."
            1 -> return "있습니다."
            else -> return "선택 안함"
        }
    }
    fun setClinicBefore (clinicBefore: Int?) : String {
        if(clinicBefore!==null) {
            when(clinicBefore) {
                0 -> return "없습니다."
                1-> return "있습니다.(단순상담)"
                else -> return "있습니다.(약물처방)"
            }
        } else {
            return "선택 안함"
        }
    }


    override fun showInfo(paper: Paper) {
        // 1. 성별 / 출생연도 / 직업
        check_paper_tv_gender.text = setGender(paper.gender)
        check_paper_tv_birth_year.text = paper.birth_year.toString()
        check_paper_tv_job.text = setIntToString(job, paper.job)

        // 2. 상담 경험 / 병원진료경험 / 나의상황(주요문제) / 심리적어려움(증상)
        check_paper_tv_counsel_before.text = yesOrNo(paper.counselBefore)
        check_paper_tv_clinic_before.text = setClinicBefore(paper.clinicBefore)
        check_paper_tv_problem.text = setJsonArrayToString(problem, JSONArray(paper.problem))
        check_paper_tv_symptom.text = setJsonArrayToString(symptom, JSONArray(paper.symptom))

        // 3. 종교 / 최종학력 / 경제생활수준 / 결혼여부 / 동거인유무 / 원가족관계
        check_paper_tv_religion.text = setIntToString(religion, paper.religion)
        check_paper_tv_living_condition.text = setIntToString(livingCondition, paper.livingCondition)
        check_paper_tv_education.text = setIntToString(education, paper.education)
        check_paper_tv_is_married.text = setIntToString(isMarried, paper.isMarried)
        check_paper_tv_has_mate.text = yesOrNo(paper.hasMate)
        check_paper_tv_family.text = paper.family

        // 4. 상담사 참고사항
        check_paper_tv_request.text = paper.request
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
