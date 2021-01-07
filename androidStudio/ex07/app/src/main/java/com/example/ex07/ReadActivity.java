package com.example.ex07;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReadActivity extends AppCompatActivity {
    EditText edtContent;
    memoVO vo = new memoVO();
    FirebaseDatabase db ;
    FirebaseAuth mAuth;
    DatabaseReference ref;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        getSupportActionBar().setTitle("글수정");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //수정정보 그대로 가져오기 2
        Intent intent = getIntent();
        vo.setKey(intent.getStringExtra("key"));
        vo.setContent(intent.getStringExtra("content"));
        vo.setCreateDate(intent.getStringExtra("createDate"));
        vo.setUpdateData(intent.getStringExtra("updateDate"));

        edtContent = findViewById(R.id.edtContent);
        edtContent.setText(vo.getContent());

        mAuth=FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String strID = user.getUid();

        db=FirebaseDatabase.getInstance();
        ref=db.getReference("memos/"+strID+"/"+vo.getKey());

        //플로팅액션버튼 3
        FloatingActionButton btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            //수정완료버튼눌렀을때
            @Override
            public void onClick(View v) {
                AlertDialog.Builder box = new AlertDialog.Builder(ReadActivity.this);
                box.setTitle("ask");
                box.setMessage("updatE?");
                //수정완료 y버튼눌렀을때 업데이트시키기
                box.setPositiveButton("y", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        vo.setContent(edtContent.getText().toString());
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String strDate = sdf.format(new Date());
                        vo.setUpdateData(strDate);
                        ref.setValue(vo);
                        setResult(RESULT_OK);
                        finish();
                    }
                });
                box.setNegativeButton("n",null);
                box.show();
            }
        });
    }

    //1
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.itemDelete:
                AlertDialog.Builder box = new AlertDialog.Builder(this);
                box.setTitle("ask");
                box.setMessage("delete?");
                box.setNegativeButton("n",null);
                box.setPositiveButton("y", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ref.removeValue();
                        setResult(3);
                        finish();
                    }
                });
                box.show();

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}