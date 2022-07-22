package com.example.ootw

import android.R
import android.app.DatePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
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

        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    // 액티비티가 파괴될 때..
    override fun onDestroy() {
        // onDestroy 에서 binding class 인스턴스 참조를 정리해주어야 한다.
        mBinding = null
        super.onDestroy()
    }
}