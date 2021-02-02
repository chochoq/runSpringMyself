package com.example.a1108inhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    TextInputEditText TextInputEditText_Email, TextInputEditText_PW;
    RelativeLayout RelativeLayout_Login;
    LinearLayout LinearLayout_LoginFB;
    TextView TextView_finPW,TextView_join;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextInputEditText_Email = findViewById(R.id.TextInputEditText_Email);
        TextInputEditText_PW = findViewById(R.id.TextInputEditText_PW);
        RelativeLayout_Login = findViewById(R.id.RelativeLayout_Login);
        LinearLayout_LoginFB = findViewById(R.id.LinearLayout_LoginFB);
        TextView_finPW = findViewById(R.id.TextView_finPW);
        TextView_join = findViewById(R.id.TextView_join);

        /*
        * 1.값을 가져온다
        * 2.클릭을 감지한다
        * 3.값을 다른 액티비티로 옮긴다(화면이동)
        * */



        RelativeLayout_Login.setClickable(true);
        RelativeLayout_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  edtEmail = TextInputEditText_Email.getText().toString();
                String  password = TextInputEditText_PW.getText().toString();

                Intent intent = new Intent();
                intent.putExtra();
            }
        });

    }
}