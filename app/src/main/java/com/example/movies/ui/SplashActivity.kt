package com.example.movies.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.movies.databinding.ActivitySplashBinding
import com.example.utilities.Constants.THREE_SECONDS

class SplashActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadSplash()
    }

    private fun loadSplash() {
        Handler(Looper.getMainLooper()).postDelayed({
            goToDashboard()
        }, THREE_SECONDS)
    }

    private fun goToDashboard(){
        val intent = Intent(this,DashboardActivity::class.java)
        startActivity(intent)
        finish()
    }

}