package com.darshil.voicetosearch.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.darshil.voicetosearch.R
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { true }

        val scheduler = Executors.newSingleThreadScheduledExecutor()
        scheduler.schedule({
            startActivity(Intent(this, WelcomeActivity::class.java))
            finish()
        }, 2000, TimeUnit.MILLISECONDS)

    }
}