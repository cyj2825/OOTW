package com.example.ootw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ootw.databinding.ActivityPostBinding


class PostActivity : AppCompatActivity() {
    // 전역 변수로 바인딩 객체 선언
    private var mBinding: ActivityPostBinding? = null
    // 매번 null 체크를 할 필요없이 편의성을 위해 바인딩 변수 재선언
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 바인딩
        mBinding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivPostFeedphoto.setImageResource(R.drawable.onepiece)
        binding.ivPostGood.setImageResource(R.drawable.fullheart)
        binding.tvPostCreate.text = "2022-07-28"
        binding.tvPostTemp.text = "27"
        binding.tvPostClassify.text = "원피스"
        binding.tvPostContent.text = "원피스만큼 편한게 없는 것 같아요ㅎㅎ"
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