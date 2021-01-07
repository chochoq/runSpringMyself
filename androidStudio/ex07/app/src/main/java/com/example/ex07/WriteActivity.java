package com.example.ex07;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WriteActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseDatabase db;
    DatabaseReference ref;
    String strID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        getSupportActionBar().setTitle("작성");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //mauth 가져오기
        mAuth=FirebaseAuth.getInstance();
        //유저정보가져오기
        FirebaseUser user= mAuth.getCurrentUser();
        //유저의 이메일알기 >e메일의 @가 안먹혀서  getUid로 받음
        strID = user.getUid();

        //db생성
        db = FirebaseDatabase.getInstance();


//        System.out.println("메일                            > "+strEmail);


        final EditText edtContent = findViewById(R.id.edtContent);

        FloatingActionButton btnSave=findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strContent=edtContent.getText().toString();

                //메모에 내용이 있거나 없을때
                if(strContent.equals("")){
                    Toast.makeText(WriteActivity.this,"내용을 입력해주세요",Toast.LENGTH_SHORT).show();
                }else{
                    //내용이있으면 메모저장
                    memoVO vo =new memoVO();
                    vo.setContent(strContent);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
                    String strDate = sdf.format(new Date());
                    vo.setCreateDate(strDate);

                    ref = db.getReference("memos").child(strID).push();
                    ref.setValue(vo);
                    setResult(RESULT_OK);
                    finish();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}