package com.darshil.voicetosearch.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.darshil.voicetosearch.R
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

class LanguageActivity : AppCompatActivity() {
    lateinit var nextButton: CardView
    lateinit var spinner: Spinner

    lateinit var sharedPreferences: SharedPreferences
    lateinit var sharedPreferencesEditor: SharedPreferences.Editor

    lateinit var mAdView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)

        initVar()

        onClick()

        onSelect()

        /* val adapter = ArrayAdapter.createFromResource(this@LanguageActivity, R.array.spinner_item, R.layout.color_spinner_layout)
         adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout)
         spinner.setAdapter(adapter)
         spinner.setOnItemSelectedListener(this@LanguageActivity)*/

    }

    private fun onSelect() {

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val language = p0?.selectedItem.toString()

                if (language == "Amharic") {
                    selectLanguage("am")
                } else if (language == "Arabic") {
                    selectLanguage("ar")
                } else if (language == "Basque") {
                    selectLanguage("eu")
                } else if (language == "Bengali") {
                    selectLanguage("bn")
                } else if (language == "English (UK)") {
                    selectLanguage("en-GB")
                } else if (language == "Portuguese (Brazil)") {
                    selectLanguage("pt-BR")
                } else if (language == "Bulgarian") {
                    selectLanguage("bg")
                } else if (language == "Catalan") {
                    selectLanguage("ca")
                } else if (language == "Cherokee") {
                    selectLanguage("chr")
                } else if (language == "Croatian") {
                    selectLanguage("hr")
                } else if (language == "Czech") {
                    selectLanguage("cs")
                } else if (language == "Danish") {
                    selectLanguage("da")
                } else if (language == "Dutch") {
                    selectLanguage("nl")
                } else if (language == "English (US)") {
                    selectLanguage("en")
                } else if (language == "Estonian") {
                    selectLanguage("et")
                } else if (language == "Filipino") {
                    selectLanguage("fil")
                } else if (language == "Finnish") {
                    selectLanguage("fi")
                } else if (language == "French") {
                    selectLanguage("fr")
                } else if (language == "German") {
                    selectLanguage("de")
                } else if (language == "Greek") {
                    selectLanguage("el")
                } else if (language == "Gujarati") {
                    selectLanguage("gu-IN")
                } else if (language == "Hebrew") {
                    selectLanguage("iw")
                } else if (language == "Hindi") {
                    selectLanguage("hi")
                } else if (language == "Hungarian") {
                    selectLanguage("hu")
                } else if (language == "Icelandic") {
                    selectLanguage("is")
                } else if (language == "Indonesian") {
                    selectLanguage("id")
                } else if (language == "Italian") {
                    selectLanguage("it")
                } else if (language == "Japanese") {
                    selectLanguage("ja")
                } else if (language == "Kannada") {
                    selectLanguage("kn")
                } else if (language == "Korean") {
                    selectLanguage("ko")
                } else if (language == "Latvian") {
                    selectLanguage("lv")
                } else if (language == "Lithuanian") {
                    selectLanguage("lt")
                } else if (language == "Malay") {
                    selectLanguage("ms")
                } else if (language == "Malayalam") {
                    selectLanguage("ms")
                } else if (language == "Marathi") {
                    selectLanguage("mr")
                } else if (language == "Norwegian") {
                    selectLanguage("no")
                } else if (language == "Polish") {
                    selectLanguage("pl")
                } else if (language == "Portuguese (Portugal)") {
                    selectLanguage("pt-PT")
                } else if (language == "Romanian") {
                    selectLanguage("ro")
                } else if (language == "Russian") {
                    selectLanguage("ru")
                } else if (language == "Serbian") {
                    selectLanguage("sr")
                } else if (language == "Chinese (PRC)") {
                    selectLanguage("zh-CN")
                } else if (language == "Slovak") {
                    selectLanguage("sk")
                } else if (language == "Slovenian") {
                    selectLanguage("sl")
                } else if (language == "Spanish") {
                    selectLanguage("es")
                } else if (language == "Swahili") {
                    selectLanguage("sw")
                } else if (language == "Swedish") {
                    selectLanguage("sv")
                } else if (language == "Tamil") {
                    selectLanguage("ta")
                } else if (language == "Telugu") {
                    selectLanguage("te")
                } else if (language == "Thai") {
                    selectLanguage("th")
                } else if (language == "Chinese (Taiwan)") {
                    selectLanguage("zh-TW")
                } else if (language == "Turkish") {
                    selectLanguage("tr")
                } else if (language == "Urdu") {
                    selectLanguage("ur-IN")
                } else if (language == "Ukrainian") {
                    selectLanguage("uk")
                } else if (language == "Vietnamese") {
                    selectLanguage("vi")
                } else if (language == "Welsh") {
                    selectLanguage("cy")
                } else if (language == "Select Language") {
                    selectLanguage("SelectLanguage")
                }

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

    }

    private fun selectLanguage(language: String) {
        sharedPreferencesEditor.putString("language", language).apply()
    }

    private fun onClick() {
        nextButton.setOnClickListener {
            if (sharedPreferences.getString("language", "") == "SelectLanguage") {
                Toast.makeText(applicationContext, "Please Select Language", Toast.LENGTH_SHORT)
                    .show();
            } else {
                startActivity(Intent(this, ChooseActivity::class.java))
            }
        }
    }

    private fun initVar() {
        nextButton = findViewById(R.id.nextButton)
        spinner = findViewById(R.id.spinner)

        sharedPreferences = getSharedPreferences("My Language", MODE_PRIVATE)
        sharedPreferencesEditor = sharedPreferences.edit()

        MobileAds.initialize(this) {}

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
    }
}