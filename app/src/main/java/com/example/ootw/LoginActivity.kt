package com.example.ootw

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.ootw.databinding.ActivityLoginBinding
import com.example.ootw.network.APIInterface
import com.example.ootw.network.ResponseDC
import com.kakao.sdk.auth.LoginClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthErrorCause
import com.kakao.sdk.user.UserApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {
    // 전역 변수로 바인딩 객체 선언
    private var mBinding: ActivityLoginBinding? = null
    // 매번 null 체크를 할 필요없이 편의성을 위해 바인딩 변수 재선언
    private val binding get() = mBinding!!

    // 서버 주소
    val url = "ec2-15-164-49-16.ap-northeast-2.compute.amazonaws.com:80?temp=10"
    // retrofit 연결
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    var server = retrofit.create(APIInterface::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 바인딩
        mBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvFindId.setOnClickListener {
            Log.d("TestLog", "find id")
            startActivity(Intent(this, FindIdActivity::class.java))
        }
        binding.tvFindPw.setOnClickListener {
            Log.d("TestLog", "find pw")
            startActivity(Intent(this, FindPwActivity::class.java))
        }
        binding.tvSignup.setOnClickListener {
            Log.d("TestLog", "sign up")
            startActivity(Intent(this, SignUpActivity::class.java))
        }
        binding.btnMainLogin.setOnClickListener {
            Log.d("TestLog", "main")
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    // 액티비티가 파괴될 때..
    override fun onDestroy() {
        // onDestroy 에서 binding class 인스턴스 참조를 정리해주어야 한다.
        mBinding = null
        super.onDestroy()
    }

        // 지금부터 주석 처리된 것은 카카오 로그인 관련 코드
        // 로그인 정보 확인
//        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
//            if (error != null) {
//                Toast.makeText(this, "토큰 정보 보기 실패", Toast.LENGTH_SHORT).show()
//            }
//            else if (tokenInfo != null) {
//                Toast.makeText(this, "토큰 정보 보기 성공", Toast.LENGTH_SHORT).show()
//                val intent = Intent(this, MainActivity::class.java)
//                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
//                finish()
//            }
//        }

        // val KeyHash = Utility.getKeyHash(this)
        // Log.d("Hash", KeyHash)

//        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
//            if (error != null) {
//                when {
//                    error.toString() == AuthErrorCause.AccessDenied.toString() -> {
//                        Toast.makeText(this, "접근이 거부 됨(동의 취소)", Toast.LENGTH_SHORT).show()
//                    }
//                    error.toString() == AuthErrorCause.InvalidClient.toString() -> {
//                        Toast.makeText(this, "유효하지 않은 앱", Toast.LENGTH_SHORT).show()
//                    }
//                    error.toString() == AuthErrorCause.InvalidGrant.toString() -> {
//                        Toast.makeText(this, "인증 수단이 유효하지 않아 인증할 수 없는 상태", Toast.LENGTH_SHORT).show()
//                    }
//                   error.toString() == AuthErrorCause.InvalidRequest.toString() -> {
//                        Toast.makeText(this, "요청 파라미터 오류", Toast.LENGTH_SHORT).show()
//                    }
//                    error.toString() == AuthErrorCause.InvalidScope.toString() -> {
//                        Toast.makeText(this, "유효하지 않은 scope ID", Toast.LENGTH_SHORT).show()
//                   }
//                    error.toString() == AuthErrorCause.Misconfigured.toString() -> {
//                        Toast.makeText(this, "설정이 올바르지 않음(android key hash)", Toast.LENGTH_SHORT).show()
//                    }
//                    error.toString() == AuthErrorCause.ServerError.toString() -> {
//                        Toast.makeText(this, "서버 내부 에러", Toast.LENGTH_SHORT).show()
//                    }
//                    error.toString() == AuthErrorCause.Unauthorized.toString() -> {
//                        Toast.makeText(this, "앱이 요청 권한이 없음", Toast.LENGTH_SHORT).show()
//                    }
//                    else -> { // Unknown
//                        Toast.makeText(this, "기타 에러", Toast.LENGTH_SHORT).show()
//                    }
//                }
//           }
//            else if (token != null) {
//                Toast.makeText(this, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show()
//                val intent = Intent(this, MainActivity::class.java)
//                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
//                finish()
//            }
//        }

        // 로그아웃, 회원 탈퇴 버튼도 따로 만들어야 하나..? (MainActivity에?)
        // binding.kakaoLoginButton = findViewById<ImageButton>(R.id.kakao_login_button)
        // 로그인 버튼
//        binding.kakaoLoginButton.setOnClickListener {
//            if(LoginClient.instance.isKakaoTalkLoginAvailable(this)){
//                LoginClient.instance.loginWithKakaoTalk(this, callback = callback)
//            }else{
//                LoginClient.instance.loginWithKakaoAccount(this, callback = callback)
//            }
//        }
}