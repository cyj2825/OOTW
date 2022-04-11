package com.example.ootw

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.example.ootw.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // 전역 변수로 바인딩 객체 선언
    private var mBinding: ActivityMainBinding? = null
    // 매번 null 체크를 할 필요없이 편의성을 위해 바인딩 변수 재선언
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 바인딩
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvFindId.setOnClickListener {
            Log.d("TestLog", "find id")
            startActivity(Intent(this, FindIdActivity::class.java))
        }
        binding.tvFindPw.setOnClickListener {
            Log.d("TestLog", "find pw")
            startActivity(Intent(this, FindPwActivity::class.java))
        }
    }

    // 액티비티가 파괴될 때..
    override fun onDestroy() {
    // onDestroy 에서 binding class 인스턴스 참조를 정리해주어야 한다.
        mBinding = null
        super.onDestroy()
    }

}