package com.darshil.voicetosearch.activity

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.darshil.voicetosearch.R
import com.darshil.voicetosearch.ads.Utils
import com.darshil.voicetosearch.model.FunctionalityClass
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.rewarded.RewardedAd

class GoogleAppsActivity : AppCompatActivity() {
    private lateinit var gmailButton: CardView
    private lateinit var chromeButton: CardView
    private lateinit var driveButton: CardView
    private lateinit var photosButton: CardView
    private lateinit var ytButton: CardView
    private lateinit var playStoreButton: CardView
    private lateinit var googleButton: CardView
    private lateinit var mapButton: CardView

    lateinit var adRequest: AdRequest

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var sharedPreferencesEditor: SharedPreferences.Editor

    private var language: String? = null
    private var requestCode: Int? = null

    private lateinit var activityResult: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_googleapps)

        initVar()

        onClick()

        activityResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                onResult(it)
            }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Utils().loadInterstitialAds(this,getString(R.string.InterstitialAdUnitId),adRequest,this)
    }

    private fun onResult(activityResult: ActivityResult) {
        requestCode = sharedPreferences.getInt("googleRequestCode", 0)

        val spokenData =
            activityResult.data?.getStringArrayListExtra("android.speech.extra.RESULTS")?.get(0)

        if (spokenData != null) {
            when (requestCode) {
                201 -> {
//          Gmail
                    val intent = Intent(Intent.ACTION_SENDTO)
                    intent.data = Uri.parse("mailto:$spokenData")
                    startActivity(intent)
                }

                202 -> {
//          Chrome
                    val url = "https://www.google.com/search?q=$spokenData"
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(url)
                    intent.setPackage("com.android.chrome")
                    startActivity(intent)
                }

                203 -> {
//          Drive
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data =
                        Uri.parse("https://drive.google.com/drive/u/0/search?q=$spokenData")
                    startActivity(intent)
                }

                204 -> {
//          Photos
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("https://photos.google.com/search/$spokenData")
                    startActivity(intent)
                }

                205 -> {
//          YouTube
                    val intent = Intent(Intent.ACTION_SEARCH)
                    intent.setPackage("com.google.android.youtube")
                    intent.putExtra("query", spokenData)
                    startActivity(intent)
                }

                206 -> {
//          Play Store
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("market://search?q=$spokenData")
                    intent.setPackage("com.android.vending")
                    startActivity(intent)
                }

                207 -> {
//          Google
                    val url = "https://www.google.com/search?q=$spokenData"
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(url)
                    intent.setPackage("com.android.chrome")
                    startActivity(intent)
                }

                208 -> {
//          Maps
                    val uri = "geo:0,0?q=$spokenData"
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                    intent.setPackage("com.google.android.apps.maps")
                    startActivity(intent)

                }
            }
        }

    }

    private fun onClick() {

        val button = FunctionalityClass().getButton(this)

        gmailButton.setOnClickListener {

            if (button == 0) {
                FunctionalityClass().voice(this, 201, "googleRequestCode", activityResult)
            } else if (button == 1) {
                FunctionalityClass().alertDialog(this, R.drawable.gmail, "Gmail") { data ->
                    val intent = Intent(Intent.ACTION_SENDTO)
                    intent.data = Uri.parse("mailto:$data")
                    startActivity(intent)
                }
            }

        }

        chromeButton.setOnClickListener {
            if (button == 0) {
                FunctionalityClass().voice(this, 202, "googleRequestCode", activityResult)
            } else if (button == 1) {
                FunctionalityClass().alertDialog(this, R.drawable.chrome, "Chrome") { data ->

                    val url = "https://www.google.com/search?q=$data"
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(url)
                    intent.setPackage("com.android.chrome")
                    startActivity(intent)

                }
            }
        }

        driveButton.setOnClickListener {
            if (button == 0) {
                FunctionalityClass().voice(this, 203, "googleRequestCode", activityResult)
            } else if (button == 1) {
                FunctionalityClass().alertDialog(this, R.drawable.gdrive, "Drive") { data ->

                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("https://drive.google.com/drive/u/0/search?q=$data")
                    startActivity(intent)

                }
            }

        }

        photosButton.setOnClickListener {
            if (button == 0) {
                FunctionalityClass().voice(this, 204, "googleRequestCode", activityResult)
            } else if (button == 1) {
                FunctionalityClass().alertDialog(this, R.drawable.gphotos, "Photos") { data ->

                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("https://photos.google.com/search/$data")
                    startActivity(intent)

                }
            }
        }

        ytButton.setOnClickListener {
            if (button == 0) {
                FunctionalityClass().voice(this, 205, "googleRequestCode", activityResult)
            } else if (button == 1) {
                FunctionalityClass().alertDialog(this, R.drawable.gphotos, "Photos") { data ->

                    val intent = Intent(Intent.ACTION_SEARCH)
                    intent.setPackage("com.google.android.youtube")
                    intent.putExtra("query", data)
                    startActivity(intent)
                }
            }

        }

        playStoreButton.setOnClickListener {
            if (button == 0) {
                FunctionalityClass().voice(this, 206, "googleRequestCode", activityResult)
            } else if (button == 1) {
                FunctionalityClass().alertDialog(this, R.drawable.gphotos, "Photos") { data ->

                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("market://search?q=$data")
                    intent.setPackage("com.android.vending")
                    startActivity(intent)
                }
            }

        }

        googleButton.setOnClickListener {

            if (button == 0) {
                FunctionalityClass().voice(this, 207, "googleRequestCode", activityResult)
            } else if (button == 1) {
                FunctionalityClass().alertDialog(this, R.drawable.gphotos, "Photos") { data ->

                    val url = "https://www.google.com/search?q=$data"
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(url)
                    intent.setPackage("com.android.chrome")
                    startActivity(intent)
                }
            }


        }

        mapButton.setOnClickListener {

            if (button == 0) {
                FunctionalityClass().voice(this, 208, "googleRequestCode", activityResult)
            } else if (button == 1) {
                FunctionalityClass().alertDialog(this, R.drawable.gphotos, "Photos") { data ->
                    val uri = "geo:0,0?q=$data"
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                    intent.setPackage("com.google.android.apps.maps")
                    startActivity(intent)
                }
            }

        }

    }


    private fun initVar() {
        gmailButton = findViewById(R.id.gmailButton)
        chromeButton = findViewById(R.id.chromeButton)
        driveButton = findViewById(R.id.driveButton)
        photosButton = findViewById(R.id.photosButton)
        ytButton = findViewById(R.id.ytButton)
        playStoreButton = findViewById(R.id.playStoreButton)
        googleButton = findViewById(R.id.googleButton)
        mapButton = findViewById(R.id.mapButton)

        sharedPreferences = getSharedPreferences("My Language", MODE_PRIVATE)
        sharedPreferencesEditor = sharedPreferences.edit()

        adRequest = AdRequest.Builder().build()

        language = sharedPreferences.getString("language", "")
        requestCode = sharedPreferences.getInt("googleRequestCode", 0)

        MobileAds.initialize(this) {}

    }

}