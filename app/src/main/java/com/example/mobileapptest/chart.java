package com.example.mobileapptest;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import com.github.mikephil.charting.charts.BarChart;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class chart extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        BarChart chart = findViewById(R.id.barchart);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference friendsRef = rootRef.child("activity");




        ArrayList NoOfEmp = new ArrayList();

        NoOfEmp.add(new BarEntry(945, 0));
        NoOfEmp.add(new BarEntry( 50, 1));
        NoOfEmp.add(new BarEntry(100, 2));
        NoOfEmp.add(new BarEntry(120, 3));



        ArrayList year = new ArrayList();

        year.add("Sitting");
        year.add("Walking");
        year.add("Standing");
        year.add("Sleeping");


        BarDataSet bardataset = new BarDataSet(NoOfEmp, "Amount of activities");
        chart.animateY(5000);
        BarData data = new BarData(year,bardataset);
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        chart.setData(data);
    }
}
