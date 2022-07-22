package com.example.ootw.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Point
import android.location.Geocoder
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.example.ootw.CalendarActivity
import com.example.ootw.PostActivity
import com.example.ootw.adapter.WeatherAdapter
import com.example.ootw.component.Common
import com.example.ootw.data.ITEM
import com.example.ootw.data.ModelWeather
import com.example.ootw.data.WEATHER
import com.example.ootw.databinding.FragmentHomeBinding
import com.example.ootw.network.WeatherObject
import com.google.android.gms.location.*
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {
    private var baseDate = "20220701"  // 발표 일자
    private var baseTime = "1400"      // 발표 시각
    private var curPoint : Point? = null    // 현재 위치의 격자 좌표를 저장할 포인트
    private lateinit var locationClient: FusedLocationProviderClient
    private val TAG = "Weather api"

    lateinit var binding: FragmentHomeBinding
    @SuppressLint("SetTextI18n", "MissingPermission")
    @RequiresApi(Build.VERSION_CODES.S)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.btnHomeItem1.setOnClickListener {
            val intent = Intent(requireContext(), PostActivity::class.java)
            startActivity(intent)
            requireActivity().overridePendingTransition(0, 0)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get permission
        val permissionList = arrayOf<String>(
            // 위치 권한
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
        )

        // 권한 요청
        requestPermissions(permissionList, 1)

        // 오늘 날짜 텍스트뷰 설정
        binding.tvHomeDate.text = SimpleDateFormat("MM월 dd일", Locale.getDefault()).format(Calendar.getInstance().time)

        requestLocation()

        // <새로고침> 버튼 누를 때 위치 정보 & 날씨 정보 다시 가져오기 => 확인 완료
//        binding.btnRefresh.setOnClickListener {
//            requestLocation()
//            Log.d(TAG,"btnclick1")
//        }
    }

    // 날씨 가져와서 설정하기
    private fun setWeather(nx : Int, ny : Int) {
        // 준비 단계 : base_date(발표 일자), base_time(발표 시각)
        // 현재 날짜, 시간 정보 가져오기
        val cal = Calendar.getInstance()
        baseDate = SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(cal.time) // 현재 날짜
        val timeH = SimpleDateFormat("HH", Locale.getDefault()).format(cal.time) // 현재 시각
        val timeM = SimpleDateFormat("HH", Locale.getDefault()).format(cal.time) // 현재 분
        // API 가져오기 적당하게 변환
        baseTime = Common().getBaseTime(timeH, timeM)
        // 현재 시각이 00시이고 45분 이하여서 baseTime이 2330이면 어제 정보 받아오기
        if (timeH == "00" && baseTime == "2330") {
            cal.add(Calendar.DATE, -1).toString()
            baseDate = SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(cal.time)
        }
        Log.d(TAG,"btnclick2")
        // 날씨 정보 가져오기
        // (한 페이지 결과 수 = 60, 페이지 번호 = 1, 응답 자료 형식-"JSON", 발표 날싸, 발표 시각, 예보지점 좌표)
        val call = WeatherObject.getRetrofitService().getWeather(60, 1, "JSON", baseDate, baseTime, nx, ny)

        // 비동기적으로 실행하기
        call.enqueue(object : retrofit2.Callback<WEATHER> {
            // 응답 성공 시
            override fun onResponse(call: Call<WEATHER>, response: Response<WEATHER>) {
                if (response.isSuccessful) {
                    Log.d(TAG,"btnclick3")
                    // 날씨 정보 가져오기
                    val it: List<ITEM> = response.body()!!.response.body.items.item

                    // 현재 시각부터 1시간 뒤의 날씨 6개를 담을 배열
                    val weatherArr = arrayOf(ModelWeather(), ModelWeather(), ModelWeather(), ModelWeather(), ModelWeather(), ModelWeather())

                    // 배열 채우기
                    var index = 0
                    val totalCount = response.body()!!.response.body.totalCount - 1
                    for (i in 0..totalCount) {
                        index %= 6
                        when(it[i].category) {
                            "PTY" -> weatherArr[index].rainType = it[i].fcstValue     // 강수 형태
                            "REH" -> weatherArr[index].humidity = it[i].fcstValue     // 습도
                            "SKY" -> weatherArr[index].skyState = it[i].fcstValue          // 하늘 상태
                            "T1H" -> weatherArr[index].temperature = it[i].fcstValue         // 기온
                            else -> continue
                        }
                        index++
                    }

                    weatherArr[0].fcstTime = "지금"
                    // 각 날짜 배열 시간 설정
                    for (i in 1..5) weatherArr[i].fcstTime = it[i].fcstTime

                    // 리사이클러 뷰에 데이터 연결
                    binding.rvHomeWeather.adapter = WeatherAdapter(weatherArr)

                    // 토스트 띄우기
//                    Toast.makeText(getActivity(), it[0].fcstDate + ", " + it[0].fcstTime + "의 날씨 정보입니다.", Toast.LENGTH_SHORT).show()
                }
            }

            // 응답 실패 시
            override fun onFailure(call: Call<WEATHER>, t: Throwable) {
                Log.d(TAG,"btnclick4")
//                binding.tvError.text = "api fail : " +  t.message.toString() + "\n 다시 시도해주세요."
//                binding.tvError.visibility = View.VISIBLE
                Log.d("api fail", t.message.toString())
            }
        })
    }

    // 내 현재 위치의 위경도를 격자 좌표로 변환하여 해당 위치의 날씨정보 설정하기
    @SuppressLint("MissingPermission")
    private fun requestLocation() {
        Log.d(TAG,"btnclick5")
        locationClient = LocationServices.getFusedLocationProviderClient(this.requireActivity())

        try {
            // 나의 현재 위치 요청
            val locationRequest = LocationRequest.create()
            locationRequest.run {
                priority = LocationRequest.PRIORITY_HIGH_ACCURACY
                interval = 60 * 1000    // 요청 간격(1초)
            }
            val locationCallback = object : LocationCallback() {
                // 요청 결과
                override fun onLocationResult(p0: LocationResult) {
                    Log.d(TAG,"btnclick6")
                    p0.let {
                        for (location in it.locations) {
                            val lat = location.latitude
                            val lon = location.longitude
                            // 현재 위치의 위경도를 격자 좌표로 변환
                            curPoint = Common().dfsXyConv(lat, lon)

                            // 오늘 날짜 텍스트뷰 설정
//                            binding.tvDate.text = SimpleDateFormat("MM월 dd일", Locale.getDefault()).format(Calendar.getInstance().time) + " 날씨"
                            // nx, ny지점의 날씨 가져와서 설정하기
                            setWeather(curPoint!!.x, curPoint!!.y)

//                            사용자의 현재 위치 행정구역(시군구) 역지오코딩
                            val city = Geocoder(context).getFromLocation(lat, lon, 1)
                            binding.tvHomeLocation.setText(city[0].subLocality).toString()
                            Log.d(TAG,"btnclick7")
                        }
                    }
                }
            }

            // 내 위치 실시간으로 감지 => 확인 완료
            Looper.myLooper()?.let {
                locationClient.requestLocationUpdates(locationRequest, locationCallback,
                    it)
            }

        } catch (e : SecurityException) {
            e.printStackTrace()
        }
    }


}