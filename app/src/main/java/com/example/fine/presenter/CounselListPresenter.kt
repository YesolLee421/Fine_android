package com.example.fine.presenter

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.example.fine.adapter.CounselListAdapter
import com.example.fine.model.Case
import com.example.fine.model.userData
import com.example.fine.view.activity.MyPageActivity
import com.example.fine.view.activity.MyPageCounselorActivity
import com.example.fine.view.activity.SearchCounselorActivity

class CounselListPresenter(): CounselListContract.Presenter {
    override fun getUser() {
        preferences = mContext.getSharedPreferences("USERSIGN", 0)

        user.user_uid = preferences.getString("user_uid", "")!!
        user.email = preferences.getString("user_email", "")!!
        //user.password = preferences.getString("user_password", "")!!
        user.nickname = preferences.getString("user_nickname","")!!
        user.type = preferences.getInt("user_type", 3)
    }

    lateinit var preferences: SharedPreferences
    var user: userData = userData("","","","",3, false)

    override fun startMypageActivity() {
        showMessage("마이페이지로 이동 type = ${user.type}")
        executionLog(TAG, "마이페이지로 이동")
        lateinit var intent: Intent

        when(user.type){
            1-> showMessage("관리자 마이페이지 미구현")
            2-> intent = Intent(mContext, MyPageCounselorActivity::class.java)
            3-> intent = Intent(mContext, MyPageActivity::class.java)
        }
        mContext.startActivity(intent)
    }

    override fun startCounselorListActivity() {
        showMessage("상담사 리스트로 이동")
        executionLog(TAG, "상담사 리스트로 이동")
        mContext.startActivity(Intent(mContext, SearchCounselorActivity::class.java))
    }

    override fun clearItems(list: ArrayList<Case?>, adapter: CounselListAdapter) {
        adapter.arrayList.clear()
    }

    override fun loadItems(list: ArrayList<Case?>, adapter: CounselListAdapter) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override lateinit var mView: CounselListContract.View
    override lateinit var mContext: Context
    override val TAG: String = "CounselListPresenter"
}