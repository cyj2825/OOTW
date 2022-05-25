package com.example.ootw.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.example.ootw.LoginActivity
import com.example.ootw.R

class SignUpFragment1 : Fragment(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_up1, container, false)
        view.findViewById<Button>(R.id.btn_SignUp1_next).setOnClickListener(this)
        view.findViewById<TextView>(R.id.tv_SignUp1_back).setOnClickListener(this)
        return view
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_SignUp1_next -> {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.signup_screen_panel, SignUpFragment2()).commitNow()
            }
            R.id.tv_SignUp1_back -> {
                activity?.let{
                    val intent = Intent(context, LoginActivity::class.java)
                    startActivity(intent)
                }
            }
            else -> {
            }
        }
    }
}