package com.example.ootw

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Point
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Base64.NO_WRAP
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import com.example.ootw.constants.mSkyState
import com.example.ootw.constants.mTemperature
import com.example.ootw.databinding.ActivityCreatePostBinding
import com.example.ootw.spinner.PrimarySpinnerListener
import com.example.ootw.spinner.PrimarySpinnerObservable
import com.example.ootw.spinner.SecondarySpinnerListener
import com.example.ootw.spinner.SecondarySpinnerObservable
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.gms.location.FusedLocationProviderClient
import java.io.ByteArrayOutputStream
import java.io.InputStream

class CreatePostActivity : AppCompatActivity(), PrimarySpinnerObservable, SecondarySpinnerObservable {
    // 전역 변수로 바인딩 객체 선언
    private var mBinding: ActivityCreatePostBinding? = null
    // 매번 null 체크를 할 필요없이 편의성을 위해 바인딩 변수 재선언
    private val binding get() = mBinding!!

    // 날씨 자동입력
    private var baseDate = "20220701"  // 발표 일자
    private var baseTime = "1400"      // 발표 시각
    private var curPoint : Point? = null    // 현재 위치의 격자 좌표를 저장할 포인트
    private lateinit var locationClient: FusedLocationProviderClient
    private val TAG = "Weather api"
    @SuppressLint("SetTextI18n", "MissingPermission")
    @RequiresApi(Build.VERSION_CODES.S)

    // 2차 스피너(옷 아이템)
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

        // 닫기 버튼 누르면 뒤로가기와같이 현재 액티비티 종료
        binding.tvCreatePostCancel.setOnClickListener {
            onBackPressed()
        }

        // 완료 버튼
        binding.tvCreatePostSubmit.setOnClickListener {

        }

        // 이미지 단일 선택 및 편집
        binding.ivCreatePostGallery.setOnClickListener {
            ImagePicker.with(this).galleryOnly().galleryMimeTypes(arrayOf("image/*")).crop()
                .maxResultSize(400, 400).start()
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

                    } 1 -> {
                        binding.tvCreatePostSkyState.setText("맑음")
                    } 2 -> {
                        binding.tvCreatePostSkyState.setText("흐림")
                    } 3 -> {
                        binding.tvCreatePostSkyState.setText("구름많음")
                    } 4 -> {
                        binding.tvCreatePostSkyState.setText("비")
                    } 5 -> {
                        binding.tvCreatePostSkyState.setText("바람")
                    } 6 -> {
                        binding.tvCreatePostSkyState.setText("눈")
                    }
                }
            }
        }

        // 날씨 자동 입력
        binding.tvCreatePostWeatherAuto.setOnClickListener {
            // 숫자값 -> 문자열값으로 수정 필요(skyState에 rainType, skyType 두가지 있음)
            when (mSkyState) {
                "1" -> {
                    binding.tvCreatePostSkyState.setText("맑음")
                } "3" -> {
                    binding.tvCreatePostSkyState.setText("구름많음")
                } "4" -> {
                    binding.tvCreatePostSkyState.setText("흐림")
                }
            }
            binding.etCreatePostTemperature.setText(mTemperature)
        }

        // 옷 카테고리(상위 스피너) 셋팅하는 코드
        categorySpinnerListner = PrimarySpinnerListener()
        itemSpinnerListener = SecondarySpinnerListener()

        categorySpinnerListner.subscribe(this)
        itemSpinnerListener.subscribe(this)

        binding.spinCreatePostCategory1.onItemSelectedListener = categorySpinnerListner
        binding.spinCreatePostCategory2.onItemSelectedListener = itemSpinnerListener

        val arrayAdapter = ArrayAdapter<String>(
            this, android.R.layout.simple_spinner_item, listOf("선택", "상의", "하의", "신발")
        )
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinCreatePostCategory1.adapter = arrayAdapter
    }

    // 1차 스피너(카테고리)로부터 포지션이 오면 해당하는 2차 스피너(아이템) 연결
    @RequiresApi(Build.VERSION_CODES.S)
    override fun updatePrimary(position: Int) {
        Log.d("test", "updatePrimary")
        when (position) {
            0 -> {
            }
            // 상의
            1 -> {
                Log.d("test", "updatePrimary 1")
                val arrayAdapter = ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, CLOTHES_TOP_ARRAY)
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinCreatePostCategory2.adapter = arrayAdapter
            }
            // 하의
            2 -> {
                Log.d("test", "updatePrimary 2")
                val arrayAdapter = ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, CLOTHES_BOTTOM_ARRAY)
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinCreatePostCategory2.adapter = arrayAdapter
            }
            // 신발
            3 -> {
                Log.d("test", "updatePrimary 3")
                val arrayAdapter = ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, CLOTHES_SHOES_ARRAY)
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

    // 이미지 단일 선택 및 편집
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode== Activity.RESULT_OK && requestCode== ImagePicker.REQUEST_CODE) {
            binding.ivCreatePostGallery?.setImageURI(data?.data)
        }

        if (requestCode == ImagePicker.REQUEST_CODE && resultCode == Activity.RESULT_OK){
            var currentImageURL = data?.data
            // Base64 인코딩부분
            val ins: InputStream? = currentImageURL?.let {
                applicationContext.contentResolver.openInputStream(it)
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