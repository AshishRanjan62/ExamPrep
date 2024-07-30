package com.example.mainactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.widget.AdapterView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class dashboard extends AppCompatActivity {
    BottomNavigationView bottomview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        bottomview=findViewById(R.id.bottomview);
        bottomview.setItemIconTintList(null);


        bottomview.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id==R.id.home){
                    loadfrag(new home(),false);

                } else if (id==R.id.profile) {
                    loadfrag(new fragmentprofile(),false);


                }
                else{
                    loadfrag(new fragmentcontact(),false);

                }
                return true;
            }
        });
        bottomview.setSelectedItemId(R.id.home);

    }
    public void loadfrag(Fragment fragment,boolean flag){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        if(flag==true){
        fragmentTransaction.add(R.id.frame,fragment);
            fragmentTransaction.commit();
       }
        else{
            fragmentTransaction.replace(R.id.frame,fragment);
            fragmentTransaction.commit();

        }

    }

}