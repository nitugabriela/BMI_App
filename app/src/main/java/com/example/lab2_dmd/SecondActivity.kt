package com.example.lab2_dmd

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_main)

        val tvBmiValue = findViewById<TextView>(R.id.tvBmiValue)
        val tvBmiMessage = findViewById<TextView>(R.id.tvBmiMessage)
        val btnInfo = findViewById<Button>(R.id.btnInfo)
        val btnShare = findViewById<Button>(R.id.btnShare)

        val bmiResult = intent.getStringExtra("BMI_RESULT") ?: "Not applicable"
        val bmiMessage = intent.getStringExtra("BMI_MESSAGE") ?: "No message available"

        tvBmiValue.text = bmiResult
        tvBmiMessage.text = bmiMessage

        btnInfo.setOnClickListener {
            val infoIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.who.int/news-room/fact-sheets/detail/obesity-and-overweight"))
            startActivity(infoIntent)
        }

        btnShare.setOnClickListener {
            shareBmiResult(bmiResult, bmiMessage)
        }
    }

    private fun shareBmiResult(bmi: String, message: String) {
        val shareText = "My BMI is $bmi. $message"
        val shareIntent = Intent().apply{
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text"
        }
        startActivity(Intent.createChooser(shareIntent, "Share BMI Result"))
    }
}
