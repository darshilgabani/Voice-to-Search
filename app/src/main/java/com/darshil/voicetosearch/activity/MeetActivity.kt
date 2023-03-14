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
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback

class MeetActivity : AppCompatActivity() {
    lateinit var nextButton: ImageView

    lateinit var mAdView: AdView

    lateinit var adRequest: AdRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meet)

        initVar()

        onClick()

    }

    private fun onClick() {
        nextButton.setOnClickListener {
            startActivity(Intent(this@MeetActivity, SearchTypeActivity::class.java))
        }
    }

    private fun initVar() {
        nextButton = findViewById(R.id.nextButton)

        MobileAds.initialize(this) {}

        mAdView = findViewById(R.id.adView)
        adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
    }
}