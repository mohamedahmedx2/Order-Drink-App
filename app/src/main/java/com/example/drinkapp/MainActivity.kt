package com.example.drinkapp

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
   private lateinit var dropDownMenu : AutoCompleteTextView
   private lateinit var textPrice : TextView
   private lateinit var button : Button
    @SuppressLint("SetTextI18n", "IntentReset")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        initializeViews()
        popDropDownMenu()
        var values = mapOf(
            "Orange juice" to 15,
            "Apple juice" to 23,
            "Mango juice" to 30,
            "Kewi juice" to 35,
            "Banana juice" to 40
        )
        setValueInTextView(values)
        button.setOnClickListener {
            var myIntent = Intent(Intent.ACTION_SEND)
            myIntent.data = Uri.parse("mailto: CitDrik@gmail.com")
            myIntent.setType("text/plain")
            var message = "i want to order : " + dropDownMenu.getText().toString()
            myIntent.putExtra(Intent.EXTRA_TEXT,message)
            myIntent.putExtra(Intent.EXTRA_SUBJECT,"Order")
            myIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("CitDrik@gmail.com"))
            startActivity(myIntent)
        }





    }

    private fun setValueInTextView(values: Map<String, Int>) {
        dropDownMenu.setOnItemClickListener { adapterView, view, i, l ->
            var fromValue = values[dropDownMenu.text.toString()]
            textPrice.setText("Total Price : " + fromValue.toString())
        }
    }

    private fun popDropDownMenu() {
        var myList =
            listOf("Orange juice", "Apple juice", "Mango juice", "Kewi juice", "Banana juice")
        var adapter = ArrayAdapter<String>(this, R.layout.drop_down, myList)
        dropDownMenu.setAdapter(adapter)
    }

    private fun initializeViews() {
        dropDownMenu = findViewById(R.id.autoComplete)
        textPrice = findViewById(R.id.textView2)
        button = findViewById(R.id.button)
    }
}