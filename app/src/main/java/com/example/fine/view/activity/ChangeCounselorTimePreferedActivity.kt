package com.example.fine.view.activity

import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import com.example.fine.R
import com.example.fine.model.ChangeTimePrefered
import com.example.fine.presenter.ChangeCounselorTimePreferedContract
import com.example.fine.presenter.ChangeCounselorTimePreferedPresenter
import com.google.gson.JsonArray
import kotlinx.android.synthetic.main.activity_change_counselor_time_prefered.*
import org.json.JSONArray
import org.json.JSONObject

class ChangeCounselorTimePreferedActivity : BaseActivity() , ChangeCounselorTimePreferedContract.View{

    fun showDay(day: JSONArray?){
        if(day!=null && day.length()!=0){
            for(i in 0 until day.length()-1){
                val str_day: String = day[i].toString()
                when(str_day){
                    "mon"-> activity_change_counselor_day_prefered_cb1_mon.isChecked = true
                    "tue"-> activity_change_counselor_day_prefered_cb2_tue.isChecked = true
                    "wed"-> activity_change_counselor_day_prefered_cb3_wed.isChecked = true
                    "thur"-> activity_change_counselor_day_prefered_cb4_thur.isChecked = true
                    "fri"->activity_change_counselor_day_prefered_cb5_fri.isChecked = true
                    "sat"->activity_change_counselor_day_prefered_cb6_sat.isChecked = true
                    "sun"->activity_change_counselor_day_prefered_cb7_sun.isChecked = true
                }
            }
            //activity_change_counselor_day_prefered_cb0_all.isChecked = day.length()==7
        } else {
            changeTimePreferedPresenter.showMessage("선택한 요일 없음")
        }
    }

    fun showTime(time: JSONArray?){
        if(time!=null && time.length()!=0){
            for(i in 0 until time.length()-1){
                val str_time: String = time[i].toString()
                when(str_time){
                    "1"-> activity_change_counselor_time_prefered_cb1.isChecked = true
                    "2"-> activity_change_counselor_time_prefered_cb2.isChecked = true
                    "3"-> activity_change_counselor_time_prefered_cb3.isChecked = true
                    "4"-> activity_change_counselor_time_prefered_cb4.isChecked = true
                }
            }
        } else {
            changeTimePreferedPresenter.showMessage("선택한 시간대 없음")
        }
    }

    override fun showInfo(day: JSONArray?, time: JSONArray?) {
        showDay(day)
        showTime(time)
    }

    override val TAG: String = "ChangeCounselorTimePreferedActivity"
    lateinit var changeTimePreferedPresenter: ChangeCounselorTimePreferedPresenter
    override fun initPresenter() {
        changeTimePreferedPresenter = ChangeCounselorTimePreferedPresenter()
        changeTimePreferedPresenter.mContext = this
        changeTimePreferedPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_counselor_time_prefered)
        val str = intent.getStringExtra("time_prefered")
        if(str!=null){
            val json = JSONObject(str)
            val day: JSONArray = JSONArray(json.get("day").toString())
            val time: JSONArray = JSONArray(json.get("time").toString())
            showInfo(day,time)
        }

        change_counselor_time_prefered_btn_save.setOnClickListener {
            changeTimePreferedPresenter.executionLog(TAG, "day = "+getDay())
            changeTimePreferedPresenter.executionLog(TAG, "time = "+getTime())
            changeTimePreferedPresenter.saveInfo(ChangeTimePrefered(getDay(), getTime()))
        }
    }

    fun getDay(): JSONArray{
        var arr = JSONArray()
        if(activity_change_counselor_day_prefered_cb1_mon.isChecked) arr.put("mon")
        if(activity_change_counselor_day_prefered_cb2_tue.isChecked) arr.put("tue")
        if(activity_change_counselor_day_prefered_cb3_wed.isChecked) arr.put("wed")
        if(activity_change_counselor_day_prefered_cb4_thur.isChecked) arr.put("thur")
        if(activity_change_counselor_day_prefered_cb5_fri.isChecked) arr.put("fri")
        if(activity_change_counselor_day_prefered_cb6_sat.isChecked) arr.put("sat")
        if(activity_change_counselor_day_prefered_cb7_sun.isChecked) arr.put("sun")
        Log.d(TAG, "getTime() arr = $arr")
        return arr
    }

    fun getTime(): JSONArray{
        var arr = JSONArray()
        if(activity_change_counselor_time_prefered_cb1.isChecked) arr.put(1)
        if(activity_change_counselor_time_prefered_cb2.isChecked) arr.put(2)
        if(activity_change_counselor_time_prefered_cb3.isChecked) arr.put(3)
        if(activity_change_counselor_time_prefered_cb4.isChecked) arr.put(4)
        Log.d(TAG, "getTime() arr = $arr")
        return arr
    }
}
