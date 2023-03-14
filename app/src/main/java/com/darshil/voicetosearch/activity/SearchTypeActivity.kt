package com.darshil.voicetosearch.activity

import android.Manifest
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.darshil.voicetosearch.R
import com.darshil.voicetosearch.ads.Utils
import com.google.android.ads.nativetemplates.TemplateView
import com.google.android.gms.ads.*


class SearchTypeActivity : AppCompatActivity() {
    private lateinit var voiceButton : CardView
    private lateinit var keyboardButton : CardView

    private lateinit var template : TemplateView

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var sharedPreferencesEditor: SharedPreferences.Editor

    lateinit var adRequest : AdRequest

    companion object{
        private const val REQUEST_AUDIO_RECORD = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searchtype)

        initVar()

        onClick()

        loadAds()

    }

    private fun loadAds() {
        Utils().loadNativeAds(this,template,getString(R.string.NativeAdUnitId), adRequest)
    }

    private fun onClick() {
        voiceButton.setOnClickListener(View.OnClickListener {
            if (checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(Manifest.permission.RECORD_AUDIO), REQUEST_AUDIO_RECORD)
            } else {
                sharedPreferencesEditor.putString("Type", "Voice").apply()
                startActivity(Intent(this,LanguageActivity::class.java))
            }
        })

        keyboardButton.setOnClickListener(View.OnClickListener {
            sharedPreferencesEditor.putString("Type", "Keyboard").apply()
            startActivity(Intent(this,ChooseActivity::class.java))
        })
    }

    private fun initVar() {
        voiceButton = findViewById(R.id.voiceButton)
        keyboardButton = findViewById(R.id.keyboardButton)

        template = findViewById(R.id.nativeAdTemplate)
        
        sharedPreferences = getSharedPreferences("My Language", MODE_PRIVATE)
        sharedPreferencesEditor = sharedPreferences.edit()

        MobileAds.initialize(this) {}

        adRequest = AdRequest.Builder().build()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_AUDIO_RECORD -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    sharedPreferencesEditor.putString("Type", "Voice").apply()
                    startActivity(Intent(this,LanguageActivity::class.java))
                } else {
                    Toast.makeText(applicationContext, "Permission Denied", Toast.LENGTH_SHORT).show()
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package", packageName, null)
                    intent.data = uri
                    startActivity(intent)
                }
                return
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

}