package com.example.ex01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {
        int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        final TextView txtResult = findViewById(R.id.txtResult);
        Button btnDe = findViewById(R.id.btnDe);
        Button btnIn = findViewById(R.id.btnIn);



        //감소버튼 클릭
        btnDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count--;
                txtResult.setText(count+"");
                //count+"" 이렇게 뒤에 문자를 넣어주면 문자로 바뀐다
            }
        });

        //증가버튼 클릭
        btnIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                txtResult.setText(count+"");
            }
        });

        //감소버튼 롱클릭
        btnDe.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                count=0;
                txtResult.setText("0");
                return true;
            }
        });

        //증가버튼 롱클릭
        btnIn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                count+=100;
                txtResult.setText(count+"");
                return true;
            }
        });
    }
}