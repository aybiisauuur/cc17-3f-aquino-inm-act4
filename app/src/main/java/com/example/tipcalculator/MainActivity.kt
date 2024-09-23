package com.example.tipcalculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tipcalculator.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        binding.buttonCalculate.setOnClickListener {
            calculateTip()
        }
    }

    /* Calculate the tip based on the user input */
    private fun calculateTip() {
        val costString = binding.costOfService.text.toString()

        if (costString.isEmpty()) {
            binding.tipResult.text = "Please enter a valid cost"
            return
        }

        val cost = costString.toDouble()

        val selectedId = binding.tipOptions.checkedRadioButtonId
        val tipPercentage = when (selectedId) {
            R.id.optionAmazing -> 0.20
            R.id.optionGood -> 0.18
            R.id.optionOkay -> 0.15
            else -> 0.15
        }
        var tip = cost * tipPercentage
        val roundUp = binding.roundTip.isChecked
        if (roundUp) {
            tip = ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        // Log the calculated tip to check if it's being calculated correctly
        Log.d("TipCalculator", "Calculated Tip: $tip")

        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}