package com.example.mobileapptest;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private  ViewPagerAdapter viewPagerAdapter;
    private TabLayout tabLayout;
    private  ViewPager viewPager;
    private Toolbar toolbar;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private static final String TAG = "MainActivity";
    ViewPager vp;
    TextView result, result1;
    Button btnBarChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        //final Button lgout = (Button) findViewById(R.id.logout);
        Toolbar toolbar = findViewById(R.id.toolBar);
        toolbar.setTitle("E-Moniter");
        toolbar.inflateMenu(R.menu.logout_menu);

        //logout button
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.logout:
                        Intent myIntent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(myIntent);
                        finish();
                        return true;
                }
                return false;
            }

        });

        //setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.app_name));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tabLayout=findViewById(R.id.tabLayout);
       viewPager =findViewById(R.id.page1);
       viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
       viewPagerAdapter.addFragment(new Chart_Fragment(),"");
       viewPagerAdapter.addFragment(new Fragment2(),"");
       viewPager.setAdapter(viewPagerAdapter);
       tabLayout.setupWithViewPager(viewPager);
       //tabLayout.getTabAt(0).setIcon(R.drawable.icon);
       //tabLayout.getTabAt(1).setIcon(R.drawable.icon);
        tabLayout.getTabAt(0).setText("Activity");
        tabLayout.getTabAt(1).setText("Report Chart");

    }


}




