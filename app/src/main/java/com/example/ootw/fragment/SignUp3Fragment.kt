package com.example.ootw.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.ootw.R

class SignUp3Fragment : Fragment(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_up3, container, false)
        view.findViewById<Button>(R.id.btn_SignUp3_submit).setOnClickListener(this)
        view.findViewById<TextView>(R.id.tv_SignUp3_back).setOnClickListener(this)
        return view
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_SignUp3_submit -> {

            }
            R.id.tv_SignUp3_back -> {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.signup_screen_panel, SignUp2Fragment()).commitNow()
            }
            else -> {
            }
        }
    }
}