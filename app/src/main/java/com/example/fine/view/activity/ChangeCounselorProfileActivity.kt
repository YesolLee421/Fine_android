package com.example.fine.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.content.ContextCompat
import com.example.fine.R
import com.example.fine.model.ChangeProfile
import com.example.fine.model.CounselorData
import com.example.fine.presenter.ChangeCounselorProfileContract
import com.example.fine.presenter.ChangeCounselorProfilePresenter
import kotlinx.android.synthetic.main.activity_change_counselor_profile.*

class ChangeCounselorProfileActivity : BaseActivity(), ChangeCounselorProfileContract.View, AdapterView.OnItemSelectedListener {
    
    // spinner 함수
    override fun onNothingSelected(p0: AdapterView<*>?) {
        changeCounselorProfilePresenter.showMessage("성별을 선택해 주십시오.")
    }

    override fun onItemSelected(parent: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        val item: String = parent?.getItemAtPosition(position).toString()
        changeCounselorProfilePresenter.showMessage(item + " 선택")
        setGender(position+1)
        gender = position+1
    }

    // 정보 보여주는 함수
    override fun showInfo(
        name_formal: String?,
        description: String?,
        count: Int,
        isVerified: Boolean,
        gender: Int,
        picture: String?
    ) {
        if(name_formal!=null){
            change_profile_tv_name_formal.text = "${name_formal} 상담사"
            change_profile_et_name_formal.visibility = View.INVISIBLE
        } else {
            change_profile_et_name_formal.visibility = View.VISIBLE
        }
        if(description!=null){
            changeCounselorProfilePresenter.executionLog(TAG, "description = ${description}")
            change_profile_et_description.setText(description)
            // change_profile_et_description.setText("dkdkdk")
        }
        // count : 프로필에선 횟수 그대로 보여주기
        change_profile_tv_count.text = "총 ${count}회 상담"

        // isVerified
        if(isVerified){
            change_profile_tv_isVerified.text = "전문 상담사 인증 완료"
            change_profile_tv_isVerified.setTextColor(ContextCompat.getColor(this, R.color.customDarkGreen))
        } else {
            change_profile_tv_isVerified.text = "전문 상담사 미인증"
            change_profile_tv_isVerified.setTextColor(ContextCompat.getColor(this, R.color.customRed))
        }
        // gender
        change_profile_tv_gender.text = setGender(gender)

        // picture
    }

    // 성별 int 받아서 string 리턴
    fun setGender(gender: Int): String{
        when(gender){
            1-> return "여성"
            2-> return "남성"
            else -> return "기타"
        }
    }
    
    override val TAG: String = "ChangeCounselorProfileActivity"
    lateinit var changeCounselorProfilePresenter: ChangeCounselorProfilePresenter
    lateinit var spinner: Spinner
    var gender: Int = 0

    override fun initPresenter() {
        changeCounselorProfilePresenter = ChangeCounselorProfilePresenter()
        changeCounselorProfilePresenter.mContext = this
        changeCounselorProfilePresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_counselor_profile)

        var name_formal: String? = intent.getStringExtra("name_formal")
        var description = intent.getStringExtra("description")
        var picture: String? = intent.getStringExtra("picture")
        gender = intent.getIntExtra("gender",1)
        val count: Int = intent.getIntExtra("count",0)
        val isVerified = intent.getBooleanExtra("isVerified", false)

        // 정보 띄우기
        showInfo(name_formal, description, count, isVerified, gender, picture)
        // 스피너 세팅
        setSpinner()

        // save 버튼
        change_profile_btn_save.setOnClickListener {
            if(name_formal==null && change_profile_et_name_formal.text.toString()==""){
                changeCounselorProfilePresenter.showMessage("실명을 반드시 적어주십시오.")
                name_formal = change_profile_et_name_formal.text.toString()
            }
            description = change_profile_et_description.text.toString()
            // gender는 이미 저장됨
            // picture는 추후 구현
            picture = null

            // 여기 picture 정보가 없다. 괜찮나? 어차피 file로 보낼거긴 한데
            changeCounselorProfilePresenter.saveInfo(name_formal!!, description, gender, picture)
        }
    }

    fun setSpinner(){
        spinner = findViewById(R.id.change_profile_spinner_gender)
        ArrayAdapter.createFromResource(
            this,
            R.array.gender,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
        spinner.onItemSelectedListener = this
    }
}
