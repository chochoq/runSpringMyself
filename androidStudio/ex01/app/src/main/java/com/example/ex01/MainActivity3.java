package com.example.ex01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {
    ArrayList<String> array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        //데이터생성
        array=new ArrayList<String>();
        array.add("딸기");
        array.add("바나나");
        array.add("수박");
        array.add("포도");

        //어댑터생성 (어디에,모양,방식)
        final ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_single_choice,array);

        //어댑터>listView
        //싱글초이스는 하나씩 생길수있다
         final ListView list=findViewById(R.id.list);
        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        list.setAdapter(adapter);


        //add버튼 작동
        ImageView add=findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edit = findViewById(R.id.edit);
                String strEdit = edit.getText().toString();
                if(strEdit.length()==0){
                    Toast.makeText(MainActivity3.this,"값을입력하세요",
                            Toast.LENGTH_SHORT).show();
                }else{
                    array.add(strEdit);
                    //데이터가 바뀌었을때 리스트뷰에 반영(리프레시)
                    adapter.notifyDataSetChanged();
                    edit.setText("");
                }

            }
        });

        //remove버튼 작동
        ImageView remove = findViewById(R.id.remove);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //체크가됬을때
                int position=list.getCheckedItemPosition();
                array.remove(position);
                adapter.notifyDataSetChanged();

            }
        });

        //토스트
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String strItem = array.get(position);
                Toast.makeText(MainActivity3.this,
                        strItem,Toast.LENGTH_SHORT).show();
            }
        });
    }
}