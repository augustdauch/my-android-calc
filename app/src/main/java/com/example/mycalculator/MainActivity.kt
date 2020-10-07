package com.example.mycalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var pendingOperation = "="
    var operand1: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val listener = View.OnClickListener { v ->
            val button = v as Button
            newNumber.append(button.text)
        }

        button0.setOnClickListener(listener)
        button1.setOnClickListener(listener)
        button2.setOnClickListener(listener)
        button3.setOnClickListener(listener)
        button4.setOnClickListener(listener)
        button5.setOnClickListener(listener)
        button6.setOnClickListener(listener)
        button7.setOnClickListener(listener)
        button8.setOnClickListener(listener)
        button9.setOnClickListener(listener)
        buttonDot.setOnClickListener(listener)

        val opListener = View.OnClickListener { v ->
            val operator = (v as Button).text.toString()
            operation.text = operator

            try {
                val value = newNumber.text.toString().toDouble()
                performOperation(value, operator)
            }catch (e: NumberFormatException) {
                newNumber.setText("")
            }
            pendingOperation = operator
        }

        buttonDivide.setOnClickListener(opListener)
        buttonMultiply.setOnClickListener(opListener)
        buttonMinus.setOnClickListener(opListener)
        buttonPlus.setOnClickListener(opListener)
        buttonEquals.setOnClickListener(opListener)

        buttonNeg.setOnClickListener(View.OnClickListener { v ->
            try {
                var value = newNumber.text.toString().toDouble()
                value *= -1
                newNumber.setText(value.toString())
            } catch (e: NumberFormatException) {

            }
        })

        buttonClear.setOnClickListener { v ->
            result.setText("")
            newNumber.setText("")
            pendingOperation = "="

        }


    }

    private fun performOperation(value: Double, operator: String) {

        if (operand1 == null) {
            operand1 = value
        } else {

            if (pendingOperation == "=") {
                pendingOperation = operator
            }

            when (pendingOperation) {
                "=" -> operand1 = value
                "+" -> operand1 = operand1!! + value
                "-" -> operand1 = operand1!! - value
                "*" -> operand1 = operand1!! * value
                "/" -> operand1 =
                    if (value == 0.0) {
                        Double.NaN
                    } else {
                        operand1!! / value
                    }
            }
        }
        result.setText(operand1.toString())
        newNumber.setText("")
    }
}
