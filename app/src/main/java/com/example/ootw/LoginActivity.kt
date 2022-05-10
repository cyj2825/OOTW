package com.example.ootw

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.ootw.databinding.ActivityLoginBinding
import com.kakao.sdk.common.util.Utility

class LoginActivity : AppCompatActivity() {
    // 전역 변수로 바인딩 객체 선언
    private var mBinding: ActivityLoginBinding? = null
    // 매번 null 체크를 할 필요없이 편의성을 위해 바인딩 변수 재선언
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 바인딩
        mBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val KeyHash = Utility.getKeyHash(this)
        Log.d("Hash", KeyHash)

        binding.tvFindId.setOnClickListener {
            Log.d("TestLog", "find id")
            startActivity(Intent(this, FindIdActivity::class.java))
        }
        binding.tvFindPw.setOnClickListener {
            Log.d("TestLog", "find pw")
            startActivity(Intent(this, FindPwActivity::class.java))
        }
        binding.btnMainLogin.setOnClickListener {
            Log.d("TestLog", "main")
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    // 액티비티가 파괴될 때..
    override fun onDestroy() {
    // onDestroy 에서 binding class 인스턴스 참조를 정리해주어야 한다.
        mBinding = null
        super.onDestroy()
    }

}