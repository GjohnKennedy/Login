package com.example.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.CycleInterpolator
import android.view.animation.TranslateAnimation
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import com.example.login.MainActivity as MainActivity


class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    val getemil: String? = null
    val getpw: String? = null
    lateinit var email_tv1: TextInputEditText
    lateinit var psw_tv: TextInputEditText
    lateinit var tv_click_me: TextView
    lateinit var forgetbtn: TextView
    lateinit var log_in: Button
    var emailid: String? = null
    var psw: String? = null
    var input: String? = null
    val TAG = javaClass.name
    var tv: TextView? = null
    var job: Job? = null
    var bar: ProgressBar? = null
//    var dummyName:String="a"
//    var dummyPsw:String="a"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        getSupportActionBar()?.hide();

        initialization()
        Log.e("first", "${Thread.currentThread()}")

        bar?.visibility = View.GONE

        tv_click_me.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
        forgetbtn.setOnClickListener {
            // build alert dialog
            processOfForget()
        }


        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                log_in.setOnClickListener {
                    processofLogin()
                    Log.e("logindata", "${Thread.currentThread()}")
                }
            }
        }
    }//oncreatefunction


    private fun processofLogin() {

        emailid = email_tv1.text.toString()
        psw = psw_tv.text.toString()

        if (!emailid.isNullOrEmpty()) {
            if (!psw.isNullOrEmpty()) {
                // Toast.makeText(this,"${emailid}",Toast.LENGTH_SHORT).show()
                Log.e("logindata", "${emailid}")
                bar?.visibility = View.VISIBLE
//               dummyFun(emailid!!,psw!!)
                postdata(emailid!!, psw!!)
            } else {
                psw_tv.startAnimation(shakeError())
            }

        } else {
            email_tv1.startAnimation(shakeError())
        }
    }


    private fun processOfForget() {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        builder.setTitle("EnterEmail")

        val dialogLayout = inflater.inflate(R.layout.activity_alert, null)
        builder.setView(dialogLayout)
        builder.show()
        val confirmbtn = dialogLayout.findViewById<Button>(R.id.email_btn)

        confirmbtn.setOnClickListener {

            val editText = dialogLayout.findViewById<EditText>(R.id.editText)
            Toast.makeText(this, editText.text.toString(), Toast.LENGTH_SHORT).show()
        }
    }


    private fun initialization() {
//        bar.visibility = View.GONE
        tv_click_me = findViewById(R.id.sign_up) as TextView
        forgetbtn = findViewById(R.id.forget_tv) as TextView
        log_in = findViewById(R.id.login_button) as Button
        email_tv1 = findViewById(R.id.email_text) as TextInputEditText
        psw_tv = findViewById(R.id.psw_text) as TextInputEditText
        bar = findViewById(R.id.progress)
    }



    private fun postdata(getEmail: String, getPsw: String) {

        Log.i("postdata", "${getEmail}")
        Log.e("postdata", "${Thread.currentThread()}")



        Log.e(TAG, "Second thread: ${Thread.currentThread()}")

        val requestBody: RequestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("username", getEmail)
            .addFormDataPart("password", getPsw)
            .build()

        val apiInterface = MyApi.create().getUserData(requestBody)

        apiInterface.enqueue(object : retrofit2.Callback<List<MyData>> {

            override fun onResponse(call: Call<List<MyData>>, response: Response<List<MyData>>) {
                if (response.body() != null) {

                    val list: List<MyData> = response.body() ?: emptyList()

                    for (mydata in list) {
                        input = mydata.Response
                    }

                    Log.e(TAG, "the input value is: ${input}")

                    login(input)
                    //Log.e("MyData", "onResponse: ${list}")
                }

            }

            override fun onFailure(call: Call<List<MyData>>, t: Throwable) {
                Log.e(TAG, "the onFailur value is: ${t}")
                errorMessage()
            }

        })
    }


    private fun login(input: String?) {

        if (input == "Login Successfully") {
            Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, FragmentActivity::class.java)
            bar?.visibility = View.GONE
            startActivity(intent)
        } else {
            errorMessage()
        }
    }


    private fun errorMessage() {
        shakeError()
        email_tv1.error = "invalid Email"
        psw_tv.error = "invalid Password"
        Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show()
    }


    private fun shakeError(): Animation? {
        val shake_ani = TranslateAnimation(0F, 0F, 0F, 20F)
        shake_ani.duration = 400
        shake_ani.interpolator = CycleInterpolator(2.3F)
        return shake_ani
    }
}




