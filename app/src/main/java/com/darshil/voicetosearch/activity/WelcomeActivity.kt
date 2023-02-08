package com.darshil.voicetosearch.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.darshil.voicetosearch.R
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

class WelcomeActivity : AppCompatActivity() {
    lateinit var startButton : ImageView

    lateinit var mAdView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        initVar()

        onClick()

    }

    private fun onClick() {
        startButton.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@WelcomeActivity,MeetActivity::class.java))
        })
    }

    private fun initVar() {
        startButton = findViewById(R.id.startButton)

        MobileAds.initialize(this) {}

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
    }
}