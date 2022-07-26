package com.example.ootw

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import com.example.ootw.api.LoginServiceCreator
import com.example.ootw.data.request.RequestLoginData
import com.example.ootw.data.response.ResponseLoginData
import com.example.ootw.databinding.ActivityLoginBinding
import com.kakao.sdk.auth.LoginClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthErrorCause
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    // 전역 변수로 바인딩 객체 선언
    private var mBinding: ActivityLoginBinding? = null
    // 매번 null 체크를 할 필요없이 편의성을 위해 바인딩 변수 재선언
    private val binding get() = mBinding!!

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
            startActivity(Intent(this, UserSetUpActivity::class.java))
            // startActivity(Intent(this, SignUpActivity::class.java))
        }
        binding.btnMainLogin.setOnClickListener {
            Log.d("TestLog", "main")

            // 서버로 보낼 로그인 데이터 생성
            val requestLoginData = RequestLoginData(
                email = binding.etMainId.text.toString(),
                password = binding.etMainPw.text.toString()
            )

            // 현재 사용자의 정보를 받아올 것을 명시
            // 서버 통신은 I/O 작업이므로 비동기적으로 받아올 Callback 내부 코드는 나중에 데이터를 받아오고 실행
            val call: Call<ResponseLoginData> = LoginServiceCreator.loginService.postLogin(requestLoginData)

            // enqueue 함수를 이용해 Call이 비동기 작업 이후 동작한 Callback을 등록할 수 있다
            // 해당 함수 호출은 Callback을 등록만하고 실제 서버 통신을 요청 이후 통신 결과가 나왔을 때 실행
            // object 키워드로 Callback을 구현할 익명 클래스를 생성
            call.enqueue(object : Callback<ResponseLoginData>{
                override fun onResponse(
                    call: Call<ResponseLoginData>,
                    response: Response<ResponseLoginData>
                ) {
                    // 네트워크 통신에 성공한 경우
                    if(response.isSuccessful){
                        Log.d("NetworkTest", "success")
                        Log.d("datavalue", "data 값 => "+ requestLoginData)
                        val data = response.body().toString()
                        Log.d("responsevalue", "response 값 => "+ data)
                        // 회원가입 된 아이디와 비밀번호일 경우 해당 if문 실행
                        if(data == "ResponseLoginData(message=login success)"){
                            // 통신 성공시 toast 메시지
                            Toast.makeText(this@LoginActivity, "로그인 완료!!", Toast.LENGTH_SHORT).show()

                            // 통신 성공할 경우 MainActivity로 넘어가도록 함
                            val nextIntent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(nextIntent)
                        }

                    }else{
                        // 이곳은 에러 발생할 경우 실행됨
                    }
                }
                // 네트워크 통신 자체가 실패한 경우 해당 함수를 retrofit이 실행!
                override fun onFailure(call: Call<ResponseLoginData>, t: Throwable) {
                    Log.d("NetworkTest", "error!")
                }
            })
            //startActivity(Intent(this, MainActivity::class.java))
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