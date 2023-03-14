package com.darshil.voicetosearch.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.darshil.voicetosearch.R
import com.darshil.voicetosearch.ads.Utils
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import okhttp3.internal.Util

class ChooseActivity : AppCompatActivity() {
    lateinit var socialMediaButton : CardView
    lateinit var shoppingAppsButton : CardView
    lateinit var musicAppsButton : CardView
    lateinit var googleAppsButton : CardView

    lateinit var mAdView : AdView

    lateinit var adRequest: AdRequest

    private var rewardedAd: RewardedAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose)

        initVar()

        onClick()

    }

    override fun onBackPressed() {
        super.onBackPressed()
        Utils().loadRewardedAds(this,getString(R.string.RewardedAdUnitId),adRequest,this)
    }

    private fun onClick() {
        socialMediaButton.setOnClickListener {
            startActivity(Intent(this,SocialMediaActivity::class.java))
        }

        googleAppsButton.setOnClickListener {
            startActivity(Intent(this,GoogleAppsActivity::class.java))
        }

        musicAppsButton.setOnClickListener {
            startActivity(Intent(this,MusicAppsActivity::class.java))
        }

        shoppingAppsButton.setOnClickListener {
            startActivity(Intent(this,ShoppingAppActivity::class.java))
        }

    }

    private fun initVar() {
        socialMediaButton = findViewById(R.id.socialMediaButton)
        shoppingAppsButton = findViewById(R.id.shoppingAppsButton)
        musicAppsButton = findViewById(R.id.musicAppsButton)
        googleAppsButton = findViewById(R.id.googleAppsButton)

        adRequest = AdRequest.Builder().build()

        MobileAds.initialize(this) {}

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
    }
}