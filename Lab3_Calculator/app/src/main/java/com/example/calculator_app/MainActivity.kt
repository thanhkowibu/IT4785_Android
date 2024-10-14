package com.example.calculator_app

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView
    private lateinit var result: TextView
    private var currentExpression = StringBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.screenText)
        result = findViewById(R.id.result)

        val buttonIds = intArrayOf(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3,
            R.id.button4, R.id.button5, R.id.button6, R.id.button7,
            R.id.button8, R.id.button9, R.id.plusButton, R.id.minusButton,
            R.id.divideButton, R.id.dotButton
        )

        for (id in buttonIds) {
            findViewById<Button>(id).setOnClickListener { view ->
                val button = view as Button
                currentExpression.append(button.text)
                display.text = currentExpression.toString()
                updateResult()
            }
        }

        findViewById<Button>(R.id.ceButton).setOnClickListener {
            currentExpression.clear()
            display.text = "0"
            result.text = ""
        }

        findViewById<Button>(R.id.cButton).setOnClickListener {
            currentExpression.clear()
            display.text = "0"
            result.text = ""
        }

        findViewById<Button>(R.id.bsButton).setOnClickListener {
            if (currentExpression.isNotEmpty()) {
                currentExpression.deleteCharAt(currentExpression.length - 1)
                display.text = if (currentExpression.isEmpty()) "0" else currentExpression.toString()
                updateResult()
            }
        }

        findViewById<Button>(R.id.productButton).setOnClickListener {
            currentExpression.append("*")
            display.text = currentExpression.toString()
            updateResult()
        }

        findViewById<Button>(R.id.signButton).setOnClickListener {
            if (currentExpression.isNotEmpty()) {
                try {
                    val expression = currentExpression.toString()

                    val numberRegex = Regex("(-?\\d+\\.?\\d*)$")
                    val matchResult = numberRegex.find(expression)

                    if (matchResult != null) {
                        val lastNumber = matchResult.value

                        val newNumber = if (lastNumber.startsWith("-")) {
                            lastNumber.removePrefix("-")
                        } else {
                            "-$lastNumber"
                        }

                        currentExpression.replace(
                            matchResult.range.first,
                            matchResult.range.last + 1,
                            newNumber
                        )

                        display.text = currentExpression.toString()
                        updateResult()
                    }
                } catch (e: Exception) {
                    display.text = "Error"
                    currentExpression.clear()
                }
            }
        }


        findViewById<Button>(R.id.equalButton).setOnClickListener {
            try {
                val expression: Expression = ExpressionBuilder(currentExpression.toString()).build()
                val resultValue = expression.evaluate()

                display.text = if (resultValue == resultValue.toInt().toDouble()) {
                    resultValue.toInt().toString() // Chỉ hiển thị phần nguyên
                } else {
                    resultValue.toString()
                }

                currentExpression = StringBuilder(display.text.toString())
            } catch (e: Exception) {
                display.text = "Error"
                currentExpression.clear()
            }
        }
    }

    private fun updateResult() {
        try {
            val expression: Expression = ExpressionBuilder(currentExpression.toString()).build()
            val resultValue = expression.evaluate()

            result.text = if (resultValue == resultValue.toInt().toDouble()) {
                resultValue.toInt().toString()
            } else {
                resultValue.toString()
            }
        } catch (e: Exception) {
            result.text = ""
        }
    }
}
