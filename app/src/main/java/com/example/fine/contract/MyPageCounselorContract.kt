package com.example.fine.contract

import com.example.fine.model.CounselorData
import com.example.fine.model.userData
import com.example.fine.presenter.BasePresenter
import com.example.fine.view.BaseView

interface MyPageCounselorContract {
    interface View : BaseView {
        fun showInfo(user: userData, counselor: CounselorData)
    }

    interface Presenter : BasePresenter<View> {
        // 데이터 요청해서 view로 전달
        fun loadData()

        // 프로필 변경
        fun startChangeProfileActivity()
        // 비밀번호 변경
        fun startChangePasswordActivity()
        // 상담사 소개 변경
        fun startChangeIntroActivity()
        // 상담사 약력 변경
        fun startChangeCareerActivity()
        // 상담사 선호일정 변경
        fun startChangeTimePreferedActivity()
        // 상담 가격 변경
        fun startChangePriceActivity()
        // 상담사 계좌정보 변경
        fun startChangeBankAccountActivity()
        // 상담접수지 확인
        fun startCheckPaperActivity()
        // 상담동의서 보기
        fun startNoticeActivity()
        // 상담내역 (상담목록) 보기
        fun startCounselListActivity()
        // 앱 설정으로 이동
        fun startSettingsActivity()

    }
}