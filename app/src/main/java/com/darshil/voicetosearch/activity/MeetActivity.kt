package com.darshil.voicetosearch.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.darshil.voicetosearch.R
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class MeetActivity : AppCompatActivity() {
    lateinit var nextButton : ImageView

    lateinit var mAdView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meet)

        initVar()

        onClick()

    }

    private fun onClick() {
        nextButton.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@MeetActivity,SearchTypeActivity::class.java))
        })
    }

    private fun initVar() {
        nextButton = findViewById(R.id.nextButton)

        MobileAds.initialize(this) {}

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
    }
}