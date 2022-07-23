package com.example.ootw.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.ootw.R
import java.text.SimpleDateFormat
import java.util.*

class SignUp2Fragment : Fragment(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_up2, container, false)
        view.findViewById<Button>(R.id.btn_SignUp2_next).setOnClickListener(this)
        view.findViewById<ImageView>(R.id.iv_SignUp2_back).setOnClickListener(this)
        view.findViewById<Button>(R.id.btn_SignUp2_pickdate).setOnClickListener(this)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 이메일 도메인 spinner
        var spinner: Spinner = requireView().findViewById(R.id.spin_SignUp2_email)
        spinner.adapter = ArrayAdapter.createFromResource(requireActivity(), com.example.ootw.R.array.spinner_email, android.R.layout.simple_spinner_item)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var etEmail: EditText = requireView().findViewById(R.id.et_SignUp2_email2)
                when (position) {
                    0 -> {
                        etEmail.setText("")
                    } 1 -> {
                        etEmail.setText("gmail.com")
                    } 2 -> {
                        etEmail.setText("naver.com")
                    } 3 -> {
                        etEmail.setText("daum.net")
                    } 4 -> {
                        etEmail.setText("hanmail.net")
                    } 5 -> {
                        etEmail.setText("nate.com")
                    }
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_SignUp2_next -> {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.signup_screen_panel, SignUp3Fragment()).commitNow()
            }
            R.id.iv_SignUp2_back -> {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.signup_screen_panel, SignUp1Fragment()).commitNow()
            }
            R.id.btn_SignUp2_pickdate -> {
                var formatDate = SimpleDateFormat("yyyy - MM - dd", Locale.KOREA)
                val getDate: Calendar = Calendar.getInstance()
//                val datePicker = DatePickerDialog(this, android.R.style.Theme_Holo_Dialog_MinWidth, DatePickerDialog.OnDateSetListener{ datePicker, i, i2, i3 ->
//                    val selectDate = Calendar.getInstance()
//                    selectDate.set(Calendar.YEAR, i)
//                    selectDate.set(Calendar.MONTH, i2)
//                    selectDate.set(Calendar.DAY_OF_MONTH, i3)
//                    val date = formatDate.format(selectDate.time)
//                    R.id.btn_SignUp2_pickdate.text = date
//                }, getDate.get(Calendar.YEAR), getDate.get(Calendar.MONTH), getDate.get(Calendar.DAY_OF_MONTH))
//                datePicker.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//                datePicker.show()
            }
            else -> {
            }
        }
    }
}