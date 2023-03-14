package com.darshil.voicetosearch.ads

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.util.Log
import com.darshil.voicetosearch.R
import com.google.android.ads.nativetemplates.NativeTemplateStyle
import com.google.android.ads.nativetemplates.TemplateView
import androidx.core.content.ContextCompat
import com.darshil.voicetosearch.activity.MeetActivity
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback

class Utils {

    fun loadNativeAds(context: Context, template: TemplateView, adId: String, adRequest: AdRequest){
        val adLoader = AdLoader.Builder(context, adId)
            .forNativeAd {
                val styles = NativeTemplateStyle.Builder().withMainBackgroundColor(ColorDrawable(ContextCompat.getColor(context,R.color.trans))).build()
                template.setStyles(styles)
                template.setNativeAd(it)
            }
            .build()

        adLoader.loadAd(adRequest)
    }

    fun loadRewardedAds(context: Context, adId: String, adRequest: AdRequest,activity: Activity){
        RewardedAd.load(context, adId, adRequest, object :
            RewardedAdLoadCallback() {
            override fun onAdLoaded(ad: RewardedAd) {
                super.onAdLoaded(ad)
                ad.show(activity) {}
            }
        })
    }

    fun loadInterstitialAds(context: Context, adId: String, adRequest: AdRequest,activity: Activity){

        InterstitialAd.load(context, adId, adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(interstitialAd: InterstitialAd) {

                    interstitialAd.show(activity)

//                    interstitialAd.fullScreenContentCallback =
//                        object : FullScreenContentCallback() {
//                            override fun onAdDismissedFullScreenContent() {
//                                Log.d("TAG", "Ad was dismissed.")
//                                interstitialAd = null
//                                startActivity(Intent(this@WelcomeActivity, MeetActivity::class.java))
//                            }
//
//                            override fun onAdFailedToShowFullScreenContent(adError: AdError) {
//                                Log.d("TAG", "Ad failed to show.")
//                                interstitialAd = null
//                            }
//
//                            override fun onAdShowedFullScreenContent() {
//                                Log.d("TAG", "Ad showed fullscreen content.")
//                            }
//                        }
                }
            }
        )
    }
}