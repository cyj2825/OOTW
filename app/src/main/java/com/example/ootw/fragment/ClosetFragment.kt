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
import android.widget.TextView
import android.widget.Toast
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
        view.findViewById<TextView>(R.id.tv_Closet_all).setOnClickListener(this)
        view.findViewById<TextView>(R.id.tv_Closet_spring).setOnClickListener(this)
        view.findViewById<TextView>(R.id.tv_Closet_summer).setOnClickListener(this)
        view.findViewById<TextView>(R.id.tv_Closet_autumn).setOnClickListener(this)
        view.findViewById<TextView>(R.id.tv_Closet_winter).setOnClickListener(this)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.btn_Closet_plus -> {
                val intent = Intent(requireContext(), CreatePostActivity::class.java)
                startActivity(intent)
                requireActivity().overridePendingTransition(0, 0)
            }

            R.id.btn_Closet_Calendar -> {
                val intent = Intent(requireContext(), CalendarActivity::class.java)
                startActivity(intent)
                requireActivity().overridePendingTransition(0, 0)
            }
            R.id.tv_Closet_all -> {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.view_Closet, MyFeedAllFragment())
                    .commitNow()
            }
            R.id.tv_Closet_spring -> {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.view_Closet, MyFeedSpringFragment())
                    .commitNow()
            }
            R.id.tv_Closet_summer -> {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.view_Closet, MyFeedSummerFragment())
                    .commitNow()
            }
            R.id.tv_Closet_autumn -> {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.view_Closet, MyFeedAutumnFragment())
                    .commitNow()
            }
            R.id.tv_Closet_winter -> {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.view_Closet, MyFeedWinterFragment())
                    .commitNow()
            }
        }
    }
}