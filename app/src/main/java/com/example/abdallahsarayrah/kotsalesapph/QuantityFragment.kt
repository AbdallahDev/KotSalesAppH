package com.example.abdallahsarayrah.kotsalesapph


import android.os.Bundle
import android.app.DialogFragment
import android.content.Intent
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.fragment_quantity.*


/**
 * A simple [Fragment] subclass.
 */
class QuantityFragment : DialogFragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_quantity, container, false)
        val buttonQuantityFragmentAdd = view.findViewById<Button>(R.id.button_quantity_fragment_add)

        buttonQuantityFragmentAdd.setOnClickListener {
            Users.qty = editText_quantity_fragment_quantity.text.toString().toInt()

            val intent = Intent(activity, TotalActivity::class.java)
            startActivity(intent)
        }

        return view
    }

}// Required empty public constructor
