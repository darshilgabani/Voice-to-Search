package com.darshil.voicetosearch.activity

import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
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

class SocialMediaActivity : AppCompatActivity() {
    private lateinit var wpButton: CardView
    private lateinit var twitterButton: CardView
    private lateinit var fbButton: CardView
    private lateinit var instagramButton: CardView
    private lateinit var linkedinButton: CardView
    private lateinit var snapButton: CardView
    private lateinit var pinterestButton: CardView
    private lateinit var discordButton: CardView

    lateinit var mAdView : AdView

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var sharedPreferencesEditor: SharedPreferences.Editor

    private var language: String? = null
    private var requestCode: Int? = null

    private lateinit var activityResult: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_socialmedia)

        initVar()

        onClick()

        activityResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                onResult(it)
            }


    }

    private fun onResult(activityResult: ActivityResult) {
        requestCode = sharedPreferences.getInt("socialRequestCode", 0)


        val spokenData =
            activityResult.data?.getStringArrayListExtra("android.speech.extra.RESULTS")?.get(0)

        if (spokenData != null) {
            when (requestCode) {
                101 -> {
//          Whatsapp
                    try {
                        Intent().apply {
                            this.action = Intent.ACTION_SEND
                            this.putExtra(Intent.EXTRA_TEXT, spokenData)
                            this.type = "text/plain"
                            this.setPackage("com.whatsapp")
                            startActivity(this)
                        }
                    } catch (e: Exception) {
                        Toast.makeText(
                            this@SocialMediaActivity,
                            "WhatsApp is unavailable on your device.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }

                102 -> {
//          Twitter
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("twitter://search?query=$spokenData")

                    if (intent.resolveActivity(packageManager) != null) {
                        startActivity(intent)
                    } else {
                        intent.data = Uri.parse("https://twitter.com/search?q=$spokenData")
                        startActivity(intent)
                    }

                }

                103 -> {
//          Facebook
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("fb://search?q=$spokenData")

                    if (intent.resolveActivity(packageManager) != null) {
                        startActivity(intent)
                    } else {
                        intent.data = Uri.parse("https://www.facebook.com/search/$spokenData")
                        startActivity(intent)
                    }

                }

                104 -> {
//          Instagram
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("https://www.instagram.com/explore/tags/$spokenData")

                    if (intent.resolveActivity(packageManager) != null) {
                        startActivity(intent)
                    } else {
                        intent.data = Uri.parse("https://www.instagram.com/explore/tags/$spokenData")
                        startActivity(intent)
                    }
                }

                105 -> {
//          Linkedin
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("linkedin://search?keywords=$spokenData")

                    if (intent.resolveActivity(packageManager) != null) {
                        startActivity(intent)
                    } else {
                        intent.data = Uri.parse("https://www.linkedin.com/search/results/all/?keywords=$spokenData")
                        startActivity(intent)
                    }

                }

                106 -> {
//          Snapchat
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("com.snapchat.android$spokenData")

                    if (intent.resolveActivity(packageManager) != null) {
                        startActivity(intent)
                    } else {
                        intent.data = Uri.parse("https://snapchat.com/add/$spokenData")
                        startActivity(intent)
                    }

                }

                107 -> {
//          Pinterest
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("pinterest://search/?q=$spokenData")

                    if (intent.resolveActivity(packageManager) != null) {
                        startActivity(intent)
                    } else {
                        intent.data = Uri.parse("https://www.pinterest.com/search/?q=$spokenData")
                        startActivity(intent)
                    }

                }

                108 -> {
//          Discord
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("com.discord$spokenData")

                    if (intent.resolveActivity(packageManager) != null) {
                        startActivity(intent)
                    } else {
                        intent.data = Uri.parse("https://discord.gg/$spokenData")
                        startActivity(intent)
                    }

                }
            }
        }

    }
    
    private fun onClick() {

        val button = FunctionalityClass().getButton(this)

        wpButton.setOnClickListener {

            if (button == 0) {
                FunctionalityClass().voice(this, 101, "socialRequestCode", activityResult)
            } else if (button == 1) {
                FunctionalityClass().alertDialog(this, R.drawable.wp, "WhatsApp") { data ->
                    try {
                        Intent().apply {
                            this.action = Intent.ACTION_SEND
                            this.putExtra(Intent.EXTRA_TEXT, data)
                            this.type = "text/plain"
                            this.setPackage("com.whatsapp")
                            startActivity(this)
                        }
                    } catch (e: Exception) {
                        Toast.makeText(
                            this@SocialMediaActivity,
                            "WhatsApp is unavailable on your device.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }
            }

        }

        twitterButton.setOnClickListener {
            if (button == 0) {
                FunctionalityClass().voice(this, 102, "socialRequestCode", activityResult)
            } else if (button == 1) {
                FunctionalityClass().alertDialog(this, R.drawable.twitter, "Twitter") { data ->
                    
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("twitter://search?query=$data")

                    if (intent.resolveActivity(packageManager) != null) {
                        startActivity(intent)
                    } else {
                        intent.data = Uri.parse("https://twitter.com/search?q=$data")
                        startActivity(intent)
                    }

                }
            }
        }

        fbButton.setOnClickListener {
            if (button == 0) {
                FunctionalityClass().voice(this, 103, "socialRequestCode", activityResult)
            } else if (button == 1) {
                FunctionalityClass().alertDialog(this, R.drawable.fb, "Facebook") { data ->
                    
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("fb://search?q=$data")

                    if (intent.resolveActivity(packageManager) != null) {
                        startActivity(intent)
                    } else {
                        intent.data = Uri.parse("https://www.facebook.com/search/$data")
                        startActivity(intent)
                    }
                    
                }
            }

        }

        instagramButton.setOnClickListener {
            if (button == 0) {
                FunctionalityClass().voice(this, 104, "socialRequestCode", activityResult)
            } else if (button == 1) {
                FunctionalityClass().alertDialog(this, R.drawable.insta, "Instagram") { data ->
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("https://www.instagram.com/explore/tags/$data")

                    if (intent.resolveActivity(packageManager) != null) {
                        startActivity(intent)
                    } else {
                        intent.data = Uri.parse("https://www.instagram.com/explore/tags/$data")
                        startActivity(intent)
                    }
                    
                }
            }
        }

        linkedinButton.setOnClickListener {
            if (button == 0) {
                FunctionalityClass().voice(this, 105, "socialRequestCode", activityResult)
            } else if (button == 1) {
                FunctionalityClass().alertDialog(this, R.drawable.linkedin, "LinkedIn") { data ->
                    
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("linkedin://search?keywords=$data")

                    if (intent.resolveActivity(packageManager) != null) {
                        startActivity(intent)
                    } else {
                        intent.data = Uri.parse("https://www.linkedin.com/search/results/all/?keywords=$data")
                        startActivity(intent)
                    }

                }
            }

        }

        snapButton.setOnClickListener {
            if (button == 0) {
                FunctionalityClass().voice(this, 106, "socialRequestCode", activityResult)
            } else if (button == 1) {
                FunctionalityClass().alertDialog(this, R.drawable.snap, "Snapchat") { data ->
                    
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("com.snapchat.android$data")

                    if (intent.resolveActivity(packageManager) != null) {
                        startActivity(intent)
                    } else {
                        intent.data = Uri.parse("https://snapchat.com/add/$data")
                        startActivity(intent)
                    }
                    

                }
            }

        }

        pinterestButton.setOnClickListener {

            if (button == 0) {
                FunctionalityClass().voice(this, 107, "socialRequestCode", activityResult)
            } else if (button == 1) {
                FunctionalityClass().alertDialog(this, R.drawable.pinterest, "Pinterest") { data ->
                    
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("pinterest://search/?q=$data")

                    if (intent.resolveActivity(packageManager) != null) {
                        startActivity(intent)
                    } else {
                        intent.data = Uri.parse("https://www.pinterest.com/search/?q=$data")
                        startActivity(intent)
                    }

                }
            }


        }

        discordButton.setOnClickListener {

            if (button == 0) {
                FunctionalityClass().voice(this, 108, "socialRequestCode", activityResult)
            } else if (button == 1) {
                FunctionalityClass().alertDialog(this, R.drawable.discord, "Discord") { data ->
                    
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("com.discord$data")

                    if (intent.resolveActivity(packageManager) != null) {
                        startActivity(intent)
                    } else {
                        intent.data = Uri.parse("https://discord.gg/$data")
                        startActivity(intent)
                    }
                   

                }
            }

        }

    }
    
    private fun initVar() {
        wpButton = findViewById(R.id.wpButton)
        twitterButton = findViewById(R.id.twitterButton)
        fbButton = findViewById(R.id.fbButton)
        instagramButton = findViewById(R.id.instagramButton)
        linkedinButton = findViewById(R.id.linkedinButton)
        snapButton = findViewById(R.id.snapButton)
        pinterestButton = findViewById(R.id.pinterestButton)
        discordButton = findViewById(R.id.discordButton)

        sharedPreferences = getSharedPreferences("My Language", MODE_PRIVATE)
        sharedPreferencesEditor = sharedPreferences.edit()

        language = sharedPreferences.getString("language", "")
        requestCode = sharedPreferences.getInt("socialRequestCode", 0)

        MobileAds.initialize(this) {}

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

    }

}