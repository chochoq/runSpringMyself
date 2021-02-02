package com.example.a1029;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    //블로그검색
    String url= "https://dapi.kakao.com/v2/search/blog";
    String query="android studio";
    ArrayList<HashMap<String,String>> array=new ArrayList<>();//키값과벨류값
    AdapterBlog adapter = new AdapterBlog();

    //메뉴이동
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.book:
                finish(); //클릭하고 이동했을때 현페이지를 닫아줌
                Intent intent=new Intent(MainActivity.this,MainActivity3.class);
                startActivity(intent);
                break;

            case R.id.local:
                finish();
                intent = new Intent(MainActivity.this,MainActivity4.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);

        MenuItem blog = menu.findItem(R.id.blog);
        blog.setVisible(false);

        MenuItem search=menu.findItem(R.id.search);
        SearchView searchView = (SearchView)search.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                query=newText;
                new kakaThread().execute();
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //액션바이름
        getSupportActionBar().setTitle("블로그검색");

        //실행
        new kakaThread().execute();


        //어댑터를 리스트뷰에
        ListView list=findViewById(R.id.list);
        list.setAdapter(adapter);
    }

    //카카오 쓰레드클래스
    //백스레드
    class kakaThread extends AsyncTask<String, String, String>{
        @Override
        protected String doInBackground(String... strings) {
            String result= kakao.connect(url+"?query="+query);
            return result;

        }
        //검색어 실행
        @Override
        protected void onPostExecute(String s) {
            //여러개의 데이터를 출력하려면 리스트뷰가 필요
            blogParser(s); //데이터생성

            //어댑터 생성
            adapter.notifyDataSetChanged();


            super.onPostExecute(s);
        }
    }

    //결과값을 보내주는? 파씽해주는작업(블로그파서)
    public void blogParser(String result){
        //오류가생길수가있어서 사용하는 트라이캐치
        try {
            //검색어가 누적되서 나와서 어레이를 지워줌
            array=new ArrayList<>();
            //다큐먼츠에 여러가지가 들어있어서 JSONArray에 넣음
            JSONArray jArray = new JSONObject(result).getJSONArray("documents");
            for(int i = 0; i<jArray.length(); i++){
                JSONObject obj = jArray.getJSONObject(i);
                HashMap<String,String> map = new HashMap<>();
                map.put("title",obj.getString("title"));
                map.put("url",obj.getString("url"));
                array.add(map);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    //어댑터정의
    class AdapterBlog extends BaseAdapter{

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
            view = getLayoutInflater().inflate(R.layout.item_blog,parent,false);
            TextView title = view.findViewById(R.id.title);
            TextView url=view.findViewById(R.id.url);

            final HashMap<String,String> map = array.get(position);
            title.setText(Html.fromHtml(map.get("title")));
            url.setText(Html.fromHtml("<u>"+map.get("url")+"</u>"));//언더라인태그 <u>

            //url을 클릭했을때 리스너
            url.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                    intent.putExtra("url",map.get("url"));
                    intent.putExtra("title",map.get("title"));
                    startActivity(intent);
                }
            });

            return view;
        }
    }



}