package com.example.a1029;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity3 extends AppCompatActivity {
    String url="https://dapi.kakao.com/v3/search/book?target=title";
    String query="안드로이드";

    //파싱해주기
    ArrayList<HashMap<String,String>> array = new ArrayList<>();
    AdapterBook adapter = new AdapterBook();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //액션바
        getSupportActionBar().setTitle("도서검색");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        new kakaoThread().execute();

        ListView list = findViewById(R.id.list);
        list.setAdapter(adapter);
    }
    //어댑터정의
    class  AdapterBook extends BaseAdapter{

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
            view = getLayoutInflater().inflate(R.layout.book,parent,false);
            TextView title = view.findViewById(R.id.title);
            TextView authors=view.findViewById(R.id.authors);
            TextView price = view.findViewById(R.id.price);
            ImageView image = view.findViewById(R.id.image);

            final HashMap<String,String> map = array.get(position);
            title.setText(map.get("title"));
            authors.setText(map.get("authors"));
            price.setText(map.get("price")+"won");

            //책사진이없을경우
            if(!map.get("image").equals("")){
                Picasso.with(MainActivity3.this). load(map.get("image")). into(image);
            }

            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LinearLayout layout = (LinearLayout)getLayoutInflater().inflate(R.layout.item_detail,null);
                    TextView contents = layout.findViewById(R.id.contents);
                    ImageView image = layout.findViewById(R.id.image);
                    contents.setText(map.get("contents"));
                    //책사진이없을경우
                    if(!map.get("image").equals("")){
                        Picasso.with(MainActivity3.this). load(map.get("image")). into(image);
                    }

                    AlertDialog.Builder box = new AlertDialog.Builder(MainActivity3.this);
                    box.setView(layout);
                    box.setPositiveButton("닫기",null);
                    box.show();
                }
            });


            return view;
        }
    }



    //결과 파싱
    public  void bookparser(String result){
        array=new ArrayList<>(); //검색할때마다 새로 어레이리스트를 만듦
        try{
            JSONArray jArray=new JSONObject(result).getJSONArray("documents");

            for (int i=0; i<jArray.length(); i++){
                JSONObject obj=jArray.getJSONObject(i);

                HashMap<String,String> map=new HashMap<>();
                map.put("title",obj.getString("title"));
                map.put("authors", obj.getString("authors"));
                map.put("price",obj.getString("price"));
                map.put("image",obj.getString("thumbnail"));
                map.put("contents",obj.getString("contents"));
//                map.put("company", obj.getString("company"));

                array.add(map);
            }

        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    //카카오에 접속하기 위한 쓰레드
    //카카오쓰레드정의
    class  kakaoThread extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {

            return kakao.connect(url+"&query="+query);//보내야하는 쿼리가 여러개일경우 &
        }

        @Override
        protected void onPostExecute(String s) {
           bookparser(s);
           adapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);

        //도서검색에 들어갔을때 메뉴에 도서검색이 보이지않게
        MenuItem book=menu.findItem(R.id.book);
        book.setVisible(false);

        MenuItem search=menu.findItem(R.id.search);
        SearchView searchView=(SearchView)search.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            //검색
            @Override
            public boolean onQueryTextChange(String newText) {
                query = newText;
                new kakaoThread().execute();
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    //메뉴이동
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.blog:
                Intent intent = new Intent(MainActivity3.this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.local:
                finish();
                intent = new Intent(MainActivity3.this,MainActivity4.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}