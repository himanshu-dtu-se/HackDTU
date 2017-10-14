package com.example.shivang.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText _email, _pass;

    private FirebaseAuth mAuth;

    private CheckBox checkbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser()!=null){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        checkbox = (CheckBox)findViewById(R.id.resiter_check);

        _email = (EditText)findViewById(R.id.login_email);
        _pass = (EditText)findViewById(R.id.login_pass);

        Button button = (Button)findViewById(R.id.login_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(checkbox.isChecked()){
                    register(_email.getText().toString(), _pass.getText().toString());
                }else {
                    login(_email.getText().toString(), _pass.getText().toString());
                }
            }
        });

    }

    private void register(String s, String s1) {

        final ProgressDialog dialog = new ProgressDialog(LoginActivity.this);
        dialog.setMessage("Please Wait... While we take care everything");
        dialog.setIndeterminate(false);
        dialog.show();

        mAuth.createUserWithEmailAndPassword(s,s1)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            dialog.dismiss();

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);

                            Toast.makeText(getApplicationContext(),"Logged In Successfully", Toast.LENGTH_SHORT).show();

                        }else{

                            dialog.dismiss();

                            Snackbar.make(getCurrentFocus(), "Registration Failed",Snackbar.LENGTH_LONG)
                                    .setAction("RETRY", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            register(_email.getText().toString(), _pass.getText().toString());
                                        }
                                    }).show();

                        }
                    }
                });

    }

    private void login(final String email, String pass) {

        final ProgressDialog dialog = new ProgressDialog(LoginActivity.this);
        dialog.setMessage("Please Wait... While we take care everything");
        dialog.setIndeterminate(false);
        dialog.show();


        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    dialog.dismiss();

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }else{

                    dialog.dismiss();

                    Snackbar.make(getCurrentFocus(), "Login Failed",Snackbar.LENGTH_LONG)
                            .setAction("RETRY", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    login(_email.getText().toString(), _pass.getText().toString());
                                }
                            }).show();



                }
            }
        });

    }
}
