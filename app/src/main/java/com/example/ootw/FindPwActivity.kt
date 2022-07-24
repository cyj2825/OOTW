package com.example.ootw

import android.R
import android.app.DatePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.ootw.databinding.ActivityFindPwBinding
import java.text.SimpleDateFormat
import java.util.*

class FindPwActivity: AppCompatActivity() {
    // 전역 변수로 바인딩 객체 선언
    private var mBinding: ActivityFindPwBinding? = null
    // 매번 null 체크를 할 필요없이 편의성을 위해 바인딩 변수 재선언
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 바인딩
        mBinding = ActivityFindPwBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var formatDate = SimpleDateFormat("yyyy - MM - dd", Locale.KOREA)
        binding.btnFindPwPickdate.setOnClickListener {
            val getDate: Calendar = Calendar.getInstance()
            val datePicker = DatePickerDialog(this, R.style.Theme_Holo_Dialog_MinWidth, DatePickerDialog.OnDateSetListener{ datePicker, i, i2, i3 ->
                val selectDate = Calendar.getInstance()
                selectDate.set(Calendar.YEAR, i)
                selectDate.set(Calendar.MONTH, i2)
                selectDate.set(Calendar.DAY_OF_MONTH, i3)
                val date = formatDate.format(selectDate.time)
                binding.btnFindPwPickdate.text = date
            }, getDate.get(Calendar.YEAR), getDate.get(Calendar.MONTH), getDate.get(Calendar.DAY_OF_MONTH))
            datePicker.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            datePicker.show()
        }

        // 뒤로가기
        binding.ivBack.setOnClickListener {
            onBackPressed()
        }

        // 이메일 도메인 spinner
        binding.spinFindPwEmail.adapter = ArrayAdapter.createFromResource(this, com.example.ootw.R.array.spinner_email, android.R.layout.simple_spinner_item)
        binding.spinFindPwEmail.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    0 -> {
                        binding.etFindPwEmail2.setText("")
                    } 1 -> {
                    binding.etFindPwEmail2.setText("gmail.com")
                } 2 -> {
                    binding.etFindPwEmail2.setText("naver.com")
                } 3 -> {
                    binding.etFindPwEmail2.setText("daum.net")
                } 4 -> {
                    binding.etFindPwEmail2.setText("hanmail.net")
                } 5 -> {
                    binding.etFindPwEmail2.setText("nate.com")
                }
                }
            }
        }
    }

    // 액티비티가 파괴될 때..
    override fun onDestroy() {
        // onDestroy 에서 binding class 인스턴스 참조를 정리해주어야 한다.
        mBinding = null
        super.onDestroy()
    }
}