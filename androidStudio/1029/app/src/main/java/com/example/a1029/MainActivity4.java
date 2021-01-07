package com.example.a1029;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.renderscript.Matrix4f;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity4 extends AppCompatActivity {
    String url="https://dapi.kakao.com/v2/local/search/keyword.json?";
    String query="파파이스";
    ArrayList<HashMap<String,String>> array=new ArrayList<>();//파싱한후 해시맵에 넣어줌
    LocalAdapter adapter=new LocalAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("map");

        new KakaoThread().execute();
        //리스트뷰에 어댑터를 넣어줌
        ListView list=findViewById(R.id.list);
        list.setAdapter(adapter);
    }

    //파싱후 어댑터정의
    class LocalAdapter extends BaseAdapter{

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
            view=getLayoutInflater().inflate(R.layout.item_local,parent,false);

            final HashMap<String,String> map=array.get(position);

            TextView name = view.findViewById(R.id.name);
            //어드레스를 누르면
            TextView address=view.findViewById(R.id.address);
            //폰을 누르면 전화할수있게
            TextView phone = view.findViewById(R.id.tel);
            //마커를 클릭하면 맵을 볼수있게
            ImageView location = view.findViewById(R.id.location);

            name.setText(Html.fromHtml("<b>"+map.get("name")+"</b>"));
            address.setText(map.get("address"));
            phone.setText(Html.fromHtml(("<u>"+map.get("phone")+"</u>"))); //u태그 밑줄

            //마커를 클릭하면 맵을 볼수있게
            location.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity4.this,MapsActivity.class);
                    //마커위치를 원하는 곳에 넣어주기위하여
                    intent.putExtra("x",map.get("x"));
                    intent.putExtra("y",map.get("y"));
                    intent.putExtra("title",map.get("name"));
                    intent.putExtra("phone",map.get("phone"));

                    startActivity(intent);
                }
            });


            //전화걸기
            phone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent =new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+map.get("phone")));
                    startActivity(intent);
                }
            });

            //name을 클릭했을 때 url로 보내줄수있게
            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(MainActivity4.this,MainActivity2.class);
                    intent.putExtra("url",map.get("url"));
                    intent.putExtra("title",map.get("name"));
                    startActivity(intent);
                }
            });
            return view;
        }
    }

    //지역검색데이터 파싱
    public void localParser(String result){
        array = new ArrayList<>();
        //오류가생길수가있어서 사용하는 트라이캐치
        try {
            //검색어가 누적되서 나와서 어레이를 지워줌
            array=new ArrayList<>();
            //다큐먼츠에 여러가지가 들어있어서 JSONArray에 넣음
            JSONArray jArray = new JSONObject(result).getJSONArray("documents");
            for(int i = 0; i<jArray.length(); i++){
                JSONObject obj = jArray.getJSONObject(i);

                //뒤의 name쪽은 api가 정한이름으로
                HashMap<String,String> map = new HashMap<>();
                map.put("name",obj.getString("place_name"));
                map.put("address",obj.getString("address_name"));
                map.put("x",obj.getString("x"));
                map.put("y",obj.getString("y"));
                map.put("phone",obj.getString("phone"));
                map.put("url",obj.getString("place_url"));
                array.add(map);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //카카오쓰레드정의
    class KakaoThread extends AsyncTask<String,String,String>{
        //백스레드실행
        @Override
        protected String doInBackground(String... strings) {

            return kakao.connect(url+"query="+query);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            localParser(s);
            //어댑터를 파서할때마다 데이터를 바꾸라고 노티바이를 함
            adapter.notifyDataSetChanged();
        }
    }

    //메뉴버튼등록
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);

        //메뉴버튼중 지역검색(현재페이지) 없애기
        MenuItem local = menu.findItem(R.id.local);
        local.setVisible(false);

        //검색
        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView)search.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                query = newText;
                new KakaoThread().execute();
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    //메뉴이동
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.book:
                finish(); //클릭하고 이동했을때 현페이지를 닫아줌
                Intent intent=new Intent(MainActivity4.this,MainActivity3.class);
                startActivity(intent);
                break;

            case R.id.blog:
                finish();
                intent = new Intent(MainActivity4.this,MainActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}