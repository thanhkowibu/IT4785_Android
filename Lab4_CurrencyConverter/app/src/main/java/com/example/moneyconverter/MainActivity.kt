package com.example.moneyconverter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    private var isUpdating = false
    val USD = "USD"
    val JPY = "JPY"
    val VND = "VND"
    val EUR = "EUR"
    val CNY = "CNY"
    private val usdVnd : Float = 25000F
    private val jpyVnd : Float = 160f
    private val eurVnd : Float = 27000F
    private val cnyVnd : Float = 3500f
    private val vndVnd : Float = 1F
    val currencies: Array<String> = arrayOf(USD, JPY, VND, EUR, CNY)
    lateinit var currency1: String
    lateinit var currency2: String
    lateinit var currency1EditText: EditText
    lateinit var currency2EditText: EditText
    lateinit var currentCurrency : EditText
    lateinit var targetCurrency : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        currency1EditText = findViewById<EditText>(R.id.currency1)
        currency2EditText = findViewById<EditText>(R.id.currency2)
        currentCurrency = currency1EditText
        targetCurrency = currency2EditText
        val watcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                ConvertMoney()
            }

            override fun afterTextChanged(s: Editable?) {}
        }

        // Gán TextWatcher cho cả hai EditText và điều chỉnh theo EditText được chọn
        currency1EditText.addTextChangedListener(watcher)
        currency2EditText.addTextChangedListener(watcher)
        currency1EditText.setOnClickListener {
            currentCurrency = currency1EditText
            targetCurrency = currency2EditText
        }
        currency2EditText.setOnClickListener {
            currentCurrency = currency2EditText
            targetCurrency = currency1EditText
        }
        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            currencies)
        val currency1Dropdown = findViewById<Spinner>(R.id.currency1DropDown)
        currency1Dropdown.run{
            adapter = arrayAdapter
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    currency1 = currencies[p2]
                    ConvertMoney()
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }
        val currency2Dropdown = findViewById<Spinner>(R.id.currency2DropDown)
        currency2Dropdown.run{
            adapter = arrayAdapter
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    currency2 = currencies[p2]
                    ConvertMoney()
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }
    }
    fun ConvertMoney(){
        if (isUpdating) return // Ngăn vòng lặp vô hạn
        val text = currentCurrency.text.toString()
        if (text.isNotEmpty()) {
            isUpdating = true
            var tempMoney = 0f;
            when (currentCurrency){
                currency1EditText ->{
                    when(currency1){
                        USD -> {
                            tempMoney = text.toFloat() * usdVnd
                        }
                        JPY -> {
                            tempMoney = text.toFloat() * jpyVnd
                        }
                        VND -> {
                            tempMoney = text.toFloat() * vndVnd
                        }
                        EUR -> {
                            tempMoney = text.toFloat() * eurVnd
                        }
                        CNY ->{
                            tempMoney = text.toFloat() * cnyVnd
                        }
                    }
                    when(currency2){
                        USD ->{
                            currency2EditText.setText(String.format("%.2f", tempMoney/usdVnd))
                        }
                        JPY ->{
                            currency2EditText.setText(String.format("%.2f", tempMoney/jpyVnd))
                        }
                        VND -> {
                            currency2EditText.setText(String.format("%.2f", tempMoney/vndVnd))
                        }
                        EUR ->{
                            currency2EditText.setText(String.format("%.2f", tempMoney/eurVnd))
                        }
                        CNY ->{
                            currency2EditText.setText(String.format("%.2f", tempMoney/cnyVnd))
                        }
                    }
                }
                currency2EditText ->{
                    when(currency2){
                        USD -> {
                            tempMoney = text.toFloat() * usdVnd
                        }
                        JPY -> {
                            tempMoney = text.toFloat() * jpyVnd
                        }
                        VND -> {
                            tempMoney = text.toFloat() * vndVnd
                        }
                        EUR -> {
                            tempMoney = text.toFloat() * eurVnd
                        }
                        CNY ->{
                            tempMoney = text.toFloat() * cnyVnd
                        }
                    }
                    when(currency1){
                        USD ->{
                            currency1EditText.setText(String.format("%.2f", tempMoney/usdVnd))
                        }
                        JPY ->{
                            currency1EditText.setText(String.format("%.2f", tempMoney/jpyVnd))
                        }
                        VND -> {
                            currency1EditText.setText(String.format("%.2f", tempMoney/vndVnd))
                        }
                        EUR ->{
                            currency1EditText.setText(String.format("%.2f", tempMoney/eurVnd))
                        }
                        CNY ->{
                            currency1EditText.setText(String.format("%.2f", tempMoney/cnyVnd))
                        }
                    }
                }
            }
            isUpdating = false
        } else {
            isUpdating = true
            targetCurrency.setText("")
            isUpdating = false
        }
    }
}