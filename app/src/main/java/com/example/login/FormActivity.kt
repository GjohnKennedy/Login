package com.example.login

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.*

class FormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        getSupportActionBar()?.hide();

        val date_pic=findViewById<TextInputEditText>(R.id.date)

        val department_layout=findViewById<TextInputLayout>(R.id.department_layout)

        genderselect()

        departmentslect();

        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        date_pic.setOnClickListener {
            val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, myear, mmonth, mdayOfMonth ->
                date_pic.setText(""+ mdayOfMonth +"/"+ mmonth +"/"+ myear)
            }, year, month, day)
            datePickerDialog.show()
        }
    }

    private fun departmentslect()
    {
        val depart = resources.getStringArray(R.array.gender_list)
        val Auto_Complete1 = findViewById<AutoCompleteTextView>(R.id.department_tv)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, depart)
        Auto_Complete1.setAdapter(adapter)
    }

    private fun genderselect()
    {
        val gender = resources.getStringArray(R.array.gender_list)
        val Auto_Complete = findViewById<AutoCompleteTextView>(R.id.gendertv)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, gender)
        Auto_Complete.setAdapter(adapter)
    }
}