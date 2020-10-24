package com.example.paymentgateway;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

//Shubh Bhatnagar

public class MainActivity extends AppCompatActivity implements PaymentResultListener {
    //initialize variables
    Button btnpay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign Variable
        btnpay = findViewById(R.id.btn_pay);

       // Initialize Amount
        String sAmount = "100";

        //Convert and round off
        final int amount = Math.round(Float.parseFloat(sAmount)*100);

        btnpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Initialize razopay checkout
                Checkout checkout = new Checkout();
                //Set key id
                checkout.setKeyID("rzp_test_A7KJbH6wFrZVQd");
                //Set image
                checkout.setImage(R.drawable.payment);
                //initialize json object
                JSONObject object = new JSONObject();
                try {
                    //Put Name
                    object.put("Name","Shubh Bhatnagar");
                    //Put description
                    object.put("description","Test Payment");
                    //Put theme color
                    object.put("theme.color","#0093DD");
                    //Put Currency unit
                    object.put("Currency","INR");
                    //Put amount
                    object.put("amount",amount);
                    //Put Mobile Number
                    object.put("prefill.contact","9876543210");
                    //Put email
                    object.put("prefill.email","shubhbhatnagar@gamil.com");
                    //Opn razopay checkout activity
                    checkout.open(MainActivity.this,object);
                }catch (JSONException e){
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public void onPaymentSuccess(String s) {
        //Initialize alert Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //Set Title
        builder.setTitle("Payment Id");
        //Set message
        builder.setMessage(s);
        //Show alert dialog
        builder.show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        //Display toast
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();

    }
}