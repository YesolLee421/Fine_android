package com.example.fine.view

import android.content.Context
import android.util.Log

interface BaseView {
    val TAG: String

    // executionLog : 공통으로 사용하는 Log 출력 부분을 생성하는 함수
    fun executionLog(tag: String, msg: String){
        Log.d(tag,msg)
    }




}