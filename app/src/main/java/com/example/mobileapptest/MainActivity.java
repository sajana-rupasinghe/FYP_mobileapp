package com.example.mobileapptest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private static final String TAG = "MainActivity";

    TextView result, result1;
    Button btnBarChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        result = (TextView) findViewById(R.id.tvResult);
        result1 = (TextView) findViewById(R.id.tvResult1);
         final ImageView image = findViewById(R.id.imageView);
        btnBarChart = findViewById(R.id.btnBarChart);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String val = String.valueOf(dataSnapshot.child("nodemcu-935e7-default-rtdb/Real_Time/-MWvrDdiFQfVxilfczaJ").getValue());
                String res = val.toString().substring(1, val.length() - 1);
                String[] data = res.split(",");
                String act = data[0];
                String time = data[1];
                // Log.d(TAG, "Value is: " + val);

                String a = data[0].substring(7);
                String b = data[1].substring(6);
                SimpleDateFormat timeStampFormat = new SimpleDateFormat("HH:mm:ss");
                Date myDate = new Date();
                String s = timeStampFormat.format(myDate);

                //result1.setText(b);
                Toast.makeText(getApplicationContext(),"Current action changed to "+ a,Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Value is: " + a);
                if (a.equals("sit")){
                    image.setImageResource(R.drawable.sit);
                    result.setText("Sitting");
                    result1.setText(b);
                }
                else if (a.equals("stand")){
                    image.setImageResource(R.drawable.st);
                    result.setText("Standing");
                    result1.setText(b);}
                else if (a.equals("walk")){
                    image.setImageResource(R.drawable.walk);
                    result.setText("Walking");
                    result1.setText(b);}
                else if (a.equals("sleep")){
                    image.setImageResource(R.drawable.sleep);
                    result.setText("Sleeping");
                    result1.setText(b);
                }

            }



            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnBarChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(MainActivity.this, chart.class);
                startActivity(I);
            }
        });

    }
    
}