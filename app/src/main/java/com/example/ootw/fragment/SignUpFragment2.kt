package com.example.ootw.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.ootw.R

class SignUpFragment2 : Fragment(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_up2, container, false)
        view.findViewById<Button>(R.id.btn_SignUp2_next).setOnClickListener(this)
        view.findViewById<TextView>(R.id.tv_SignUp2_back).setOnClickListener(this)
        return view
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_SignUp2_next -> {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.signup_screen_panel, SignUpFragment3()).commitNow()
            }
            R.id.tv_SignUp2_back -> {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.signup_screen_panel, SignUpFragment1()).commitNow()
            }
            else -> {
            }
        }
    }
}