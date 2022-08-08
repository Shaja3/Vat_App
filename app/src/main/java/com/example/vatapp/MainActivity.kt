package com.example.vatapp

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vatapp.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO [2] Layout View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root //reference layout file
        setContentView(view) // link layout

        // TODO [3] Button binding and Calculate VAT
        binding.btnCalculateVat.setOnClickListener {
            calculateVat()
        } //End setOnClickListener

    } // End onCreate

    private fun calculateVat() {
        val stringInTextField = binding.etTotalCost.text.toString()
        val cost = stringInTextField.toDouble()

        val selectedId = binding.rbVatOption.checkedRadioButtonId

        val vatPercentage = when(selectedId){
            R.id.rb_vat_10 -> 0.10
            R.id.rb_vat_15 -> 0.15
            else -> 0.20
        }

        val vat = vatPercentage * cost
        var total = cost + vat

        val roundVat = binding.switchRoundUp.isChecked

        if (roundVat){
            total = kotlin.math.ceil(total)
        }

        val formattedTotal = NumberFormat.getCurrencyInstance().format(total)
        binding.txtCostTotal.text = getString(R.string.total_amount, formattedTotal)
    }
} // End MainActivity


