package com.itis.irekpractice

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.lang.Exception

var WrongName = false
var WrongHeight = false
var WrongWeight = false
var WrongAge = false

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var handler = FieldHandler()
        val clickButton = findViewById<Button>(R.id.button)
        val resultField = findViewById<TextView>(R.id.result)

        val nameField = findViewById<EditText>(R.id.nameInput)
        val heightField = findViewById<EditText>(R.id.heightInput)
        val weightField = findViewById<EditText>(R.id.weightInput)
        val ageField = findViewById<EditText>(R.id.ageInput)

        clickButton?.setOnClickListener {
            handler.handleEveryField(nameField, heightField, weightField, ageField)
            if (!WrongName && !WrongHeight && !WrongWeight && !WrongAge) {
                resultField.text = "Result: " + caloriesCalculator(
                    nameField.text.toString(), heightField.text.toString().toInt(),
                    weightField.text.toString().toDouble(), ageField.text.toString().toInt()
                ).toString() + " calories/day"
                resultField.visibility = View.VISIBLE
            }
        }

        nameField.setOnClickListener {
            if (WrongName)
                nameField.setText("")
            WrongName = false
            nameField.setTextColor(Color.parseColor("#FF49454F"))
        }
        heightField.setOnClickListener {
            if (WrongHeight)
                heightField.setText("")
            WrongHeight = false
            heightField.setTextColor(Color.parseColor("#FF49454F"))
        }
        weightField.setOnClickListener {
            if (WrongWeight)
                weightField.setText("")
            WrongWeight = false
            weightField.setTextColor(Color.parseColor("#FF49454F"))
        }
        ageField.setOnClickListener {
            if (WrongAge)
                ageField.setText("")
            WrongAge = false
            ageField.setTextColor(Color.parseColor("#FF49454F"))
        }

    }
}

fun caloriesCalculator(name: String, height: Int, weight: Double, age: Int) : Double {
    return (name.length % 3 + 1) * (5 * height - 9 * age) + 11.5 * weight + 225
}

class FieldHandler {

    fun handleEveryField(nameField: TextView, heightField: TextView, weightField: TextView, ageField: TextView) {
        if (!nameCheck(nameField.text.toString()))
        {
            WrongName = true
            wrongField(nameField)
        }

        if (!heightCheck(heightField.text.toString()))
        {
            WrongHeight = true
            wrongField(heightField)
        }

        if (!weightCheck(weightField.text.toString()))
        {
            WrongWeight = true
            wrongField(weightField)
        }

        if (!ageCheck(ageField.text.toString()))
        {
            WrongAge = true
            wrongField(ageField)
        }
    }
    fun nameCheck(name: String): Boolean {
        if (name.isEmpty())
            return false
        else
            return name.length <= 50

    }

    fun heightCheck(height: String): Boolean {
        if (height.isEmpty())
             return false
        try {
            return height.toInt() in 1..249
        }
        catch (ex: Exception) {
            return false
        }
    }
    fun weightCheck(weight: String): Boolean {
        if (weight.isEmpty())
            return false
        try {
            return weight.toDouble() > 0 && weight.toDouble() < 250
        }
        catch (ex: Exception) {
            return false
        }
    }
    fun ageCheck(age: String): Boolean {
        if (age.isEmpty())
            return false
        try {
            return age.toInt() in 1..149
        }
        catch (ex: Exception) {
            return false
        }
    }
    fun wrongField(field: TextView) {
        field.text = "Wrong input"
        field.setTextColor(Color.parseColor("#FF0000"))

    }
}