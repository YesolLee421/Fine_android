package com.example.fine.view.activity

import android.R.layout.simple_spinner_dropdown_item
import android.R.layout.simple_spinner_item
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.content.ContextCompat
import com.example.fine.R
import com.example.fine.model.ChangeBankAccount
import com.example.fine.presenter.ChangeCounselorBankAccountContract
import com.example.fine.presenter.ChangeCounselorBankAccountPresenter
import kotlinx.android.synthetic.main.activity_change_counselor_bank_account.*

class ChangeCounselorBankAccountActivity : BaseActivity(), ChangeCounselorBankAccountContract.View, AdapterView.OnItemSelectedListener {
    override fun onNothingSelected(p0: AdapterView<*>?) {
        changeBankAccountPresenter.showMessage("은행을 선택해 주십시오.")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val item: String = parent?.getItemAtPosition(position).toString()
        changeBankAccountPresenter.showMessage(item + " 선택")
        bank_name = item
    }

    override fun showInfo(bank_name: String?, account_number: String?) {
        if(bank_name!=null){
            change_bank_account_tv_bank_name.text = bank_name
            if(account_number!=null){
                change_bank_account_tv_account_number.text = account_number
                return
            }
            return
        }
        change_bank_account_tv_bank_name.text = "연결된 계좌 없음"
    }

    override val TAG: String = "ChangeCounselorBankAccountActivity"
    lateinit var changeBankAccountPresenter: ChangeCounselorBankAccountPresenter
    var bank_name: String? = null
    var account_number: String? = null
    lateinit var spinner: Spinner



    override fun initPresenter() {
        changeBankAccountPresenter = ChangeCounselorBankAccountPresenter()
        changeBankAccountPresenter.mContext = this
        changeBankAccountPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_counselor_bank_account)

        // 은행명 선택 스피너 세팅
        setSpinner()

        bank_name = intent.getStringExtra("bank_name")
        account_number = intent.getStringExtra("account_number")
        showInfo(bank_name, account_number)

        change_bank_account_btn_save.setOnClickListener {
            if(change_bank_account_et_account_number.text.toString()!=""){
                account_number = change_bank_account_et_account_number.text.toString()
                changeBankAccountPresenter.executionLog(TAG, "bank_name="+bank_name+" / account_number="+account_number)
                changeBankAccountPresenter.saveInfo(ChangeBankAccount(bank_name, account_number))
            } else {
                changeBankAccountPresenter.showMessage("은행명과 계좌번호를 입력해 주십시오.")
            }

        }

    }

    fun setSpinner(){
        spinner = findViewById(R.id.change_counselor_bank_account_spinner_bank_name)
        ArrayAdapter.createFromResource(
            this,
            R.array.bank_array,
            simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
        spinner.onItemSelectedListener = this
    }
}
