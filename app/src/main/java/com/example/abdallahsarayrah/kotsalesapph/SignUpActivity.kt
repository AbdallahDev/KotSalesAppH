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
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        button_signUp_signUpBtn.setOnClickListener {
            if (editText_signUp_password.text.toString() == editText_signUp_confirm.text.toString()) {
                val url = "http://192.168.0.29/android/salesh/signUp.php?mobile=${editText_signUp_mobile.text}&password=${editText_signUp_password.text}&name=${editText_signUp_name.text}&address=${editText_signUp_address.text}"

                val progressDialog = ProgressDialog(this)
                progressDialog.setMessage("Please Wait...")
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
                progressDialog.show()

                val requestQueue = Volley.newRequestQueue(this)
                val stringRequest = StringRequest(Request.Method.GET, url, Response.Listener { response ->
                    progressDialog.hide()
                    if (response != "0") {
                        Users.mobile = editText_signUp_mobile.text.toString()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else Toast.makeText(this, "User already exist", Toast.LENGTH_SHORT).show()
                }, Response.ErrorListener { error ->
                    progressDialog.hide()
                    Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
                })
                requestQueue.add(stringRequest)
            } else Toast.makeText(this, "Password not match.", Toast.LENGTH_SHORT).show()
        }
    }
}
