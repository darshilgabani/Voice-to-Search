package com.darshil.voicetosearch.activity

import android.content.ActivityNotFoundException
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

class ShoppingAppActivity : AppCompatActivity() {
    private lateinit var amazonButton: CardView
    private lateinit var flipkartButton: CardView
    private lateinit var myntraButton: CardView
    private lateinit var meeshoButton: CardView
    private lateinit var tataCliqButton: CardView
    private lateinit var ajioButton: CardView
    private lateinit var snapDealButton: CardView
    private lateinit var jioMartButton: CardView

    lateinit var mAdView : AdView

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var sharedPreferencesEditor: SharedPreferences.Editor

    private var language: String? = null
    private var requestCode: Int? = null

    private lateinit var activityResult: ActivityResultLauncher<Intent>
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shoppingapp)

        initVar()

        onClick()

        activityResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                onResult(it)
            }

    }

    private fun onResult(activityResult: ActivityResult) {
        requestCode = sharedPreferences.getInt("shoppingRequestCode", 0)

        val spokenData =
            activityResult.data?.getStringArrayListExtra("android.speech.extra.RESULTS")?.get(0)

        if (spokenData != null) {
            when (requestCode) {
                401 -> {
//          Amazon
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("https://www.amazon.com/s/ref=nb_sb_noss_2?url=search-alias%3Daps&field-keywords=$spokenData")
                    startActivity(intent)

                }

                402 -> {
//          Flipkart
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("https://www.flipkart.com/search?q=$spokenData&otracker=search&otracker1=search&marketplace=FLIPKART&as-show=on&as=off")
                    startActivity(intent)
                }

                403 -> {
//          Myntra
                    try {
                        val intent = Intent("android.intent.action.WEB_SEARCH")
                        intent.setClassName("com.android.myntra", "com.android.myntra.SearchActivity")
                        intent.putExtra("query", spokenData)
                        startActivity(intent)
                    } catch (e: ActivityNotFoundException) {
                        startActivity(Intent("android.intent.action.VIEW", Uri.parse("https://www.myntra.com/$spokenData")))
                    }

                }

                404 -> {
//          Meesho
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://meesho.com/search?q=$spokenData&searchType=manual&searchIdentifier=text_search")
                        )
                    )

                }

                405 -> {
//          Tata CLiQ
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://www.tatacliq.com/search/?searchCategory=all&text=$spokenData")
                        )
                    )

                }

                406 -> {
//          AJIO
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://ajio.com/search?q=$spokenData")
                        )
                    )

                }

                407 -> {
//          Snapdeal
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://www.snapdeal.com/search?keyword=$spokenData")
                        )
                    )

                }

                408 -> {
//          JioMart
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://www.jiomart.com/catalogsearch/result?q=$spokenData")
                        )
                    )

                }
            }
        }

    }

    private fun onClick() {

        val button = FunctionalityClass().getButton(this)

        amazonButton.setOnClickListener {

            if (button == 0) {
                FunctionalityClass().voice(this, 401, "shoppingRequestCode", activityResult)
            } else if (button == 1) {
                FunctionalityClass().alertDialog(this, R.drawable.amazon, "Amazon") { data ->

                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("https://www.amazon.com/s/ref=nb_sb_noss_2?url=search-alias%3Daps&field-keywords=$data")
                    startActivity(intent)

                }
            }

        }

        flipkartButton.setOnClickListener {
            if (button == 0) {
                FunctionalityClass().voice(this, 402, "shoppingRequestCode", activityResult)
            } else if (button == 1) {
                FunctionalityClass().alertDialog(this, R.drawable.flipkart, "Flipkart") { data ->

                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("https://www.flipkart.com/search?q=$data&otracker=search&otracker1=search&marketplace=FLIPKART&as-show=on&as=off")
                    startActivity(intent)

                }
            }
        }

        myntraButton.setOnClickListener {
            if (button == 0) {
                FunctionalityClass().voice(this, 403, "shoppingRequestCode", activityResult)
            } else if (button == 1) {
                FunctionalityClass().alertDialog(this, R.drawable.myntra, "Myntra") { data ->

                    try {
                        val intent = Intent(Intent.ACTION_WEB_SEARCH)
                        intent.setClassName("com.android.myntra", "com.android.myntra.SearchActivity")
                        intent.putExtra("query", data)
                        startActivity(intent)
                    } catch (e: ActivityNotFoundException) {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.myntra.com/$data")))
                    }
                }
            }

        }

        meeshoButton.setOnClickListener {
            if (button == 0) {
                FunctionalityClass().voice(this, 404, "shoppingRequestCode", activityResult)
            } else if (button == 1) {
                FunctionalityClass().alertDialog(this, R.drawable.meesho, "Meesho") { data ->

                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://meesho.com/search?q=$data&searchType=manual&searchIdentifier=text_search")
                        )
                    )


                }
            }
        }

        tataCliqButton.setOnClickListener {
            if (button == 0) {
                FunctionalityClass().voice(this, 405, "shoppingRequestCode", activityResult)
            } else if (button == 1) {
                FunctionalityClass().alertDialog(this, R.drawable.tatacliq, "Tata CLiQ") { data ->

                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://www.tatacliq.com/search/?searchCategory=all&text=$data")
                        )
                    )

                }
            }

        }

        ajioButton.setOnClickListener {
            if (button == 0) {
                FunctionalityClass().voice(this, 406, "shoppingRequestCode", activityResult)
            } else if (button == 1) {
                FunctionalityClass().alertDialog(this, R.drawable.ajio, "AJIO") { data ->

                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://ajio.com/search?q=$data")
                        )
                    )

                }
            }

        }

        snapDealButton.setOnClickListener {

            if (button == 0) {
                FunctionalityClass().voice(this, 407, "shoppingRequestCode", activityResult)
            } else if (button == 1) {
                FunctionalityClass().alertDialog(this, R.drawable.snapdeal, "Snapdeal") { data ->

                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://www.snapdeal.com/search?keyword=$data")
                        )
                    )

                }
            }


        }

        jioMartButton.setOnClickListener {

            if (button == 0) {
                FunctionalityClass().voice(this, 408, "shoppingRequestCode", activityResult)
            } else if (button == 1) {
                FunctionalityClass().alertDialog(this, R.drawable.jiomart, "JioMart") { data ->

                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://www.jiomart.com/catalogsearch/result?q=$data")
                        )
                    )

                }
            }

        }

    }

    private fun initVar() {
        amazonButton = findViewById(R.id.amazonButton)
        flipkartButton = findViewById(R.id.flipkartButton)
        myntraButton = findViewById(R.id.myntraButton)
        meeshoButton = findViewById(R.id.meeshoButton)
        tataCliqButton = findViewById(R.id.tataCliqButton)
        ajioButton = findViewById(R.id.ajioButton)
        snapDealButton = findViewById(R.id.snapDealButton)
        jioMartButton = findViewById(R.id.jioMartButton)

        sharedPreferences = getSharedPreferences("My Language", MODE_PRIVATE)
        sharedPreferencesEditor = sharedPreferences.edit()

        language = sharedPreferences.getString("language", "")
        requestCode = sharedPreferences.getInt("shoppingRequestCode", 0)

        MobileAds.initialize(this) {}

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

    }
}