package com.example.ootw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ootw.databinding.ActivitySignUpBinding
import com.example.ootw.fragment.SignUp1Fragment

class SignUpActivity : AppCompatActivity() {
    // 전역 변수로 바인딩 객체 선언
    private var mBinding: ActivitySignUpBinding? = null
    // 매번 null 체크를 할 필요없이 편의성을 위해 바인딩 변수 재선언
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 바인딩
        mBinding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.signup_screen_panel, SignUp1Fragment()).commit()
    }
}