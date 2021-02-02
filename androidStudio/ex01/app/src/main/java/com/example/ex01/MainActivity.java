package com.example.ex01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        //메인엑티비티
        Button btnPrint = findViewById(R.id.btnPrint);
        final EditText editName = findViewById(R.id.edtName);
        final EditText detAdd=findViewById(R.id.edtAdd);

        //클릭이벤트를 저장
        btnPrint.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                EditText edtName=findViewById(R.id.edtName);
                EditText edtAdd=findViewById(R.id.edtAdd);
                String strName=edtName.getText().toString();
                String strAdd=edtAdd.getText().toString();

                Toast.makeText(MainActivity.this, strName+"\n"+strAdd, Toast.LENGTH_SHORT).show();


            }
        }
);
    }
}