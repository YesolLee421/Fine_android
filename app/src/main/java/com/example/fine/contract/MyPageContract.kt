package com.example.fine.presenter

import com.example.fine.model.userData
import com.example.fine.view.BaseView

interface MyPageContract {

    interface View : BaseView {
        fun showInfo(user: userData)
    }

    interface Presenter : BasePresenter<View>{
        // 데이터 요청해서 view로 전달
        fun loadData()

        // 닉네임 변경
        fun startChangeNickNameActivity()
        // 비밀번호 변경
        fun startChangePasswordActivity()
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