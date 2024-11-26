package co.tamara.example

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import co.tamara.example.appvalue.AppConst.Companion.AUTH_TOKEN
import co.tamara.example.appvalue.AppConst.Companion.API_URL
import co.tamara.example.appvalue.AppConst.Companion.NOTIFICATION_WEB_HOOK_URL
import co.tamara.example.appvalue.AppConst.Companion.NOTIFICATION_TOKEN
import co.tamara.example.appvalue.AppConst.Companion.PUBLIC_KEY
import co.tamara.sdk.PaymentResult
import co.tamara.sdk.TamaraPayment
import co.tamara.sdk.TamaraPaymentHelper

internal class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        TamaraPayment.initialize(AUTH_TOKEN,API_URL, NOTIFICATION_WEB_HOOK_URL, PUBLIC_KEY,
        NOTIFICATION_TOKEN, true)
        /*
        * Redirect to the Tamara checkout page using a backend-generated checkout URL.
        * Ensure you replace the example checkout URL and callback URLs with actual dynamic values.
        * Uncomment the lines below when ready to use.
        */
        //TamaraPayment.startPayment(this, "https://checkout-sandbox.tamara.co/checkout/310fdb59-f447-44df-825b-19f467c6774b?locale=en-US",
        //    "tamara://success", "tamara://failure", "tamara://cancel")
        //startActivity(Intent(this, MainJavaActivity::class.java))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(TamaraPaymentHelper.shouldHandleActivityResult(requestCode, resultCode, data)){
            var result = TamaraPaymentHelper.getData(data!!)

            when(result?.status){
                PaymentResult.STATUS_CANCEL ->{
                    Toast.makeText(this, R.string.payment_cancel, Toast.LENGTH_LONG).show()
                }
                PaymentResult.STATUS_FAILURE -> {
                    Toast.makeText(this, result.getMessage() ?: getString(R.string.payment_error), Toast.LENGTH_LONG).show()
                }
                PaymentResult.STATUS_SUCCESS -> {
                    val checkoutSession = TamaraPaymentHelper.checkOutSession(data)
                    Log.d("TAG", "checkoutSession>>>>>: ${checkoutSession?.order_id}")
                    Toast.makeText(this, R.string.payment_success, Toast.LENGTH_LONG).show()
                }
            }
        }
    }



}
