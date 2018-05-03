package com.example.abdallahsarayrah.kotsalesapph

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_items.*
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener


class ItemsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)

        val url = "http://192.168.0.29/android/salesh/items_get.php?itemCategory=${intent.getStringExtra("item_category")}"
        val requestQueue = Volley.newRequestQueue(this)
        val arrayList = ArrayList<String>()
        val arrayListId = ArrayList<String>()
        val requestType = JsonArrayRequest(Request.Method.GET, url, null, Response.Listener { response ->
            for (index in 0 until response.length()) {
                arrayList.add(response.getJSONObject(index).getString("item_name"))
                arrayListId.add(response.getJSONObject(index).getString("item_id"))
            }
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList)
            listView_items_items.adapter = adapter
        }, Response.ErrorListener { error -> Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show() })
        requestQueue.add(requestType)

        listView_items_items.onItemClickListener = OnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, ItemDetailsActivity::class.java)
            intent.putExtra("item_id", arrayListId[position])
            startActivity(intent)
        }
    }
}
