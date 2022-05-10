package com.example.ootw

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this,"4a377cc2e305379c77c04d69aa2ffeef")
    }
}