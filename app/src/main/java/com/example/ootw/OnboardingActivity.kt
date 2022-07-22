package com.example.ootw

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.ootw.databinding.ActivityOnboardingBinding
import kotlinx.coroutines.delay

class OnboardingActivity : AppCompatActivity() {
    // 전역 변수로 바인딩 객체 선언
    private var mBinding: ActivityOnboardingBinding? = null
    // 매번 null 체크를 할 필요없이 편의성을 위해 바인딩 변수 재선언
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 바인딩
        mBinding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launchWhenStarted {
            delay(2000)
            Log.i("Work Login", "로그인이 시작되면 됩니다.")
            val nextIntent = Intent(this@OnboardingActivity, LoginActivity::class.java)
            startActivity(nextIntent)
            finish()
        }
    }
}