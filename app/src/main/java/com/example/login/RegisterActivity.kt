package com.example.login
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

import com.google.android.material.textfield.TextInputEditText

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        getSupportActionBar()?.hide();
        val loginbtn = findViewById(R.id.reg_login_btn) as Button
        val registerbtn = findViewById(R.id.reg_btn) as Button


        val first = findViewById(R.id.firstname) as TextInputEditText
        val last = findViewById(R.id.lastname) as TextInputEditText
        val emailid = findViewById(R.id.emailname) as TextInputEditText
        val phno = findViewById(R.id.phonenumber) as TextInputEditText
        val regpsw = findViewById(R.id.password) as TextInputEditText
        val regpswconfirm = findViewById(R.id.cofirmpsw) as TextInputEditText

        loginbtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        registerbtn.setOnClickListener {
            val f_name = first.text.toString()
            val l_name = last.text.toString()
            val email_id = emailid.text.toString()
            val ph_no = phno.text.toString()
            val psw_reg = regpsw.text.toString()
            val confirmpsw_reg = regpswconfirm.text.toString()


            if (checkValues(f_name, l_name, email_id, ph_no, psw_reg, confirmpsw_reg, first, last, emailid, phno, regpsw, regpswconfirm))
            {
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
            }
        }
    }




    private fun checkValues(fName: String, lName: String, emailId: String, phNo: String, pswReg: String, confirmpswReg: String, first: TextInputEditText,
                            last: TextInputEditText, emailid: TextInputEditText, phno: TextInputEditText, regpsw: TextInputEditText, regpswconfirm: TextInputEditText
    ): Boolean {
        if (!fName.isNullOrEmpty())
        {

            if (!lName.isNullOrEmpty())
            {
                if (!emailId.isNullOrEmpty())
                {

                    if (!phNo.isNullOrEmpty())
                    {
                        if (!pswReg.isNullOrEmpty())
                        {
                            if (!confirmpswReg.isNullOrEmpty())
                            {
                                return true
                            }
                            else
                            {
                               // regpswconfirm.error="PleaseEnterConfirmPassword"
                                Toast.makeText(this, "PleaseEnterConfirmPassword", Toast.LENGTH_SHORT).show()
                            }
                        }
                        else
                        {
                            //regpsw.error="PleaseEnterPassword"
                            Toast.makeText(this, "PleaseEnterPassword", Toast.LENGTH_SHORT).show()
                        }
                    }
                    else
                    {
                        phno.error="PleaseEnterPhoneNo"
//                        Toast.makeText(this, "PleaseEnterPhoneNo", Toast.LENGTH_SHORT).show()
                    }
                }
                else
                {
                    emailid.error="PleaseEnterEmail"
//                    Toast.makeText(this, "PleaseEnterEmail", Toast.LENGTH_SHORT).show()
                }
            }
            else
            {
                last.error="PleaseEnterLastName"
//                Toast.makeText(this, "PleaseEnterLastName", Toast.LENGTH_SHORT).show()
            }
        }
        else
        {
            first.error="PleaseEnterFirstName"
//            Toast.makeText(this, "PleaseEnterFirstName", Toast.LENGTH_SHORT).show()
        }
        return false
    }
}

















