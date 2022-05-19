package com.example.ootw

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ootw.databinding.ActivityFindPwBinding

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

        binding.tvFindpwBack.setOnClickListener {
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