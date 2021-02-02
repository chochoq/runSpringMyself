package com.example.a1030;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    //null포인트 익셉션문제는 정의안해줘서
    ArrayList<HashMap<String, String>> array = new ArrayList<>();
    ArrayList<HashMap<String, String>> wArray = new ArrayList<>();

    //날씨
    int index=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new CGVTread().execute();
        new DaumThread().execute();


    }

    //1초간격으로 날씨출력하는 쓰레드
    class WeatherThread extends Thread{
        @Override
        public void run() {
            super.run();
            while (true){
                if(index== wArray.size()-1) index=0;
                try {
                    //아래의 핸들러랑연결
                    handler.sendEmptyMessage(0);

                    //쓰레드를 2초동안 쉬고있게만듬
                    Thread.sleep(2000);
                    index++;
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    //날씨를 핸들바에 출력
    Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            String part= wArray.get(index).get("part");
            String temper= wArray.get(index).get("temper");
            String ico= wArray.get(index).get("ico");
            getSupportActionBar().setTitle(part+":"+temper+"℃"+"/"+ico);
        }
    };

    //다음날씨 쓰레드정의
    class DaumThread extends AsyncTask<String, String, String> {
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            WeatherThread weatherThread = new WeatherThread();
            weatherThread.setDaemon(true);
            weatherThread.start();
        }


        @Override
        protected String doInBackground(String... strings) {

            try{
                //백그라운드에서 인터넷접속
                Document doc = Jsoup.connect("https://www.daum.net/").get();
                //무비차트안에 ol을 가져온다 여러개를가져와서 Elements
                Elements es = doc.select(".list_weather");

                //하나만가져와서Element
                for (Element e : es.select("li")) {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("part", e.select(".txt_part").text());
                    map.put("temper", e.select(".txt_temper").text());
                    map.put("ico", e.select(".ico_ws").text());
                    wArray.add(map);
                }
                System.out.println("몇개야:   "+ wArray.size());
            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }



            //CGV 크롤링 백스레드 정의
            class CGVTread extends AsyncTask<String, String, String> {

                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);

                    RecyclerView list = findViewById(R.id.list);
                    list.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    CGVAdapter adapter = new CGVAdapter(MainActivity.this, array);
                    list.setAdapter(adapter);
                }

                @Override
                protected String doInBackground(String... strings) {
                    try {
                        //백그라운드에서 인터넷접속
                        Document doc = Jsoup.connect("http://www.cgv.co.kr/movies/").get();
                        //무비차트안에 ol을 가져온다 여러개를가져와서 Elements
                        Elements es = doc.select(".sect-movie-chart ol");
                        //하나만가져와서Element
                        for (Element e : es.select("li")) {
                            HashMap<String, String> map = new HashMap<>();
                            map.put("rank", e.select(".rank").text());
                            map.put("title", e.select(".title").text());
                            map.put("image", e.select("img").attr("src"));
                            map.put("date", e.select(".txt-info strong").text());
                            map.put("booking", e.select(".score .percent").text().split(" ")[0]);

                            if (!e.select(".rank").text().equals("")) {
                                array.add(map);
                            }


                        }
                        System.out.println("몇개? : " + array.size());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            }

        }