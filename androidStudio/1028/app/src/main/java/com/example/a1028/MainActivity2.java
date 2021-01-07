package com.example.a1028;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity2 extends AppCompatActivity {
    int mYear, mMonth, mDay;
    TextView date;
    DiaryDB db;
    SQLiteDatabase sql;
    EditText subject, content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        date=findViewById(R.id.date);

        db=new DiaryDB(this);
        sql=db.getWritableDatabase();

        subject=findViewById(R.id.subject);
        content=findViewById(R.id.content);

        //액션바의 이름과 뒤로가기버튼
        getSupportActionBar().setTitle("글쓰기");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //달력선언실행
        Calendar cal=new GregorianCalendar();
        mYear=cal.get(Calendar.YEAR);
        mMonth=cal.get(Calendar.MONTH);
        mDay=cal.get(Calendar.DAY_OF_MONTH);
        updateDate();

        //달력아이콘클릭시 날짜 바뀌는거
        ImageView btnCal=findViewById(R.id.btnCal);
        btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(MainActivity2.this,null,mYear,mMonth,mDay).show();
            }
        });
    }

    //날짜출력 %d-%02d-%02d 는 09-01 이렇게 출력하기위해서, 달에 +1을 하는 이유는 0월부터시작해서
    private void updateDate(){
        date.setText(String.format("%d-%02d-%02d",mYear,mMonth+1,mDay));
    }

    public  void  mClick(View v){
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
                AlertDialog.Builder box =new AlertDialog.Builder(this);
                box.setTitle("ask");
                box.setMessage("save?");
                box.setPositiveButton("y", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String strDate=date.getText().toString();
                        String strSubject=subject.getText().toString();
                        String strContent =content.getText().toString();
                        String str = "insert into diary(date,subject,content) values(";
                        str+="'"+strDate+"',";
                        str+="'"+strContent+"',";
                        str+="'"+strContent+"')";
                        sql.execSQL(str);
                        finish();
                    }
                });
                box.setNegativeButton("n",null);
                box.show();
                break;
        }


    }

    //뒤로가기버튼
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}