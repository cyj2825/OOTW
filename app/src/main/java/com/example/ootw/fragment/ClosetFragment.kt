package com.example.ootw.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import com.example.ootw.*
import com.example.ootw.api.GetProfileService
import com.example.ootw.api.GetProfileServiceCreator
import com.example.ootw.api.LogoutService
import com.example.ootw.api.LogoutServiceCreator
import com.example.ootw.data.response.ResponseGetProfileData
import com.example.ootw.data.response.ResponseLogoutData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClosetFragment : Fragment(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_closet, container, false)
        view.findViewById<Button>(R.id.btn_Closet_plus).setOnClickListener(this)
        view.findViewById<ImageButton>(R.id.btn_Closet_Calendar).setOnClickListener(this)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // test용 아이디 입력
        val call: Call<ResponseGetProfileData> = GetProfileServiceCreator.getProfileService.getProfile("1")

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_Closet_plus -> {
//                parentFragmentManager.beginTransaction()
//                    .replace(R.id.main_screen_panel, CreatePostFragment()).commitNow()
                val intent = Intent(requireContext(), CreatePostActivity::class.java)
                startActivity(intent)
                requireActivity().overridePendingTransition(0, 0)
            }

            R.id.btn_Closet_Calendar -> {
                val intent = Intent(requireContext(), CalendarActivity::class.java)
                startActivity(intent)
                requireActivity().overridePendingTransition(0, 0)
            }
            R.id.btn_logout -> {
                // todo: 로그아웃 서버 통신
                // LogoutServiceCreator.logoutService.getRequest().enqueue(object : Callback<ResponseLogoutData>{
                    // override fun onResponse(
                        // call: Call<ResponseLogoutData>,
                        // response: Response<ResponseLogoutData>
                    // ) {
                        // Log.d("logout결과", "성공!!")
                        // val intent = Intent(requireContext(), LoginActivity::class.java)
                        // startActivity(intent)
                    // }

                    // override fun onFailure(call: Call<ResponseLogoutData>, t: Throwable) {
                        // Log.d("logout결과", "실패ㅠㅠ")
                    // }
                // })
            }
        }
    }
}