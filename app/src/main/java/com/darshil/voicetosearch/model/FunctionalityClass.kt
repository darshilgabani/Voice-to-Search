package com.darshil.voicetosearch.model

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import com.darshil.voicetosearch.R

class FunctionalityClass {

    private var language: String? = null

    fun alertDialog(context: Context,drawableId : Int,appName : String,callback: (data: String) -> Unit){
        val builder = android.app.AlertDialog.Builder(context)
        val inflate = LayoutInflater.from(context).inflate(R.layout.keyboard_dialog, null)

        val cancelButton = inflate.findViewById<Button>(R.id.cancelButton)
        val imageView = inflate.findViewById<ImageView>(R.id.img)
        val textView = inflate.findViewById<TextView>(R.id.txt)
        val searchButton = inflate.findViewById<Button>(R.id.searchButton)

        imageView.setImageResource(drawableId)
        textView.text = appName

        val dialog = builder.create()
        dialog.setCancelable(true)

        searchButton.setOnClickListener {
            val data = inflate.findViewById<EditText>(R.id.editText).text.toString()
            if (data == "") {
                Toast.makeText(context, "Please Enter Text", Toast.LENGTH_SHORT).show()
            } else {
                callback(data)
                dialog.cancel()
            }
        }

        cancelButton.setOnClickListener {
            dialog.cancel()
        }
        dialog.setView(inflate)
        dialog.show()

    }

    fun getButton(context: Context) : Int {
        val sharedPreferences: SharedPreferences? = context.getSharedPreferences("My Language", AppCompatActivity.MODE_PRIVATE)
        val type = sharedPreferences?.getString("Type", "")
        if (type == "Voice") {
            return 0
        } else if (type == "Keyboard") {
            return 1
        }
        return 10
    }

    fun voice(context: Context,requestCode : Int,key : String,activityResult :ActivityResultLauncher<Intent>){

        val sharedPreferences: SharedPreferences? = context.getSharedPreferences("My Language", AppCompatActivity.MODE_PRIVATE)
        sharedPreferences?.edit()?.putInt(key, requestCode)?.apply()

        language = sharedPreferences?.getString("language", "")

        val intent = Intent("android.speech.action.RECOGNIZE_SPEECH")
        intent.putExtra("android.speech.extra.LANGUAGE", language)

        activityResult.launch(intent)
    }
}