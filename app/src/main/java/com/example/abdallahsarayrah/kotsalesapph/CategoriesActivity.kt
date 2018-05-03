package com.example.abdallahsarayrah.kotsalesapph

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_categories.*


class CategoriesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        val url = "http://192.168.0.29/android/salesh/categories_get.php"
        val request = Volley.newRequestQueue(this)
        val arrayList = ArrayList<String>()
        val requestType = JsonArrayRequest(Request.Method.GET, url, null, Response.Listener { response ->
            (0 until response.length()).mapTo(arrayList) { response.getJSONObject(it).getString("item_category") }
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList)
            listView_categories_categories.adapter = adapter
        }, Response.ErrorListener { error ->
            Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
        })
        request.add(requestType)

        listView_categories_categories.onItemClickListener = OnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, ItemsActivity::class.java)
            intent.putExtra("item_category", arrayList[position])
            startActivity(intent)
        }
    }
}
