package com.example.luassignment1

import android.R.string
import android.os.Bundle
import android.text.Spanned
import android.text.style.SuperscriptSpan
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.round


class MainActivity : AppCompatActivity() {

    lateinit var calcBtn : Button
    lateinit var rinp : EditText
    lateinit var hinp : EditText
    lateinit var result : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        calcBtn = findViewById(R.id.calcbtn)
        rinp = findViewById(R.id.rinp)
        hinp = findViewById(R.id.hinp)
        result = findViewById(R.id.result)
        var flagInp = "cm"
        var flagOut = "cm3"



        var inputunit: Spinner = findViewById(R.id.inputunit)
        var optionsInp = arrayOf("cm","m")
        inputunit.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,optionsInp)

        var outputunit: Spinner = findViewById(R.id.outputunit)
        var optionsOut = arrayOf("cm3","m3","litre")
        outputunit.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,optionsOut)

        inputunit.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                flagInp = optionsInp.get(p2) //p2 is the index of selected item
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
        outputunit.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                flagOut = optionsOut.get(p2) //p2 is the index of selected item
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
        fun unitConvert(a: Double, inpUnit: String): Double {
            var a_conv: Double = 0.0
            if (inpUnit == "cm"){
                a_conv=a/100
            }
            else{
                a_conv=a
            }


            return a_conv
        }

        calcBtn.setOnClickListener{
            var r = rinp.text.toString().toDouble()
            var h = hinp.text.toString().toDouble()
            r = unitConvert(r,flagInp)
            h = unitConvert(h,flagInp)
            var res = 3.14159 * r * r * h
            if(flagOut=="cm3"){
                res = res * 1000000
            }
            else if (flagOut == "litre"){
                res = res * 1000

            }
            else{

            }
            res = round(res*1000)/1000

            result.text = "Result : " + res + " " + flagOut

        }
    }
}