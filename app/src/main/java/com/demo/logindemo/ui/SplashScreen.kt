package com.demo.logindemo.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.demo.logindemo.dataStore.UserPrefrence
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SplashScreen : AppCompatActivity() {
    private lateinit var userPrefrence: UserPrefrence

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_splash_screen)
        userPrefrence = UserPrefrence(this)

        lifecycleScope.launch {
            delay(2000)
            callScreen(userPrefrence.readIsLogin.first())
        }
    }

    private fun callScreen(isLogin:Boolean){
//        if(isLogin){
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//            finish()
//        }else{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
//        }

    }
}