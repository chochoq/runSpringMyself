package com.example.a1028;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {
    int id;
    DiaryDB db;
    SQLiteDatabase sql;
    Cursor cursor;
    EditText subject, content;
    TextView date;
    int mYear, mMonth, mDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //액션바이름, 뒤로가기버튼
        getSupportActionBar().setTitle("update");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        id=intent.getIntExtra("id",0);

        subject=findViewById(R.id.subject);
        content=findViewById(R.id.content);
        date=findViewById(R.id.date);

        db=new DiaryDB(this);
        sql=db.getWritableDatabase();
        //수정작업 시 원래 데이터가 들어갈수있게
        cursor=sql.rawQuery("select * from diary where _id="+id,null);
        if(cursor.moveToNext()){
            date.setText(cursor.getString(1));
            subject.setText(cursor.getString(2));
            content.setText(cursor.getString(3));

        }
    }


    //달력과 세이브버튼을 사용하기위해서

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public  void  mClick(View v){

        String  strDate=date.getText().toString();
        mYear = Integer.parseInt(strDate.substring(0,4));
        mMonth = Integer.parseInt(strDate.substring(5,7))-1;
        mDay =Integer.parseInt(strDate.substring(8,10));


        switch (v.getId()){
            //달력버튼눌렀을때
            case R.id.btnCal :
                new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mYear=year;
                        mMonth=month;
                        mDay=dayOfMonth;
                        updateDate();
                    }
                }, mYear, mMonth, mDay).show();
                break;

            //저장버튼눌렀을때
            case R.id.btnSave:
                AlertDialog.Builder box = new AlertDialog.Builder(this);
                box.setTitle("질의");
                box.setMessage("수정?");
                box.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String strDate = date.getText().toString();
                        String strSubject = subject.getText().toString();
                        String strContent = content.getText().toString();

                        String str = "update diary set date='" + strDate + "',";
                        str += "subject='" + strSubject + "',";
                        str += "content='" + strContent + "'";
                        str += "where _id=" +id;
                        sql.execSQL(str);
                        setResult(RESULT_OK);

                        finish();
                    }
                });
                box.setNegativeButton("n",null);
                box.show();
                break;
        }


    }


    //달력을사용하기위해서
    //날짜출력 %d-%02d-%02d 는 09-01 이렇게 출력하기위해서, 달에 +1을 하는 이유는 0월부터시작해서
    private void updateDate(){
        date.setText(String.format("%d-%02d-%02d",mYear,mMonth+1,mDay));
    }
}