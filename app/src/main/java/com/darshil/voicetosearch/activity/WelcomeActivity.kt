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
import com.google.android.gms.ads.admanager.AdManagerAdRequest
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WelcomeActivity : AppCompatActivity() {
    lateinit var startButton: ImageView

    lateinit var mAdView: AdView

    lateinit var adRequest : AdRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        initVar()

        onClick()

    }

    private fun onClick() {
        startButton.setOnClickListener {
            startActivity(Intent(this@WelcomeActivity, MeetActivity::class.java))
        }
    }

    private fun initVar() {
        startButton = findViewById(R.id.startButton)

        MobileAds.initialize(this) {}

        mAdView = findViewById(R.id.adView)
        adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
    }

}