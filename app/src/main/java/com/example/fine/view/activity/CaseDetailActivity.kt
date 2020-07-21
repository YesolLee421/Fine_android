package com.example.fine.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.fine.R
import com.example.fine.model.case_detail
import com.example.fine.presenter.CaseDetailContract
import com.example.fine.presenter.CaseDetailPresenter
import kotlinx.android.synthetic.main.activity_case_detail.*
import java.text.SimpleDateFormat
import java.util.*

class CaseDetailActivity : BaseActivity(), CaseDetailContract.View {
    override fun showInfo(case: case_detail) {

        // 상담사 사진 & 이름
        // case_detail_iv_counselor_picture
        case_detail_tv_counselor_name.text = "${case.counselor_name} 상담사"

        // 상담접수지 제출 여부: 미제출, 제출 완료
        if(case.hasPaper){
            paper_id = case.paper_id
            case_detail_tv_hasPaper.text = "제출 완료"
            case_detail_tv_hasPaper.setTextColor(ContextCompat.getColor(this, R.color.customDarkGreen))
        } else {
            case_detail_tv_hasPaper.text = "미제출"
            case_detail_tv_hasPaper.setTextColor(ContextCompat.getColor(this, R.color.customRed))
        }

        // 상담접수지 - 주요 문제 및 증상 (-)

        // 진행상황: 총 2회기 중 1회기 완료 (1/2)
        val status = "총 ${case.totalCase}회기 중 ${case.usedCase}회기 완료 (${case.usedCase}/${case.totalCase})"
        case_detail_tv_status.text = status

        // 다음 상담 일정 : 6/3 수요일 16시 (2회차)
        val formatter = SimpleDateFormat("MM월 dd일 E a hh:mm", Locale.KOREA)
        if(case.nextCase==null){
            case_detail_tv_nextDate.text = "미정"
        } else {
            case_detail_tv_nextDate.text = formatter.format(case.nextCase!!)
        }
    }

    override val TAG: String = "CaseDetailActivity"
    lateinit var caseDetailPresenter: CaseDetailPresenter
    var paper_id : Int = -1
    var case_id: Int = -1

    override fun initPresenter() {
        caseDetailPresenter = CaseDetailPresenter()
        caseDetailPresenter.mContext = this
        caseDetailPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_case_detail)
        case_id = intent.getIntExtra("case_id", -1)

        case_detail_btn.setOnClickListener { // 채팅상담 시작
            caseDetailPresenter.startChatRoomActivity(case_id)
        }
        case_detail_btn_paper.setOnClickListener {
            caseDetailPresenter.startCheckPaperActivity(paper_id)
        }
    }

    override fun onStart() {
        super.onStart()
        caseDetailPresenter.loadData(intent.getIntExtra("case_id", -1))
    }
}
