package com.darshil.voicetosearch.activity

import android.app.SearchManager
import android.content.ComponentName
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.darshil.voicetosearch.R
import com.darshil.voicetosearch.model.FunctionalityClass
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

class MusicAppsActivity : AppCompatActivity() {
    private lateinit var spotifyButton: CardView
    private lateinit var gaanaButton: CardView
    private lateinit var ytMusicButton: CardView
    private lateinit var appleMusicButton: CardView
    private lateinit var amazonMusicButton: CardView
    private lateinit var jioSaavanButton: CardView
    private lateinit var hungamaButton: CardView
    private lateinit var wynkButton: CardView

    lateinit var mAdView : AdView

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var sharedPreferencesEditor: SharedPreferences.Editor

    private var language: String? = null
    private var requestCode: Int? = null

    private lateinit var activityResult: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_musicapps)

        initVar()

        onClick()

        activityResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                onResult(it)
            }

    }

    private fun onResult(activityResult: ActivityResult) {
        requestCode = sharedPreferences.getInt("musicRequestCode", 0)

        val spokenData =
            activityResult.data?.getStringArrayListExtra("android.speech.extra.RESULTS")?.get(0)

        if (spokenData != null) {
            when (requestCode) {
                301 -> {
//          Spotify
                    try {
                        val intent = Intent(Intent.ACTION_SEARCH)
                        intent.setPackage("com.spotify.music")
                        intent.putExtra(SearchManager.QUERY, spokenData)
                        startActivity(intent)
                    }catch (e : Exception){
                        startActivity(Intent("android.intent.action.VIEW", Uri.parse("https://open.spotify.com/search/results/$spokenData")))
                    }

                }

                302 -> {
//          Gaana
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("https://gaana.com/search/$spokenData")
                    startActivity(intent)

                }

                303 -> {
//          YouTube Music
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("https://music.youtube.com/search?q=$spokenData")
                    startActivity(intent)
                }

                304 -> {
//          Apple Music
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("https://music.apple.com/us/search?term=$spokenData")
                    startActivity(intent)
                }

                305 -> {
//          Amazon Music
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("https://music.amazon.com/search/$spokenData")
                    startActivity(intent)
                }

                306 -> {
//          JioSaavan
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("https://www.jiosaavn.com/search/$spokenData")
                    startActivity(intent)
                }

                307 -> {
//          Hungama
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("https://hungama.com/search?q=$spokenData")
                    startActivity(intent)
                }

                308 -> {
//          Wynk Music
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("https://wynk.in/music/search/$spokenData")
                    startActivity(intent)

                }
            }
        }

    }

    private fun onClick() {

        val button = FunctionalityClass().getButton(this)

        spotifyButton.setOnClickListener {

            if (button == 0) {
                FunctionalityClass().voice(this, 301, "musicRequestCode", activityResult)
            } else if (button == 1) {
                FunctionalityClass().alertDialog(this, R.drawable.spotify, "Spotify") { data ->

                    try {
                        val intent = Intent(Intent.ACTION_SEARCH)
                        intent.setPackage("com.spotify.music")
                        intent.putExtra(SearchManager.QUERY, data)
                        startActivity(intent)
                    }catch (e : Exception){
                        startActivity(Intent("android.intent.action.VIEW", Uri.parse("https://open.spotify.com/search/results/$data")))
                    }

                }
            }

        }

        gaanaButton.setOnClickListener {
            if (button == 0) {
                FunctionalityClass().voice(this, 302, "musicRequestCode", activityResult)
            } else if (button == 1) {
                FunctionalityClass().alertDialog(this, R.drawable.gaana, "Gaana") { data ->

                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("https://gaana.com/search/$data")
                    startActivity(intent)

                }
            }
        }

        ytMusicButton.setOnClickListener {
            if (button == 0) {
                FunctionalityClass().voice(this, 303, "musicRequestCode", activityResult)
            } else if (button == 1) {
                FunctionalityClass().alertDialog(this, R.drawable.ytmusic, "YouTube Music") { data ->
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data = Uri.parse("https://music.youtube.com/search?q=$data")
                        startActivity(intent)
                }
            }

        }

        appleMusicButton.setOnClickListener {
            if (button == 0) {
                FunctionalityClass().voice(this, 304, "musicRequestCode", activityResult)
            } else if (button == 1) {
                FunctionalityClass().alertDialog(this, R.drawable.applemusic, "Apple Music") { data ->

                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("https://music.apple.com/us/search?term=$data")
                    startActivity(intent)

                }
            }
        }

        amazonMusicButton.setOnClickListener {
            if (button == 0) {
                FunctionalityClass().voice(this, 305, "musicRequestCode", activityResult)
            } else if (button == 1) {
                FunctionalityClass().alertDialog(this, R.drawable.amazonmusic, "Amazon Music") { data ->

                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("https://music.amazon.com/search/$data")
                    startActivity(intent)

                }
            }

        }

        jioSaavanButton.setOnClickListener {
            if (button == 0) {
                FunctionalityClass().voice(this, 306, "musicRequestCode", activityResult)
            } else if (button == 1) {
                FunctionalityClass().alertDialog(this, R.drawable.jiosaavn, "JioSaavan") { data ->

                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("https://www.jiosaavn.com/search/$data")
                    startActivity(intent)
                    
                }
            }

        }

        hungamaButton.setOnClickListener {

            if (button == 0) {
                FunctionalityClass().voice(this, 307, "musicRequestCode", activityResult)
            } else if (button == 1) {
                FunctionalityClass().alertDialog(this, R.drawable.hungama, "Hungama") { data ->

                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("https://hungama.com/search?q=$data")
                    startActivity(intent)

                }
            }


        }

        wynkButton.setOnClickListener {

            if (button == 0) {
                FunctionalityClass().voice(this, 308, "musicRequestCode", activityResult)
            } else if (button == 1) {
                FunctionalityClass().alertDialog(this, R.drawable.wynk, "Wynk Music") { data ->
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("https://wynk.in/music/search/$data")
                    startActivity(intent)
                }
            }

        }

    }

    private fun initVar() {
        spotifyButton = findViewById(R.id.spotifyButton)
        gaanaButton = findViewById(R.id.gaanaButton)
        ytMusicButton = findViewById(R.id.ytMusicButton)
        appleMusicButton = findViewById(R.id.appleMusicButton)
        amazonMusicButton = findViewById(R.id.amazonMusicButton)
        jioSaavanButton = findViewById(R.id.jioSaavanButton)
        hungamaButton = findViewById(R.id.hungamaButton)
        wynkButton = findViewById(R.id.wynkButton)

        sharedPreferences = getSharedPreferences("My Language", MODE_PRIVATE)
        sharedPreferencesEditor = sharedPreferences.edit()

        language = sharedPreferences.getString("language", "")
        requestCode = sharedPreferences.getInt("musicRequestCode", 0)

        MobileAds.initialize(this) {}

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

    }
}