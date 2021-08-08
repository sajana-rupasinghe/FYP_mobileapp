package com.example.mobileapptest;

import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.text.SimpleDateFormat;
import java.util.Date;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;


public class Chart_Fragment extends Fragment {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private static final String TAG = "MainActivity";
     View view;
    TextView result, result1;
    Button btnBarChart;
    ViewPager vp;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       //setContentView(R.layout.fragment_chart);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@NonNull ViewGroup container, Bundle savedInstanceState) {


        View v= inflater.inflate(R.layout.fragment_chart, container, false);
        //view= inflater.inflate(R.layout.fragment_chart, container, false);
        result = (TextView) v.findViewById(R.id.tvResult);
        result1 = (TextView) v.findViewById(R.id.tvResult1);
        final ImageView image = v.findViewById(R.id.imageView);



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
                Toast.makeText(getActivity().getApplicationContext(),"Current action changed to "+ a,Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Value is: " + a);
                if (a.equals("sit")){
                    image.setImageResource(R.drawable.sitting);
                    result.setText("Sitting");
                    result1.setText(b);
                }
                else if (a.equals("stand")){
                    image.setImageResource(R.drawable.st);
                    result.setText("Standing");
                    result1.setText(b);}
                else if (a.equals("walk")){
                    image.setImageResource(R.drawable.walkinggif);
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

        return v;

    }
}
