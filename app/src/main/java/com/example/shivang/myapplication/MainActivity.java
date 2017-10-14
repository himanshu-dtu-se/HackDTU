package com.example.shivang.myapplication;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    display_selected_item(R.id.navigation_home);
                    return true;
                case R.id.navigation_dashboard:
                    display_selected_item(R.id.navigation_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    display_selected_item(R.id.navigation_notifications);
                    return true;
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        display_selected_item(R.id.navigation_home);
    }

    private void display_selected_item(int id) {

        Fragment fragment= null;

        switch(id){

            case R.id.navigation_home:
                fragment = new AllNewsFragment();
                break;

        }

        if(fragment!=null){
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.container, fragment);
            transaction.commit();
        }

    }


}
