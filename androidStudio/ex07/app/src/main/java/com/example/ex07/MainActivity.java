package com.example.ex07;

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
    EditText edtEmail, edtPW;
    Button btnJoin, btnLogin;
    private FirebaseAuth mAuth;
    String strEmail, strPW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //액션바바
       getSupportActionBar().setTitle("로그인");

        //파이어베이스생성
        mAuth = FirebaseAuth.getInstance();

        edtEmail = findViewById(R.id.edtEmail);
        edtPW = findViewById(R.id.edtPW);

        btnLogin = findViewById(R.id.btnLogin);
        //로그인4번
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strEmail=edtEmail.getText().toString();
                strPW=edtPW.getText().toString();
                loginUser(strEmail,strPW);
            }
        });

        btnJoin = findViewById(R.id.btnJoin);
        //가입2번
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strEmail=edtEmail.getText().toString();
                strPW=edtPW.getText().toString();
                if(strPW.length()<8){
                    Toast.makeText(MainActivity.this,"비밀번호8자리이상 입력하세요",Toast.LENGTH_SHORT).show();
                }else {
                    //아래에 만든 메일등록 클래스를 호출
                    registerUser(strEmail,strPW);
                }
            }
        });

    }

    //메일등록 1번
    public void registerUser(String strEmail, String strPW) {
        mAuth.createUserWithEmailAndPassword(strEmail, strPW)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "등록성공", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(MainActivity.this, "실패", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    //로그인 3번
    public void loginUser(String strEmail,String StrPW){
        mAuth.signInWithEmailAndPassword(strEmail,strPW).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"인증성공",Toast.LENGTH_SHORT).show();

                    //인증시 리스트로 이동
                    Intent intent = new Intent(MainActivity.this,ListActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(MainActivity.this,"인증실패",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
