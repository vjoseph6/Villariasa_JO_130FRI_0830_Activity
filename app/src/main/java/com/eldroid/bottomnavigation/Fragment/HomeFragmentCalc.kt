package com.eldroid.bottomnavigation.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.eldroid.bottomnavigation.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragmentCalc.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragmentCalc : Fragment() {

    private lateinit var tvResult: TextView
    private lateinit var tvPreviousOperation: TextView
    private var number1: Double = 0.0
    private var number2: Double = 0.0
    private var operator: String = ""
    private var input: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_calc, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize views using view from fragment layout
        tvResult = view.findViewById(R.id.tvResult)
        tvPreviousOperation = view.findViewById(R.id.tvPreviousOperation)

        val btn1: Button = view.findViewById(R.id.btn1)
        val btn2: Button = view.findViewById(R.id.btn2)
        val btn3: Button = view.findViewById(R.id.btn3)
        val btn4: Button = view.findViewById(R.id.btn4)
        val btn5: Button = view.findViewById(R.id.btn5)
        val btn6: Button = view.findViewById(R.id.btn6)
        val btn7: Button = view.findViewById(R.id.btn7)
        val btn8: Button = view.findViewById(R.id.btn8)
        val btn9: Button = view.findViewById(R.id.btn9)
        val btn0: Button = view.findViewById(R.id.btn0)
        val btnPlus: Button = view.findViewById(R.id.btnPlus)
        val btnMinus: Button = view.findViewById(R.id.btnMinus)
        val btnMultiply: Button = view.findViewById(R.id.btnMultiply)
        val btnDivide: Button = view.findViewById(R.id.btnDivide)
        val btnEquals: Button = view.findViewById(R.id.btnEquals)
        val btnModulo: Button = view.findViewById(R.id.btnModulo)
        val btnDot: Button = view.findViewById(R.id.btnDot)
        val btnClear: Button = view.findViewById(R.id.btnAllClear)
        val btnDelete: Button = view.findViewById(R.id.btnDelete)

        // Number buttons click listener
        val numberButtons = listOf(btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0)
        numberButtons.forEach { button ->
            button.setOnClickListener {
                input += button.text
                tvResult.text = input
            }
        }

        // Operator buttons click listener
        val operatorButtons = mapOf(
            btnPlus to "+",
            btnMinus to "-",
            btnMultiply to "*",
            btnDivide to "/",
            btnModulo to "%"
        )

        operatorButtons.forEach { (button, op) ->
            button.setOnClickListener {

                // Ignore if input is empty (no number entered) or if an operator is already set
                if (input.isEmpty()) {
                    return@setOnClickListener // Don't apply operator if no number is input
                }

                if (input.isNotEmpty()) {
                    operator = op
                    number1 = input.toDouble()
                    input = ""
                }

            }
        }

        // Equals button click listener
        btnEquals.setOnClickListener {
            number2 = input.toDouble()

            // Calculate result based on the operator
            val result = when (operator) {
                "+" -> number1 + number2
                "-" -> number1 - number2
                "*" -> number1 * number2
                "/" -> number1 / number2
                "%" -> number1 % number2
                else -> 0.0
            }

            // Format number1 and number2 based on whether they are whole numbers or have decimals
            val formattedNumber1 = if (number1 % 1 == 0.0) {
                number1.toLong().toString()
            } else {
                String.format("%.2f", number1) // Format to 2 decimal places
            }

            val formattedNumber2 = if (number2 % 1 == 0.0) {
                number2.toLong().toString()
            } else {
                String.format("%.2f", number2) // Format to 2 decimal places
            }

            // Display the previous operation as formatted (without decimals for whole numbers)
            val equationText = "$formattedNumber1 $operator $formattedNumber2"

            // Format the result to 2 decimal places
            val resultText = String.format("%.2f", result)

            // Update the TextViews
            tvPreviousOperation.text = equationText
            tvResult.text = resultText
            input = resultText
        }

        // Delete button click listener
        btnDelete.setOnClickListener {
            if (input.isNotEmpty()) {
                input = input.dropLast(1)
                tvResult.text = if (input.isEmpty()) "0" else input
            }
        }

        // Dot button click listener
        btnDot.setOnClickListener {
            if (!input.contains(".")) {
                input += "."
                tvResult.text = input
            }
        }

        // Clear button click listener
        btnClear.setOnClickListener {
            input = ""
            operator = ""
            number1 = 0.0
            number2 = 0.0
            tvResult.text = "0"
            tvPreviousOperation.text = ""
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragmentCalc()
    }
}
