package com.example.androidmaster.imccalculator

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidmaster.R
import com.example.androidmaster.imccalculator.ImcCalculatorActivity.Companion.IMC_KEY

class ResultIMCActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private lateinit var tvIMC: TextView
    private lateinit var tvDescription: TextView
    private lateinit var btnRecalculate: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result_mcactivity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val result: Double = intent.extras?.getDouble(IMC_KEY) ?: -1.0
        initComponent()
        initUI(result)
        initListeners()
    }

    private fun initListeners() {
        btnRecalculate.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initUI(result: Double){
        tvIMC.text = result.toString()
        when(result){
            in 0.00..18.50 -> { //Bajo peso
                tvResult.text = getString(R.string.title_low_weight)
                tvResult.setTextColor(getColor(R.color.low_weight))
                tvDescription.text = getString(R.string.description_low_weight)
                Log.i("Result", "Bajo peso")
        }
            in 18.51..24.99 -> { //Peso normal
                tvResult.text = getString(R.string.title_normal_weight)
                tvResult.setTextColor(getColor(R.color.normal_weight))
                tvDescription.text = getString(R.string.description_normal_weight)
                Log.i("Result", "Peso normal")
        }
            in 25.00..29.99 -> { //Sobrepeso
                tvResult.text = getString(R.string.title_overweight)
                tvResult.setTextColor(getColor(R.color.overweight))
                tvDescription.text = getString(R.string.description_overweight)
                Log.i("Result", "Sobrepeso")
        }
            in 30.00..99.00 -> { //Obesidad
                tvResult.text = getString(R.string.title_obesity)
                tvResult.setTextColor(getColor(R.color.obesity))
                tvDescription.text = getString(R.string.description_obesity)
                Log.i("Result", "Obesidad")
        }
            else -> { //Error
                tvIMC.text = getString(R.string.error)
                tvResult.text = getString(R.string.error)
                tvResult.setTextColor(getColor(R.color.low_weight))
            }
        }
    }

    private fun initComponent() {
        tvResult = findViewById(R.id.tvResult)
        tvIMC = findViewById(R.id.tvIMC)
        tvDescription = findViewById(R.id.tvDescription)
        btnRecalculate = findViewById(R.id.btnRecalculate)
    }

}