package com.example.a1028;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    DiaryDB db;
    SQLiteDatabase sql;
    DiaryAdapter adapter;
    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //add버튼을(플로트,글쓰기버튼) 눌렀을때 메인에서 메인2로 이동
        FloatingActionButton btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivityForResult(intent, 1);
            }
        });

        //액션바에 이름
        getSupportActionBar().setTitle("dairy");

        //db생성, 오픈
        db = new DiaryDB(this);
        sql = db.getWritableDatabase();


        //어댑터생성2
        cursor = sql.rawQuery("select * from diary order by date desc", null);
        adapter = new DiaryAdapter(this, cursor);

        //어댑터를 리스트뷰에 생성3
        ListView list = findViewById(R.id.list);
        list.setAdapter(adapter);

        registerForContextMenu(list);
    }

    //어댑터정의1
    class DiaryAdapter extends CursorAdapter {

        public DiaryAdapter(Context context, Cursor c) {
            super(context, c);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            //데이터가 들어있는 수만큼 item이 나옴
            View view = getLayoutInflater().inflate(R.layout.item, parent, false); //커서의갯수(다이어리테이블의 갯수)만큼 뷰가만들어짐
            return view;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            //db에 있는 데이터를 폰에서 볼수있게 넣어줌
            TextView date = view.findViewById(R.id.date);
            date.setText(cursor.getString(1));

            TextView subject = view.findViewById(R.id.subject);
            subject.setText(cursor.getString(2));

            TextView content = view.findViewById(R.id.content);
            content.setText(cursor.getString(3));


        }
    }

    //정렬과 서치바
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        //검색
        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) search.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }


            @Override
            public boolean onQueryTextChange(String query) {
                String str = "select * from diary where date like '%" + query + "%'";
                str += " or subject like '%" + query + "%'";
                str += " or content like '%" + query + "%'";
                try {
                    cursor = sql.rawQuery(str, null);
                    adapter.changeCursor(cursor);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String str = "";
        Intent intent = null;

        switch (item.getItemId()) {
            //일기장(main2)으로 이동
            case R.id.add:
                intent = new Intent(this, MainActivity2.class);
                startActivity(intent);
                break;
            //날짜정렬
            case R.id.orderDate:
                str = "select * from diary order by date";
                cursor = sql.rawQuery(str, null);
                break;
            //제목정렬
            case R.id.orderSubject:
                str = "select * from diary order by subject";
                cursor = sql.rawQuery(str, null);
                break;
        }
        adapter.changeCursor(cursor);
        return super.onOptionsItemSelected(item);
    }

    //입력후 출력값이 바로보이게(어플을 껏다키지않아도 바로 셀렉되서 보임)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            cursor = sql.rawQuery("select * from diary order by date desc", null);
            adapter.changeCursor(cursor);
            Toast.makeText(this, "save", Toast.LENGTH_SHORT).show();
        }
    }

    //컨텍스트메뉴생성
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("chooses menu");
        menu.add(0,1,0,"update");
        menu.add(0,2,0,"delete");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        final int id = cursor.getInt(0);

        switch (item.getItemId()){
            case 1:
                Intent intent = new Intent(MainActivity.this,MainActivity3.class);
                intent.putExtra("id",id);
                startActivityForResult(intent,1);
                break;

            case 2:
                AlertDialog.Builder box=new AlertDialog.Builder(this);
                box.setTitle("ask");
                box.setMessage(id+"delete?");
                box.setPositiveButton("y", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String str = "delete from diary where _id="+id;
                        sql.execSQL(str);


                        cursor=sql.rawQuery("select * from diary order by date desc",null);
                        adapter.changeCursor(cursor);
                    }
                });
                box.setNegativeButton("N",null);
                box.show();
                break;
        }

        return super.onContextItemSelected(item);
    }
}