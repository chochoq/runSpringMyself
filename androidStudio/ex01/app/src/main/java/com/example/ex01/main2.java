package com.example.ex01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class main2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView image=findViewById(R.id.image);
                Button btn=findViewById(R.id.btn);

                if(image.getVisibility() == View.VISIBLE){
                    //버튼누르면 이미지보이기
                   image.setVisibility(View.INVISIBLE);
                   btn.setText("보이기");
                }else {
                    //버튼누르면 이미지 안보이기
                    image.setVisibility(View.VISIBLE);
                    btn.setText("숨기기");
                }
            }
        });
    }
}