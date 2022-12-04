package com.mrt.sharedpref

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.* /*go to the build.gradle(app)
and change the plugins as follows:
plugins {
    id 'com.android.application'
    id 'kotlin-android'
    id 'kotlin-android-extensions'
}
otherwise you may get errors.
*/

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()

        bt_button.setOnClickListener {
            saveData()
        }
    }

    private fun saveData(){
        val insertedText = et_text.text.toString()
        tv_text.text = insertedText

        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply(){
            putString("STRING_KEY",insertedText)
            putBoolean("BOOLEAN_KEY", sw_switch.isChecked)
        }.apply()

        Toast.makeText(this, "Veri kaydedildi", Toast.LENGTH_SHORT).show()
    }
    private fun loadData(){
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val savedString = sharedPreferences.getString("STRING_KEY", null)
        val savedBoolean = sharedPreferences.getBoolean("BOOLEAN_KEY", false)

        tv_text.text = savedString
        sw_switch.isChecked = savedBoolean

    }

}