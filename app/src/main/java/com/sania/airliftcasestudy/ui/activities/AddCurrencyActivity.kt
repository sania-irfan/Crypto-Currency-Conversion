package com.sania.airliftcasestudy.ui.activities

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import com.sania.airliftcasestudy.R
import com.sania.airliftcasestudy.models.SpinnerModel
import com.sania.airliftcasestudy.ui.databinding.SpinnerAdapter
import com.sania.airliftcasestudy.ui.databinding.SpinnerConstants
import kotlinx.android.synthetic.main.activity_add_currency.*
import java.util.*

class AddCurrencyActivity : AppCompatActivity() {

    //String variables to get the user selection from the spinner
    private var mCryptoUnit: String? = null
    private var mCurrencyUnit: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_currency)

        setUpCryptoSpinner()
        setUpCurrencySpinner()

        btnOkay.setOnClickListener {
            val intent = Intent()
            var amount = 1
            if (etCryptoAmount.text.isNotEmpty()) {
                amount = etCryptoAmount.text.toString().toInt()
            }
            intent.putExtra("crypto", mCryptoUnit)
            intent.putExtra("amount", amount)
            intent.putExtra("currency", mCurrencyUnit)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    private fun setUpCryptoSpinner() {
        val cryptoAdapter = SpinnerAdapter(this, SpinnerConstants.cryptoList)
        spCrypto.adapter = cryptoAdapter

        // Set the integer mSelected to the constant values
        spCrypto.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                val currentItem: SpinnerModel = SpinnerConstants.cryptoList[position]
                if (currentItem != null) {
                    mCryptoUnit = currentItem.codes
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun setUpCurrencySpinner() {
        val currencyAdapter = SpinnerAdapter(this, SpinnerConstants.countryList)
        spCurrency.adapter = currencyAdapter

        spCurrency.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                val currentItem: SpinnerModel = SpinnerConstants.countryList[position]
                if (currentItem != null) {
                    mCurrencyUnit = currentItem.codes
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
}
