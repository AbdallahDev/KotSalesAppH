package com.example.abdallahsarayrah.kotsalesapph

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.paypal.android.sdk.payments.PayPalConfiguration
import com.paypal.android.sdk.payments.PayPalPayment
import com.paypal.android.sdk.payments.PayPalService
import com.paypal.android.sdk.payments.PaymentActivity
import kotlinx.android.synthetic.main.activity_total.*
import java.math.BigDecimal

class TotalActivity : AppCompatActivity() {

    private var config: PayPalConfiguration? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_total)

        val total: Double = Users.qty * Users.price
        textView_total.text = total.toString()

        config = PayPalConfiguration().environment(PayPalConfiguration.ENVIRONMENT_SANDBOX).clientId(Users.clientId)
        val intent = Intent(this, PayPalService::class.java)
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config)
        startService(intent)

        button_total_paypal.setOnClickListener {
            val payment = PayPalPayment(BigDecimal.valueOf(total), "USD", "KotSalesAppH", PayPalPayment.PAYMENT_INTENT_SALE)

            val paypalIntent = Intent(this, PaymentActivity::class.java)
            paypalIntent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config)
            paypalIntent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment)
            startActivityForResult(paypalIntent, 123)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 123 && resultCode == Activity.RESULT_OK) Toast.makeText(this, "Thank you", Toast.LENGTH_SHORT).show()
    }
}
