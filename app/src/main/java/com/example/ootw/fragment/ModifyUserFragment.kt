package com.example.ootw.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.example.ootw.PostActivity
import com.example.ootw.R
import com.example.ootw.api.GetProfileServiceCreator
import com.example.ootw.data.response.ResponseGetProfileData
import com.example.ootw.databinding.FragmentHomeBinding
import com.example.ootw.databinding.FragmentModifyUserBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ModifyUserFragment : Fragment() {
    private lateinit var callback: OnBackPressedCallback
    lateinit var binding: FragmentModifyUserBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentModifyUserBinding.inflate(inflater, container, false)

        binding.ivModifyUserProfileImg.setOnClickListener {
            val intent = Intent(requireContext(), PostActivity::class.java)
            startActivity(intent)
            requireActivity().overridePendingTransition(0, 0)
        }

        // 뒤로가기 imageView 클릭시
        binding.ivModifyUserBack.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.main_screen_panel, MyPageFragment())?.commit()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        GetProfileServiceCreator.getProfileService.getProfile(1).enqueue(object :
            Callback<ResponseGetProfileData> {
            override fun onResponse(
                call: Call<ResponseGetProfileData>,
                response: Response<ResponseGetProfileData>
            ) {
                Log.d("datavalue", "회원정보 수정 데이터 값: "+ GetProfileServiceCreator.getProfileService.getProfile(1))
                val data = response.body().toString()
                // message, userProfile 값 null
                Log.d("responsevalue", "modify_user_response 값 => "+ data)
                if (response.isSuccessful) {
                    Log.d("NetworkTest", "modify user success")

                }
            }

            override fun onFailure(call: Call<ResponseGetProfileData>, t: Throwable) {
                Log.d("NetworkTest", "modify user failure")
            }
            })
    }

//  핸드폰 뒤로가기 실행시
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Log.d("TestLog", "백프레스")
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.main_screen_panel, MyPageFragment())?.commit()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

}