package com.example.ootw

import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Base64.NO_WRAP
import android.util.Log
import android.widget.ImageView
import com.example.ootw.databinding.ActivityCreatePostBinding
import com.example.ootw.databinding.ActivityFindIdBinding
import com.example.ootw.databinding.ActivityLoginBinding
import com.example.ootw.fragment.MyFeedAllFragment
import com.github.dhaval2404.imagepicker.ImagePicker
import java.io.ByteArrayOutputStream
import java.io.InputStream

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

//        이미지 단일 선택 및 편집
        binding.ivCreatePostGallery.setOnClickListener {
            ImagePicker.with(this).galleryOnly().galleryMimeTypes(arrayOf("image/*")).crop()
                .maxResultSize(400, 400).start()
        }

//      닫기 버튼 누르면 뒤로가기와 같이 현재 액티비티 종료
        binding.tvCreatePostCancel.setOnClickListener {
            onBackPressed()
        }

        binding.tvCreatePostSubmit.setOnClickListener {

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

//    이미지 단일 선택 및 편집
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

//        if(resultCode== Activity.RESULT_OK && requestCode== ImagePicker.REQUEST_CODE) {
//            binding.ivCreatePostGallery?.setImageURI(data?.data)
//        }

        if (requestCode == ImagePicker.REQUEST_CODE && resultCode == Activity.RESULT_OK){
            var currentImageURL = data?.data
            // Base64 인코딩부분
            val ins: InputStream? = currentImageURL?.let {
                applicationContext.contentResolver.openInputStream(
                    it
                )
            }
            val img: Bitmap = BitmapFactory.decodeStream(ins)
            ins?.close()
            val resized = Bitmap.createScaledBitmap(img, 256, 256, true)
            val byteArrayOutputStream = ByteArrayOutputStream()
            resized.compress(Bitmap.CompressFormat.JPEG, 60, byteArrayOutputStream)
            val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
            val outStream = ByteArrayOutputStream()
            val res: Resources = resources
            var profileImageBase64 = Base64.encodeToString(byteArray, NO_WRAP)
            // 여기까지 인코딩 끝
            Log.d("TestLog", "Image: "+ profileImageBase64)

            // 이미지 뷰에 선택한 이미지 출력
            binding.ivCreatePostGallery?.setImageURI(currentImageURL)
            try {
                //이미지 선택 후 처리
            }catch (e: Exception){
                e.printStackTrace()
            }
        } else{
            Log.d("ActivityResult", "something wrong")
        }

    }
}