package com.example.ootw.fragment

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.ootw.LoginActivity
import com.example.ootw.R
import com.example.ootw.spinner.PrimarySpinnerListener
import com.example.ootw.spinner.PrimarySpinnerObservable
import com.example.ootw.spinner.SecondarySpinnerListener
import com.example.ootw.spinner.SecondarySpinnerObservable
import kotlinx.android.synthetic.main.fragment_sign_up1.*
import kotlinx.android.synthetic.main.fragment_sign_up1.spin_SignUp3_region1
import kotlinx.android.synthetic.main.fragment_sign_up3.*
import java.text.SimpleDateFormat
import java.util.*

// 일단 여기부분은 필요 없어요! 7/26 + SignUp2Fragment, SignUp3Fragment도 필요없습니다
class SignUp1Fragment : Fragment(), View.OnClickListener, PrimarySpinnerObservable, SecondarySpinnerObservable {

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
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_up1, container, false)
        view.findViewById<Button>(R.id.btn_SignUp1_next).setOnClickListener(this)
        view.findViewById<TextView>(R.id.iv_back).setOnClickListener(this)
        view.findViewById<Button>(R.id.btn_SignUp2_pickdate).setOnClickListener(this)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 이메일 도메인 spinner
        var spinner: Spinner = requireView().findViewById(R.id.spin_SignUp2_email)
        spinner.adapter = ArrayAdapter.createFromResource(requireActivity(), com.example.ootw.R.array.spinner_email, android.R.layout.simple_spinner_item)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var etEmail: EditText = requireView().findViewById(R.id.et_SignUp2_email2)
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

        view.findViewById<Spinner>(R.id.spin_SignUp3_region1).onItemSelectedListener = sidoSpinnerListener
        view.findViewById<Spinner>(R.id.spin_SignUp3_region2).onItemSelectedListener = sigunguSpinnerListener

        // 지역 시도(상위 스피너) 셋팅하는 코드
        val arrayAdapter = ArrayAdapter<String>(
            requireActivity(), android.R.layout.simple_spinner_item, listOf("선택", "서울시", "부산시", "대구시", "인천시", "광주시", "대전시", "울산시", "세종시", "경기도", "강원도", "충청북도", "충청남도", "전라북도", "전라남도", "경상북도", "경상남도", "제주도")
        )
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spin_SignUp3_region1.adapter = arrayAdapter
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_SignUp1_next -> {
                activity?.let{
                    val intent = Intent(context, LoginActivity::class.java)
                    startActivity(intent)
                }
                // parentFragmentManager.beginTransaction()
                //    .replace(R.id.signup_screen_panel, SignUp2Fragment()).commitNow()
            }
            R.id.iv_back -> {
                activity?.let{
                    val intent = Intent(context, LoginActivity::class.java)
                    startActivity(intent)
                }
            }
            R.id.btn_SignUp2_pickdate -> {
                var formatDate = SimpleDateFormat("yyyy - MM - dd", Locale.KOREA)
                val getDate: Calendar = Calendar.getInstance()
//                val datePicker = DatePickerDialog(this, android.R.style.Theme_Holo_Dialog_MinWidth, DatePickerDialog.OnDateSetListener{ datePicker, i, i2, i3 ->
//                    val selectDate = Calendar.getInstance()
//                    selectDate.set(Calendar.YEAR, i)
//                    selectDate.set(Calendar.MONTH, i2)
//                    selectDate.set(Calendar.DAY_OF_MONTH, i3)
//                    val date = formatDate.format(selectDate.time)
//                    R.id.btn_SignUp2_pickdate.text = date
//                }, getDate.get(Calendar.YEAR), getDate.get(Calendar.MONTH), getDate.get(Calendar.DAY_OF_MONTH))
//                datePicker.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//                datePicker.show()
            }
            else -> {
            }
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
                val arrayAdapter = ArrayAdapter<String>(requireActivity(), android.R.layout.simple_spinner_item, SIGUNGU_SEOUL_ARRAY)
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spin_SignUp3_region2.adapter = arrayAdapter
            }
            // 부산
            2 -> {
                Log.d("test", "updatePrimary 1")
                val arrayAdapter = ArrayAdapter<String>(requireActivity(), android.R.layout.simple_spinner_item, SIGUNGU_BUSAN_ARRAY)
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spin_SignUp3_region2.adapter = arrayAdapter
            }
            // 대구
            3 -> {
                Log.d("test", "updatePrimary 1")
                val arrayAdapter = ArrayAdapter<String>(requireActivity(), android.R.layout.simple_spinner_item, SIGUNGU_DAEGU_ARRAY)
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spin_SignUp3_region2.adapter = arrayAdapter
            }
            // 인천
            4 -> {
                Log.d("test", "updatePrimary 1")
                val arrayAdapter = ArrayAdapter<String>(requireActivity(), android.R.layout.simple_spinner_item, SIGUNGU_INCHEON_ARRAY)
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spin_SignUp3_region2.adapter = arrayAdapter
            }
            // 광주
            5 -> {
                Log.d("test", "updatePrimary 1")
                val arrayAdapter = ArrayAdapter<String>(requireActivity(), android.R.layout.simple_spinner_item, SIGUNGU_GWANGJU_ARRAY)
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spin_SignUp3_region2.adapter = arrayAdapter
            }
            // 대전
            6 -> {
                Log.d("test", "updatePrimary 1")
                val arrayAdapter = ArrayAdapter<String>(requireActivity(), android.R.layout.simple_spinner_item, SIGUNGU_DAEJEON_ARRAY)
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spin_SignUp3_region2.adapter = arrayAdapter
            }
            // 울산
            7 -> {
                Log.d("test", "updatePrimary 1")
                val arrayAdapter = ArrayAdapter<String>(requireActivity(), android.R.layout.simple_spinner_item, SIGUNGU_ULSAN_ARRAY)
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spin_SignUp3_region2.adapter = arrayAdapter
            }
            // 세종
            8 -> {
                Log.d("test", "updatePrimary 1")
                val arrayAdapter = ArrayAdapter<String>(requireActivity(), android.R.layout.simple_spinner_item, SIGUNGU_SEJONG_ARRAY)
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spin_SignUp3_region2.adapter = arrayAdapter
            }
            // 경기
            9 -> {
                Log.d("test", "updatePrimary 1")
                val arrayAdapter = ArrayAdapter<String>(requireActivity(), android.R.layout.simple_spinner_item, SIGUNGU_GYEONGGI_ARRAY)
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spin_SignUp3_region2.adapter = arrayAdapter
            }
            // 강원
            10 -> {
                Log.d("test", "updatePrimary 1")
                val arrayAdapter = ArrayAdapter<String>(requireActivity(), android.R.layout.simple_spinner_item, SIGUNGU_GANGWON_ARRAY)
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spin_SignUp3_region2.adapter = arrayAdapter
            }
            // 충북
            11 -> {
                Log.d("test", "updatePrimary 1")
                val arrayAdapter = ArrayAdapter<String>(requireActivity(), android.R.layout.simple_spinner_item, SIGUNGU_CHUNGBUK_ARRAY)
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spin_SignUp3_region2.adapter = arrayAdapter
            }
            // 충남
            12 -> {
                Log.d("test", "updatePrimary 1")
                val arrayAdapter = ArrayAdapter<String>(requireActivity(), android.R.layout.simple_spinner_item, SIGUNGU_CHUNGNAM_ARRAY)
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spin_SignUp3_region2.adapter = arrayAdapter
            }
            // 전북
            13 -> {
                Log.d("test", "updatePrimary 1")
                val arrayAdapter = ArrayAdapter<String>(requireActivity(), android.R.layout.simple_spinner_item, SIGUNGU_JEONBUK_ARRAY)
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spin_SignUp3_region2.adapter = arrayAdapter
            }
            // 전남
            14 -> {
                Log.d("test", "updatePrimary 1")
                val arrayAdapter = ArrayAdapter<String>(requireActivity(), android.R.layout.simple_spinner_item, SIGUNGU_JEONNAM_ARRAY)
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spin_SignUp3_region2.adapter = arrayAdapter
            }
            // 경북
            15 -> {
                Log.d("test", "updatePrimary 1")
                val arrayAdapter = ArrayAdapter<String>(requireActivity(), android.R.layout.simple_spinner_item, SIGUNGU_GYEONGBUK_ARRAY)
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spin_SignUp3_region2.adapter = arrayAdapter
            }
            // 경남
            16 -> {
                Log.d("test", "updatePrimary 1")
                val arrayAdapter = ArrayAdapter<String>(requireActivity(), android.R.layout.simple_spinner_item, SIGUNGU_GYEONGNAM_ARRAY)
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spin_SignUp3_region2.adapter = arrayAdapter
            }
            // 제주
            17 -> {
                Log.d("test", "updatePrimary 1")
                val arrayAdapter = ArrayAdapter<String>(requireActivity(), android.R.layout.simple_spinner_item, SIGUNGU_JEJU_ARRAY)
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