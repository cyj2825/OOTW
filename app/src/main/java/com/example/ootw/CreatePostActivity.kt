package com.example.ootw

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.ootw.databinding.ActivityCreatePostBinding
import com.example.ootw.databinding.ActivityFindIdBinding
import com.example.ootw.databinding.ActivityLoginBinding
import com.github.dhaval2404.imagepicker.ImagePicker

class CreatePostActivity : AppCompatActivity() {
    // 전역 변수로 바인딩 객체 선언
    private var mBinding: ActivityCreatePostBinding? = null
    // 매번 null 체크를 할 필요없이 편의성을 위해 바인딩 변수 재선언
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 바인딩
        mBinding = ActivityCreatePostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("TestLog", "Create Post Activity!")

        binding.btnCreatePostPickImg.setOnClickListener {
            ImagePicker.with(this).galleryOnly().galleryMimeTypes(arrayOf("image/*")).crop()
                .maxResultSize(400, 400).start()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode== Activity.RESULT_OK && requestCode== ImagePicker.REQUEST_CODE) {
            binding.ivCreatePostGallery?.setImageURI(data?.data)
        }

    }
}