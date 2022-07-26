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
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.ootw.api.PostWriteServiceCreator
import com.example.ootw.data.request.RequestPostWriteData
import com.example.ootw.data.response.ResponsePostWriteData
import com.example.ootw.databinding.ActivityCreatePostBinding
import com.example.ootw.fragment.BookmarkFragment
import com.example.ootw.fragment.ClosetFragment
import com.example.ootw.spinner.PrimarySpinnerListener
import com.example.ootw.spinner.PrimarySpinnerObservable
import com.example.ootw.spinner.SecondarySpinnerListener
import com.example.ootw.spinner.SecondarySpinnerObservable
import com.github.dhaval2404.imagepicker.ImagePicker
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.InputStream

class CreatePostActivity : AppCompatActivity(), PrimarySpinnerObservable, SecondarySpinnerObservable {
    // 전역 변수로 바인딩 객체 선언
    private var mBinding: ActivityCreatePostBinding? = null
    // 매번 null 체크를 할 필요없이 편의성을 위해 바인딩 변수 재선언
    private val binding get() = mBinding!!

    // 2차 스피너(옷 아이템)
//    private val CLOTHES_TOP_ARRAY = resources.getStringArray(R.array.spinner_clothes_top)
//    private val CLOTHES_BOTTOM_ARRAY = resources.getStringArray(R.array.spinner_clothes_bottom)
//    private val CLOTHES_SHOES_ARRAY = resources.getStringArray(R.array.spinner_clothes_shoes)
    private val CLOTHES_TOP_ARRAY = listOf("선택", "패딩", "두꺼운 니트", "기모 후드티", "야상", "코트", "패딩조끼", "긴팔니트", "후드티", "야구점퍼", "트렌치 코트", "블레이저", "긴팔티", "원피스", "반팔셔츠", "반팔티", "나시")
    private val CLOTHES_BOTTOM_ARRAY = listOf("선택", "긴바지", "슬랙스", "청바지", "치마", "원피스")
    private val CLOTHES_SHOES_ARRAY = listOf("선택", "운동화", "장화", "구두", "부츠")

    private lateinit var categorySpinnerListner: PrimarySpinnerListener
    private lateinit var itemSpinnerListener: SecondarySpinnerListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 바인딩
        mBinding = ActivityCreatePostBinding.inflate(layoutInflater)
        // 스피너 리스너 연결

        setContentView(binding.root)
        Log.d("TestLog", "Create Post Activity!")

        // 이미지 단일 선택 및 편집
        binding.ivCreatePostGallery.setOnClickListener {
            ImagePicker.with(this).galleryOnly().galleryMimeTypes(arrayOf("image/*")).crop()
                .maxResultSize(400, 400).start()
        }

        // 닫기 버튼 누르면 뒤로가기와같이 현재 액티비티 종료
        binding.tvCreatePostCancel.setOnClickListener {
            Log.d("test", "back!!")
            onBackPressed()
        }

        binding.tvCreatePostSubmit.setOnClickListener {
            Log.d("Network", "creatpost")
            // 서버로 보낼 게시글 데이터 생성
            val requestPostWriteData = RequestPostWriteData(
                title = binding.etCreatePostTitle.text.toString(),
                body = binding.etCreatePostContent.text.toString(),
                temp = binding.etTemp.text.toString().toInt(),
                item = binding.spinCreatePostCategory2.toString(),
                imgURL = binding.ivCreatePostGallery.toString()
            )
            // 현재 사용자의 정보를 받아올 것을 명시
            // 서버 통신은 I/O 작업이므로 비동기적으로 받아올 Callback 내부 코드는 나중에 데이터를 받아오고 실행
            val call: Call<ResponsePostWriteData> = PostWriteServiceCreator.postwriteService.postPostWrite(requestPostWriteData)

            // enqueue 함수를 이용해 Call이 비동기 작업 이후 동작한 Callback을 등록할 수 있다
            // 해당 함수 호출은 Callback을 등록만하고 실제 서버 통신을 요청 이후 통신 결과가 나왔을 때 실행
            // object 키워드로 Callback을 구현할 익명 클래스를 생성
            call.enqueue(object : Callback<ResponsePostWriteData> {
                override fun onResponse(
                    call: Call<ResponsePostWriteData>,
                    response: Response<ResponsePostWriteData>
                ) {
                    // 네트워크 통신에 성공한 경우
                    if(response.isSuccessful){
                        Log.d("NetworkTest", "게시물 생성 success")
                        Log.d("datavalue", "data 값 => "+ requestPostWriteData)
                        // val data = response.body().toString()
                        // Log.d("responsevalue", "response 값 => "+ data)
                        // 통신 성공시 toast 메시지
                        Toast.makeText(this@CreatePostActivity, "게시물 생성 완료!!", Toast.LENGTH_SHORT).show()

                        // 통신 성공할 경우 ClosetFragment로 넘어가도록 함
                        finish()
                    }else{
                        // 이곳은 에러 발생할 경우 실행됨
                    }
                }
                // 네트워크 통신 자체가 실패한 경우 해당 함수를 retrofit이 실행!
                override fun onFailure(call: Call<ResponsePostWriteData>, t: Throwable) {
                    Log.d("NetworkTest", "게시물 생성 error!")
                }
            })
        }

        // 날씨 spinner
        binding.spinCreatePostWeather.adapter = ArrayAdapter.createFromResource(this, R.array.spinner_weather, android.R.layout.simple_spinner_item)
        binding.spinCreatePostWeather.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    // 직접입력
                    0 -> {

                    }
                }
            }
        }

        categorySpinnerListner = PrimarySpinnerListener()
        itemSpinnerListener = SecondarySpinnerListener()

        categorySpinnerListner.subscribe(this)
        itemSpinnerListener.subscribe(this)

        binding.spinCreatePostCategory1.onItemSelectedListener = categorySpinnerListner
        binding.spinCreatePostCategory2.onItemSelectedListener = itemSpinnerListener

        // 옷 카테고리(상위 스피너) 셋팅하는 코드
        val arrayAdapter = ArrayAdapter<String>(
            this, android.R.layout.simple_spinner_item, listOf("선택", "상의", "하의", "신발")
        )
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinCreatePostCategory1.adapter = arrayAdapter
    }

    // 1차 스피너(카테고리)로부터 포지션이 오면 해당하는 2차 스피너(아이템) 연결
    override fun updatePrimary(position: Int) {
        Log.d("test", "updatePrimary")
        when (position) {
            0 -> {
            }
            // 상의
            1 -> {
                Log.d("test", "updatePrimary 1")
                val arrayAdapter = ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, CLOTHES_TOP_ARRAY
                )
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinCreatePostCategory2.adapter = arrayAdapter
            }
            // 하의
            2 -> {
                Log.d("test", "updatePrimary 2")
                val arrayAdapter = ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, CLOTHES_BOTTOM_ARRAY
                )
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinCreatePostCategory2.adapter = arrayAdapter
            }
            // 신발
            3 -> {
                Log.d("test", "updatePrimary 3")
                val arrayAdapter = ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, CLOTHES_SHOES_ARRAY
                )
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinCreatePostCategory2.adapter = arrayAdapter
            }
            // error
            else -> {
                Log.d("test", "error")
                return
            }
        }
    }

    // 2차 스피너로부터 포지션이 온다.
    override fun updateSecondary(position: Int) {
        val adapter = binding.spinCreatePostCategory2.adapter
        Log.d("test", "updateSecondary item : ${adapter.getItem(position).toString()}")
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