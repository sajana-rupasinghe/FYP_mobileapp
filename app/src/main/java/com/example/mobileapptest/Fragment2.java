package com.example.mobileapptest;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

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
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Fragment2 extends Fragment {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    Spinner spinner;
    ValueEventListener listener;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    DatabaseReference spinnerref;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_2, container, false);
        BarChart chart = v.findViewById(R.id.barchart);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        spinnerref=FirebaseDatabase.getInstance().getReference("activity");
        spinner = (Spinner) v.findViewById(R.id.spinnerdata);


        list=new ArrayList<>();
        adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,list);
        spinner.setAdapter(adapter);


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
        fetchdata();
        return  v;
    }


  public void fetchdata(){


      databaseReference.child("nodemcu-935e7-default-rtdb").child("activity").addValueEventListener(new ValueEventListener(){

        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {

            try {

                list.clear();
                for (DataSnapshot mydata :snapshot.getChildren()){

                    String action=mydata.child("Time").getValue().toString().substring(0,9);
                    list.add(action);
                }

                adapter.notifyDataSetChanged();
            }

            catch (Exception e){

            }
        }

        @Override
        public void onCancelled(@NonNull  DatabaseError error) {
            Log.w("failed to read valuee",error.toException());

        }
    });
     
    }
}