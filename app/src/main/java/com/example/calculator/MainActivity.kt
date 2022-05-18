package com.example.calculator

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

class MainActivity : AppCompatActivity() {
    var checkop = 0
    var checkcl = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Number
        button00.setOnClickListener { appendOnClick(clear = true, string = "0") }
        button01.setOnClickListener { appendOnClick(clear = true, string = "1") }
        button02.setOnClickListener { appendOnClick(clear = true, string = "2") }
        button03.setOnClickListener { appendOnClick(clear = true, string = "3") }
        button04.setOnClickListener { appendOnClick(clear = true, string = "4") }
        button05.setOnClickListener { appendOnClick(clear = true, string = "5") }
        button06.setOnClickListener { appendOnClick(clear = true, string = "6") }
        button07.setOnClickListener { appendOnClick(clear = true, string = "7") }
        button08.setOnClickListener { appendOnClick(clear = true, string = "8") }
        button09.setOnClickListener { appendOnClick(clear = true, string = "9") }
        buttonDots.setOnClickListener { appendOnClick(clear = true, string = ".") }

        //Operator
        buttonTotals.setOnClickListener { appendOnClick(clear = false, string = "+") }
        buttonMinuss.setOnClickListener { appendOnClick(clear = false, string = "-") }
        buttonMultis.setOnClickListener { appendOnClick(clear = false, string = "*") }// ALT + 0215
        buttonDevis.setOnClickListener { appendOnClick(clear = false, string = "/") } // ALT + 0247
        buttonPercents.setOnClickListener { appendOnClick(clear = false, string = "%") }

        buttonClearAlls.setOnClickListener {
            ClearAll()
        }

        buttonBackSpaces.setOnClickListener {
            backSpaceAction()
        }

        buttonResults.setOnClickListener {
            result()
        }


        buttonOpenCloses.setOnClickListener {
            OpenClose()
        }
    }

    fun appendOnClick(clear: Boolean,string:String){
        if(clear){
            tvResults.text=""
            tvExpressions.append(string)
        }else{
            tvExpressions.append(tvResults.text)
            tvExpressions.append(string)
            tvResults.text=""
        }
    }

    fun ClearAll(){
        tvExpressions.text=""
        tvResults.text=""
    }

    fun result(){
        try{
            val input = ExpressionBuilder(tvExpressions.text.toString()).build()
            val output = input.evaluate()
            val longOutput = output.toLong()
            if(output==longOutput.toDouble()){
                tvResults.text=longOutput.toString()
            }else{
                tvResults.text=output.toString()
            }

        }catch(e:Exception){
            Toast.makeText(this@MainActivity, e.message,Toast.LENGTH_LONG).show()
        }
    }

    fun backSpaceAction()
    {
        val length = tvExpressions.length()
        if(length > 0)
            tvExpressions.text = tvExpressions.text.subSequence(0, length - 1)
    }

    var X: IntArray = intArrayOf(0,1,2,3,4,5,6,7,8,9)


    fun OpenClose(){
        if(checkop == 0 ){
            appendOnClick(clear = true, string = "(")
            checkop++
        }
        if(checkcl<checkop ){
            appendOnClick(clear = false, string = ")")
            checkcl++
        }

    }

}