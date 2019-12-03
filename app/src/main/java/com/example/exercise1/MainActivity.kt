package com.example.exercise1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.exercise1.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)



        binding.buttonCalculate.setOnClickListener {
            calculate(it)

        }
        binding.buttonReset.setOnClickListener{
            reset(it)
        }
    }

    private fun reset(view: View){
        binding.apply {

            binding.editTextLoanPeriod.text = null
            binding.editTextInterestRate.text = null
            binding.editTextDownPayment.text = null
            binding.editTextCarPrice.text = null
            binding.textViewCalLoan.text = null
            binding.textViewCalInterest.text= null
            binding.textViewCalMonRepay.text = null
        }
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun calculate(view: View){

        val car_loan = binding.editTextCarPrice.text.toString().toInt() - binding.editTextDownPayment.text.toString().toInt()

        val car_interest = (binding.editTextInterestRate.text.toString().toDouble()/100) * (binding.editTextLoanPeriod.text.toString().toInt() * car_loan)
        val monthlyPayment = (car_loan + car_interest) / binding.editTextLoanPeriod.text.toString().toInt() /12
        binding.apply {


            binding.textViewCalLoan.text = "RM " + car_loan.toString()
            binding.textViewCalInterest.text= "RM " + car_interest.toString()
            binding.textViewCalMonRepay.text = "RM " + monthlyPayment.toString()
        }

        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)

    }
}
