package com.example.lab2_dmd

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etAge: EditText
    private lateinit var etHeight: EditText
    private lateinit var etWeight: EditText
    private lateinit var rgGender: RadioGroup
    private lateinit var rbMale: RadioButton
    private lateinit var rbFemale: RadioButton
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etAge = findViewById(R.id.age)
        etHeight = findViewById(R.id.height)
        etWeight = findViewById(R.id.weight)
        rgGender = findViewById(R.id.radio_group)
        rbMale = findViewById(R.id.radio_male)
        rbFemale = findViewById(R.id.radio_female)
        btnCalculate = findViewById(R.id.calculate)
        tvResult = findViewById(R.id.tvResult)

        btnCalculate.setOnClickListener {
            calculateBMI()
        }
    }

    private fun calculateBMI() {
        val ageStr = etAge.text.toString()
        val heightStr = etHeight.text.toString()
        val weightStr = etWeight.text.toString()

        if (ageStr.isEmpty() || heightStr.isEmpty() || weightStr.isEmpty()) {
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            return
        }

        val height = heightStr.toFloatOrNull()?.div(100)
        val weight = weightStr.toFloatOrNull()

        if (height == null || weight == null || height == 0f) {
            Toast.makeText(this, "Please enter valid values", Toast.LENGTH_SHORT).show()
            return
        }

        val bmi = weight / (height * height)

        val bmiLabel = when {
            bmi < 18.5 -> "Underweight"
            bmi in 18.5..24.9 -> "You are on the right path!"
            bmi in 25.0..29.9 -> "Overweight"
            else -> "Obese"
        }

        val result = String.format("%.1f", bmi)
        tvResult.text = result

        val intent = Intent(this, SecondActivity::class.java).apply {
            putExtra("BMI_RESULT", result)
            putExtra("BMI_MESSAGE", bmiLabel)
        }
        startActivity(intent)
    }
}
