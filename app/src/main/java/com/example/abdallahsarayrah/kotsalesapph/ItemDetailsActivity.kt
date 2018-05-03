package com.example.abdallahsarayrah.kotsalesapph

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_item_details.*

class ItemDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_details)

        val requestQueue = Volley.newRequestQueue(this)
        val requestType = JsonObjectRequest(Request.Method.GET, "http://192.168.0.29/android/salesh/item_get.php?itemId=${intent.getStringExtra("item_id")}", null, Response.Listener { response ->
            textView_item_details_name.text = "Item name: " + response.getString("item_name")
            textView_item_details_price.text = "Item Price: " + response.getString("item_price")
            var photo = "http://192.168.0.29/android/salesh/images/${response.getString("item_image")}".replace(" ", "%20")
            Picasso.with(this).load(photo).into(imageView_item_details_image)

            Users.price = response.getString("item_price").toDouble()
        }, Response.ErrorListener { error -> Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show() })
        requestQueue.add(requestType)

        button_item_details_quantity.setOnClickListener {
            val obj = QuantityFragment()
            obj.show(fragmentManager, "Quantity")
        }
    }
}
