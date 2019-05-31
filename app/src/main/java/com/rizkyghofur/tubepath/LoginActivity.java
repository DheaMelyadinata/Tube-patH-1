package com.rizkyghofur.tubepath;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonSignIn;
    private Button buttonSignup;
    private EditText editTextEmail;
    private EditText editTextPassword;

    private FirebaseAuth firebaseAuth;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        }

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonSignIn = findViewById(R.id.btnsignin);
        buttonSignup  = findViewById(R.id.btnsignup);
        progressDialog = new ProgressDialog(this);

        buttonSignIn.setOnClickListener(this);
        buttonSignup.setOnClickListener(this);
    }

    private void userLogin(){
        String email = editTextEmail.getText().toString().trim();
        String password  = editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Masukkan email terlebih dahulu",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Masukkan kata sandi terlebih dahulu",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Proses login, Mohon tunggu");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this,"Login Sukses",Toast.LENGTH_LONG).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        }
                    else{
                        Toast.makeText(LoginActivity.this,"Login Error, cek kembali koneksi atau email dan kata sandi Anda",Toast.LENGTH_LONG).show();
                    }
                    }
                });

    }

    @Override
    public void onClick(View view) {
        if(view == buttonSignIn){
            userLogin();
        }

        if(view == buttonSignup){
            finish();
            startActivity(new Intent(this, RegisterActivity.class));
        }
    }
}