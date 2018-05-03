package com.example.abdallahsarayrah.kotsalesapph

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        button_login_loginBtn.setOnClickListener {
            val url = "http://10.152.36.151/android/salesh/login.php?mobile=${editText_login_mobile.text}&password=${editText_login_password.text}"

            val progressDialog = ProgressDialog(this)
            progressDialog.setMessage("Please Wait...")
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            progressDialog.show()

            val requestQueue = Volley.newRequestQueue(this)
            val stringRequest = StringRequest(Request.Method.GET, url, Response.Listener { response ->
                progressDialog.hide()
                if (response == "1") {
                    Users.mobile = editText_login_mobile.text.toString()
                    val intent = Intent(this, CategoriesActivity::class.java)
                    startActivity(intent)
                } else Toast.makeText(this, "Login Failed, please try again.", Toast.LENGTH_SHORT).show()
            }, Response.ErrorListener { error ->
                progressDialog.hide()
                Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
            })
            requestQueue.add(stringRequest)
        }

        button_login_signUpBtn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}
