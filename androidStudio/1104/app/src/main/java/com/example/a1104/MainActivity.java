package com.example.a1104;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText editEmail, edtPass;
    Button btnRegi, btnLogin;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("로그인");

        editEmail = findViewById(R.id.edtEmail);
        edtPass = findViewById(R.id.edtPass);
        btnLogin = findViewById(R.id.btLogin);
        btnRegi = findViewById(R.id.btRegister);
    }

    //mClick버튼을 눌렀을때
    public void mClick(View v) {
        String strEmail = editEmail.getText().toString();
        String strPass = edtPass.getText().toString();
        switch (v.getId()) {
            case R.id.btLogin:
                login(strEmail, strPass);
                break;

            case R.id.btRegister:
                register(strEmail, strPass);
                break;

        }
    }

    //로그인1
    public void login(String strEmail, String strPass) {
        mAuth.signInWithEmailAndPassword(strEmail, strPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "인증성공", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this,ChatActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "인증실패", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    //가입 ?..하지마..2
    public void register(String strEmail, String strPass) {
        mAuth.createUserWithEmailAndPassword(strEmail, strPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "가입성공", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "가입실패", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}