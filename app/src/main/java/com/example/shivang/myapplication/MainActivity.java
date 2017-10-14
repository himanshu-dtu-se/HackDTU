package com.example.shivang.myapplication;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;

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

        mAuth = FirebaseAuth.getInstance();

        display_selected_item(R.id.navigation_home);
    }

    private void display_selected_item(int id) {

        Fragment fragment= null;

        switch(id){

            case R.id.navigation_home:
                fragment = new AllNewsFragment();
                break;

            case R.id.navigation_dashboard:
                fragment = new MyQueries();
                break;

            case R.id.navigation_notifications:
                fragment = new AllQueries();
                break;

        }

        if(fragment!=null){
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.container, fragment);
            transaction.commit();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.logout:

                    mAuth.signOut();
                Toast.makeText(getApplicationContext(), "Sign out successful", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                break;
        }
        return true;
    }

}
