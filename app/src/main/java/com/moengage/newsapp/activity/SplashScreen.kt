package com.moengage.newsapp.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.moengage.newsapp.R


class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)
        Handler().postDelayed(Runnable {
            val i = Intent(this@SplashScreen, MainActivity::class.java)
            startActivity(i) //start new activity
            finish()
        }, 3500)
    }
}