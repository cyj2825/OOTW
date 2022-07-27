package com.example.ootw

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.ootw.api.SignUpServiceCreator
import com.example.ootw.data.request.RequestSignupData
import com.example.ootw.data.response.ResponseSignupData
import com.example.ootw.databinding.ActivityUserSetUpBinding
import com.example.ootw.spinner.PrimarySpinnerListener
import com.example.ootw.spinner.PrimarySpinnerObservable
import com.example.ootw.spinner.SecondarySpinnerListener
import com.example.ootw.spinner.SecondarySpinnerObservable
import kotlinx.android.synthetic.main.fragment_sign_up1.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class UserSetUpActivity : AppCompatActivity(), PrimarySpinnerObservable, SecondarySpinnerObservable {
    // 전역 변수로 바인딩 객체 선언
    private var mBinding: ActivityUserSetUpBinding? = null
    // 매번 null 체크를 할 필요없이 편의성을 위해 바인딩 변수 재선언
    private val binding get() = mBinding!!
    var result1: String = ""
    var result2: Int = 0
    var result3: Int = 0

    private lateinit var sidoSpinnerListener : PrimarySpinnerListener
    private lateinit var sigunguSpinnerListener : SecondarySpinnerListener

    private val SIGUNGU_SEOUL_ARRAY = listOf("선택", "종로구", "중구", "용산구", "성동구", "광진구", "동대문구", "중랑구", "성북구", "강북구", "도봉구", "노원구", "은평구", "서대문구", "마포구", "양천구", "강서구", "구로구", "금천구", "영등포구", "동작구", "관악구", "서초구", "강남구", "송파구", "강동구")
    private val SIGUNGU_BUSAN_ARRAY = listOf("선택", "중구", "서구", "동구", "영도구", "부산진구", "동래구", "남구", "북구", "강서구", "해운대구", "사하구", "금정구", "연제구", "수영구", "사상구", "기장군")
    private val SIGUNGU_DAEGU_ARRAY = listOf("선택", "중구", "동구", "서구", "남구", "북구", "수성구", "달서구", "달성군")
    private val SIGUNGU_INCHEON_ARRAY = listOf("선택", "중구", "동구", "미추홀구", "연수구", "남동구", "부평구", "계양구", "서구", "강화군", "웅진군")
    private val SIGUNGU_GWANGJU_ARRAY = listOf("선택", "동구", "서구", "남구", "북구", "광산구")
    private val SIGUNGU_DAEJEON_ARRAY = listOf("선택", "동구", "중구", "서구", "유성구", "대덕구")
    private val SIGUNGU_ULSAN_ARRAY = listOf("선택", "중구", "남구", "동구", "북구", "울주군")
    private val SIGUNGU_SEJONG_ARRAY = listOf("세종시")
    private val SIGUNGU_GYEONGGI_ARRAY = listOf("선택", "수원시", "성남시", "의정부시", "안양시", "부천시", "광명시", "동두천시", "평택시", "안산시", "고양시", "과천시", "구리시", "남양주시", "오산시", "시흥시", "군포시", "의왕시", "하남시", "용인시", "파주시", "이천시", "안성시", "김포시", "화성시", "광주시", "양주시", "포천시", "여주시", "연천군", "가평군", "양평군")
    private val SIGUNGU_GANGWON_ARRAY = listOf("선택", "춘천시", "원주시", "강릉시", "동해시", "태백시", "속초시", "삼척시", "홍천군", "횡성군", "영월군", "평창군", "정선군", "철원군", "화천군", "양구군", "인제군", "고성군", "양양군")
    private val SIGUNGU_CHUNGBUK_ARRAY = listOf("선택", "청주시", "보은군", "옥천군", "영동군", "증편군", "진천군", "괴산군", "음성군", "단양군")
    private val SIGUNGU_CHUNGNAM_ARRAY = listOf("선택", "천안시", "공주시", "보령시", "아산시", "서산시", "논산시", "계룡시", "당진시", "금산군", "부여군", "서천군", "청양군", "홍성군", "예산군", "태안군")
    private val SIGUNGU_JEONBUK_ARRAY = listOf("선택", "전주시", "군산시", "익산시", "정읍시", "남원시", "김제시", "완주군", "진안군", "무주군", "장수군", "임실군", "순창군", "고창군", "부안군")
    private val SIGUNGU_JEONNAM_ARRAY = listOf("선택", "목포시", "여수시", "순천시", "나주시", "광양시", "담양군", "곡성군", "구례군", "고흥군", "보성군", "화순군", "장흥군", "강진군", "해남군", "영암군", "무안군", "함평군", "영광군", "장성군", "완도군", "진도군", "신안군")
    private val SIGUNGU_GYEONGBUK_ARRAY = listOf("선택", "포항시", "경주시", "김천시", "안동시", "구미시", "영주시", "영천시", "상주시", "문경시", "경산시", "군위군", "의성군", "청송군", "영양군", "영덕군", "천도군", "고령군", "성주군", "칠곡군", "예천군", "봉화군", "울진군", "울릉군")
    private val SIGUNGU_GYEONGNAM_ARRAY = listOf("선택", "창원시", "진주시", "통영시", "사천시", "김해시", "밀양시", "거제시", "양산시", "의령군", "함안군", "창녕군", "고성군", "남해군", "하동군", "산청군", "함양군", "거창군", "합천군")
    private val SIGUNGU_JEJU_ARRAY = listOf("선택", "제주시", "서귀포시")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 바인딩
        mBinding = ActivityUserSetUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener{
            Log.d("TestLog", "user set up")
            startActivity(Intent(this, LoginActivity::class.java))
        }
        binding.rgSignUpGender.setOnCheckedChangeListener { radioGroup, i ->
            when(i){
                R.id.rb_men -> result1 = "남성"
                R.id.rb_women -> result1 = "여성"
            }
        }
        binding.rgSignUp3Cold.setOnCheckedChangeListener { radioGroup, i ->
            when(i){
                R.id.rb_SignUp3_cold1 -> result2 = 1
                R.id.rb_SignUp3_cold2 -> result2 = 2
                R.id.rb_SignUp3_cold3 -> result2 = 3
                R.id.rb_SignUp3_cold4 -> result2 = 4
                R.id.rb_SignUp3_cold5 -> result2 = 5
            }
        }
        binding.rgSignUp3Heat.setOnCheckedChangeListener { radioGroup, i ->
            when(i){
                R.id.rb_SignUp3_heat1 -> result3 = 1
                R.id.rb_SignUp3_heat2 -> result3 = 2
                R.id.rb_SignUp3_heat3 -> result3 = 3
                R.id.rb_SignUp3_heat4 -> result3 = 4
                R.id.rb_SignUp3_heat5 -> result3 = 5
            }
        }
        binding.btnSignUp1Next.setOnClickListener {
            Log.d("userset", "성공???")
            // 서버로 보낼 회원가입 데이터 생성
            val requestSignupData = RequestSignupData(
                loginId = binding.etSignUp1Id.text.toString(),
                email = binding.etSignUpEmail1.text.toString()+"@"+binding.etSignUpEmail2.text.toString(),
                password = binding.etSignUp1Pw.text.toString(),
                birth = binding.btnSignUp2Pickdate.text.toString(),
                nickname = binding.etSignUpName.text.toString(),
                gender = result1,
                coldSensitivity = result2,
                hotSensitivity = result3,
                // todo: 나중에 수정 필요
                area ="서울특별시",
                // area = binding.spinSignUp3Region1.toString(),
                areaDetail = "중구 회현동",
                // area_detail = binding.spinSignUp3Region2.toString(),
                // todo: 이부분 URL로 받아야할 것 같은데 어떻게 해야 할지 고민할 필요있음
                profileImg = "https://ziriootw.s3.ap-northeast-2.amazonaws.com/original/1658373729961%C3%AB%C2%B0%C2%B1%C3%AC%C2%97%C2%94%C3%AB%C2%93%C2%9C.jpg"
                // profile_img = binding.ivSignUpProfileImg.toString()
            )
            // 현재 사용자의 정보를 받아올 것을 명시!
            // 서버 통신은 I/O 작업이므로 비동기적으로 받아올 Callback 내부 코드는 나중에 데이터를 받아오고 실행
            val call: Call<ResponseSignupData> = SignUpServiceCreator.signupService.postSignup(requestSignupData)

            // enqueue 함수를 이용해 Call이 비동기 작업 이후 동작할 Callback을 등록할 수 있다.
            // 해당 함수 호출은 Callback을 등록만하고 실제 서버 통신을 요청 이후 통신 결과가 나왔을때 실행
            // object 키워드로 Callback을 구현할 익명 클래스를 생성
            call.enqueue(object : Callback<ResponseSignupData>{
                // 네트워크 통신 Response가 있는 경우 해당 함수를 retrofit이 호출
                override fun onResponse(
                    call: Call<ResponseSignupData>,
                    response: Response<ResponseSignupData>
                ) {
                    Log.d("Network2", "값 => "+ requestSignupData)
                    val data = response.body().toString()
                    Log.d("responsevalue", "response 값~!! => "+ data)
                    // 네트워크 통신에 성공한 경우 실행
                    if(response.isSuccessful){
                        Log.d("NetworkTest2", "success")
                        // 회원가입 완료 및 통신 성공시 toast 메시지
                        Toast.makeText(this@UserSetUpActivity, "회원가입 완료!!", Toast.LENGTH_SHORT).show()
                        // 통신 성공할 경우 LoginActivity로 넘어가도록 함
                        val nextIntent = Intent(this@UserSetUpActivity, LoginActivity::class.java)
                        startActivity(nextIntent)
                    }else{
                        Log.d("NetworkTest2", "여긴가?")
                    }
                }
                // 네트워크 통신 자체가 실패한 경우 해당 함수를 retrofit이 실행!
                override fun onFailure(call: Call<ResponseSignupData>, t: Throwable) {
                    Log.d("NetworkTest2", "error!")
                }
            })
            // startActivity(Intent(this, LoginActivity::class.java))
        }

        // 이메일 도메인 spinner
        var spinner: Spinner = binding.spinSignUp2Email
        spinner.adapter = ArrayAdapter.createFromResource(this, com.example.ootw.R.array.spinner_email, android.R.layout.simple_spinner_item)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var etEmail: EditText = binding.etSignUpEmail2
                when (position) {
                    0 -> {
                        etEmail.setText("")
                    } 1 -> {
                    etEmail.setText("gmail.com")
                    } 2 -> {
                    etEmail.setText("naver.com")
                    } 3 -> {
                    etEmail.setText("daum.net")
                    } 4 -> {
                    etEmail.setText("hanmail.net")
                    } 5 -> {
                    etEmail.setText("nate.com")
                    }
                }
            }
        }
        sidoSpinnerListener = PrimarySpinnerListener()
        sigunguSpinnerListener = SecondarySpinnerListener()

        sidoSpinnerListener.subscribe(this)
        sigunguSpinnerListener.subscribe(this)

        binding.spinSignUp3Region1.onItemSelectedListener = sidoSpinnerListener
        binding.spinSignUp3Region2.onItemSelectedListener = sigunguSpinnerListener

        // 지역 시도(상위 스피너) 셋팅하는 코드
        val arrayAdapter = ArrayAdapter<String>(
            this, android.R.layout.simple_spinner_item, listOf("선택", "서울특별시", "부산광역시", "대구시", "인천광역시", "광주시", "대전시", "울산시", "세종시", "경기도", "강원도", "충청북도", "충청남도", "전라북도", "전라남도", "경상북도", "경상남도", "제주도")
        )
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spin_SignUp3_region1.adapter = arrayAdapter
    }

    // 이부분은 주희님께 물어보자 7/26
    fun openDatePicker(view: View?){
        binding.btnSignUp2Pickdate.setOnClickListener {
            var formatDate = SimpleDateFormat("yyyy - MM - dd", Locale.KOREA)
            val getDate: Calendar = Calendar.getInstance()
        }
    }

    override fun updatePrimary(position: Int) {
        Log.d("test", "updatePrimary")
        when (position) {
            0 -> {
            }
            // 서울
            1 -> {
                Log.d("test", "updatePrimary 1")
                val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, SIGUNGU_SEOUL_ARRAY)
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spin_SignUp3_region2.adapter = arrayAdapter
            }
            // 부산
            2 -> {
                Log.d("test", "updatePrimary 1")
                val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, SIGUNGU_BUSAN_ARRAY)
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spin_SignUp3_region2.adapter = arrayAdapter
            }
            // 대구
            3 -> {
                Log.d("test", "updatePrimary 1")
                val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, SIGUNGU_DAEGU_ARRAY)
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spin_SignUp3_region2.adapter = arrayAdapter
            }
            // 인천
            4 -> {
                Log.d("test", "updatePrimary 1")
                val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, SIGUNGU_INCHEON_ARRAY)
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spin_SignUp3_region2.adapter = arrayAdapter
            }
            // 광주
            5 -> {
                Log.d("test", "updatePrimary 1")
                val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, SIGUNGU_GWANGJU_ARRAY)
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spin_SignUp3_region2.adapter = arrayAdapter
            }
            // 대전
            6 -> {
                Log.d("test", "updatePrimary 1")
                val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, SIGUNGU_DAEJEON_ARRAY)
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spin_SignUp3_region2.adapter = arrayAdapter
            }
            // 울산
            7 -> {
                Log.d("test", "updatePrimary 1")
                val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, SIGUNGU_ULSAN_ARRAY)
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spin_SignUp3_region2.adapter = arrayAdapter
            }
            // 세종
            8 -> {
                Log.d("test", "updatePrimary 1")
                val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, SIGUNGU_SEJONG_ARRAY)
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spin_SignUp3_region2.adapter = arrayAdapter
            }
            // 경기
            9 -> {
                Log.d("test", "updatePrimary 1")
                val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, SIGUNGU_GYEONGGI_ARRAY)
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spin_SignUp3_region2.adapter = arrayAdapter
            }
            // 강원
            10 -> {
                Log.d("test", "updatePrimary 1")
                val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, SIGUNGU_GANGWON_ARRAY)
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spin_SignUp3_region2.adapter = arrayAdapter
            }
            // 충북
            11 -> {
                Log.d("test", "updatePrimary 1")
                val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, SIGUNGU_CHUNGBUK_ARRAY)
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spin_SignUp3_region2.adapter = arrayAdapter
            }
            // 충남
            12 -> {
                Log.d("test", "updatePrimary 1")
                val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, SIGUNGU_CHUNGNAM_ARRAY)
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spin_SignUp3_region2.adapter = arrayAdapter
            }
            // 전북
            13 -> {
                Log.d("test", "updatePrimary 1")
                val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, SIGUNGU_JEONBUK_ARRAY)
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spin_SignUp3_region2.adapter = arrayAdapter
            }
            // 전남
            14 -> {
                Log.d("test", "updatePrimary 1")
                val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, SIGUNGU_JEONNAM_ARRAY)
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spin_SignUp3_region2.adapter = arrayAdapter
            }
            // 경북
            15 -> {
                Log.d("test", "updatePrimary 1")
                val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, SIGUNGU_GYEONGBUK_ARRAY)
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spin_SignUp3_region2.adapter = arrayAdapter
            }
            // 경남
            16 -> {
                Log.d("test", "updatePrimary 1")
                val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, SIGUNGU_GYEONGNAM_ARRAY)
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spin_SignUp3_region2.adapter = arrayAdapter
            }
            // 제주
            17 -> {
                Log.d("test", "updatePrimary 1")
                val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, SIGUNGU_JEJU_ARRAY)
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spin_SignUp3_region2.adapter = arrayAdapter
            }
            // error
            else -> {
                Log.d("test", "error")
                return
            }
        }
    }

    override fun updateSecondary(position: Int) {
        val adapter = spin_SignUp3_region2.adapter
        Log.d("test", "updateSecondary item : ${adapter.getItem(position).toString()}")
    }
}