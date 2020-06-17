package com.example.fine.presenter

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast

interface BasePresenter <T>{
    var mView: T
    var mContext: Context
    val TAG: String

    // showMessage : 공통으로 사용하는 messsage 출력 부분을 생성하는 함수
    fun showMessage(msg : String?) {
        var message = msg
        if(msg==null){
            message = "아무런 메시지가 없습니다."
        }
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
    }

    // executionLog : 공통으로 사용하는 Log 출력 부분을 생성하는 함수
    fun executionLog(tag: String, msg: String){
        Log.d(tag,msg)
    }



//    // takeView : View가 Create, Bind 될 때 Presenter에 전달하는 함수
//    fun takeView(view: T)
//
//    // dropView : View가 delete, unBind 될 때 Presenter에 전달하는 함수
//    fun dropView()

    // executionLog : 공통으로 사용하는 Log 출력 부분을 생성하는 함수

}