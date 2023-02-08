package com.darshil.voicetosearch.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.darshil.voicetosearch.R
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

class ChooseActivity : AppCompatActivity() {
    lateinit var socialMediaButton : CardView
    lateinit var shoppingAppsButton : CardView
    lateinit var musicAppsButton : CardView
    lateinit var googleAppsButton : CardView

    lateinit var mAdView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose)

        initVar()

        onClick()

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

        MobileAds.initialize(this) {}

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
    }
}