package com.example.ex01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity4 extends AppCompatActivity {
    ArrayList<ItemVO> array = new ArrayList<ItemVO>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        //데이터생성
        ItemVO vo = new ItemVO("그램과 맥북 사고싶다", R.mipmap.ic_launcher,350);
        array.add(vo);

        vo = new ItemVO("LG 세탁기", R.drawable.ic_launcher_foreground,500);
        array.add(vo);

        vo = new ItemVO("횬다이", R.mipmap.ic_launcher,4500);
        array.add(vo);

        vo = new ItemVO("LG 냉장고", R.mipmap.ic_launcher,4400);
        array.add(vo);

        //어댑터생성
        MyAdapter adapter = new MyAdapter();

        //어댑터를 리스트뷰에 넣어줌
        ListView list = findViewById(R.id.list);
        list.setAdapter(adapter);
    }
    //어댑터정의
    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return array.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            view=getLayoutInflater().inflate(R.layout.item,parent,false);
            ImageView image = view.findViewById(R.id.image);
            TextView name = view.findViewById(R.id.name);
            TextView price = view.findViewById(R.id.price);

            ItemVO vo = array.get(position);
            image.setImageResource(vo.getImage());
            name.setText(vo.getName());
            price.setText(vo.getPrice()+"만원");
            return view;
        }
    }

    //옵션메뉴생성
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //옵션메뉴를 선택했을때의 이벤트
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent();

        switch (item.getItemId()){
            case R.id.item1 :
                intent = new Intent(this,MainActivity.class);
                break;
            case R.id.item2 :
                intent = new Intent(this,main2.class);
                break;
            case R.id.item3 :
                intent = new Intent(this,MainActivity3.class);
                break;
            case R.id.item4 :
                //main4는 뭐한게없어서 안뜨나버ㅏㅇ
                intent = new Intent(this,MainActivity4.class);
                break;
            case R.id.item5 :
                intent = new Intent(this,SubActivity.class);
                break;

        }
        //엑티비티로이동
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}