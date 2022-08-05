package com.example.brotherskitchen.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.brotherskitchen.MainActivity;
import com.example.brotherskitchen.R;
import com.example.brotherskitchen.models.MyCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlacedOrderActivity extends AppCompatActivity {

    FirebaseFirestore firestore;
    FirebaseAuth auth;
    Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_placed_order);
//        setContentView(R.layout.activity_select_payment);


        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();





        List<MyCartModel> list = (ArrayList<MyCartModel>) getIntent().getSerializableExtra("itemList");

        if (list != null && list.size() > 0 ){
            for(MyCartModel model : list ){
                final HashMap<String,Object> cartMap = new HashMap<>();

                cartMap.put("productName",model.getProductName());
                cartMap.put("productPrice",model.getProductPrice());
                cartMap.put("currentDate",model.getCurrentDate());
                cartMap.put("currentTime",model.getCurrentTime());
                cartMap.put("totalQuantity",model.getTotalQuantity());
                cartMap.put("totalPrice",model.getTotalPrice());
                cartMap.put("status",model.getStatus()); // new line 11-15************************
                cartMap.put("payment",model.getPayment()); // new line 11-17************************

                firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                        .collection("MyOrder").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
//                        Toast.makeText(PlacedOrderActivity.this, "Your Order Has Been Placed", Toast.LENGTH_SHORT).show();

//                        Log.e("1111",task.getResult().getId().toString());
                        Intent i=new Intent(PlacedOrderActivity.this,SelectPaymentActivity.class);
                        i.putExtra("key",task.getResult().getId().toString());
                        startActivity(i);



                    }
                });
            }

        }

    }
}