package com.example.brotherskitchen.activities;

import androidx.annotation.ContentView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.brotherskitchen.MainActivity;
import com.example.brotherskitchen.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SelectPaymentActivity extends AppCompatActivity {

    ImageView card,cod;
    String orderID;
    Button ok;

    FirebaseFirestore firestore;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_payment);

        card = findViewById(R.id.creditcard);
        cod = findViewById(R.id.cod);




         orderID = getIntent().getStringExtra("key");

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SelectPaymentActivity.this,CardPaymentActivity.class));
//                Toast.makeText(SelectPaymentActivity.this, "ksaksajksaksjaka", Toast.LENGTH_SHORT).show();
                // card payment eke -------------*******

            }
        });

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();




        cod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_placed_order);

                ok = findViewById(R.id.ok);

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(SelectPaymentActivity.this, MainActivity.class));
                    }
                });
                Toast.makeText(SelectPaymentActivity.this, "Your Order Has Been Placed", Toast.LENGTH_LONG).show();

                // payment method update    ------>l :(

                final DocumentReference reference = FirebaseFirestore.getInstance().collection("CurrentUser").document(auth.getCurrentUser().getUid())
                        .collection("MyOrder").document(orderID);
                reference.update("payment","cash on delivery");

//                final HashMap<String,Object> cartMap = new HashMap<>();
//
//                cartMap.put("payment","cash on delivery");
//
//                firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
//                        .collection("MyOrder").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
//                    @Override
//                    public void onComplete(@NonNull Task<DocumentReference> task) {
////                        Toast.makeText(DetailedActivity.this, "Added To A cart", Toast.LENGTH_SHORT).show();
////                        finish();
//                    }
//                });

            }
        });
    }
}