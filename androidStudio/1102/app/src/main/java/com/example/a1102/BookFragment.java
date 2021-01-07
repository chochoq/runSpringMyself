package com.example.a1102;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class BookFragment extends Fragment {
    String url="https://openapi.naver.com/v1/search/book.json";
    String query="안드로이드";
    RecyclerView list;
    ArrayList<HashMap<String,String>> array = new ArrayList<>();
    BookAdapter adapter = new BookAdapter();
    EditText search;
    int start = 1;


        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_book, container, false);

            //검색
            search=view.findViewById(R.id.search);
            //입력했을때의 이벤트
            search.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    start = 1;
                    query=search.getText().toString();
                    array=new ArrayList<>();
                    new NaverThread().execute();
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });

            new NaverThread().execute();
            list=view.findViewById(R.id.list);
            list.setLayoutManager(new LinearLayoutManager(getActivity()));
            list.setAdapter(adapter);

            //스크롤이 바뀔때마다 10개씩 더 볼수있게
            list.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                   if(!list.canScrollVertically(1)){
                            start +=10;
                            new NaverThread().execute();
                   }
                }
            });
            return view;
        }

        //도서검색 어댑터 파싱
        class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder>{

            @NonNull
            @Override
            public BookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = getLayoutInflater().inflate(R.layout.item_book,parent,false);
                return new ViewHolder(view); //리턴해주는게 ViewHolder라서 ViewHolder에 넣어줘서 리턴
            }
            @Override
            public void onBindViewHolder(@NonNull BookAdapter.ViewHolder holder, int position) {
                HashMap<String,String> map = array.get(position);
                holder.title.setText(Html.fromHtml(map.get("title")));
                holder.author.setText(Html.fromHtml(map.get("author")));
                holder.price.setText(map.get("price"));
                Picasso.with(getActivity()).load(map.get("image")).into(holder.image);
            }
            @Override
            public int getItemCount() {
                return array.size();
            }

            public class ViewHolder extends RecyclerView.ViewHolder {
                TextView title,author,price;
                ImageView image;
                public ViewHolder(@NonNull View itemView) {
                    super(itemView);
                    title=itemView.findViewById(R.id.title);
                    author=itemView.findViewById(R.id.author);
                    image=itemView.findViewById(R.id.image);
                    price=itemView.findViewById(R.id.price);
                }
            }
        }


        //네이버접속 쓰레드
        class NaverThread extends AsyncTask<String,String,String> {

            @Override
            protected String doInBackground(String... strings) {
                return NaverAPI.connect(url,query,start);
            }

            @Override
            protected void onPostExecute(String s) {
                try{
                    JSONArray JArray= new JSONObject(s).getJSONArray("items");
                    for(int i=0; i<JArray.length(); i++){
                        JSONObject obj = JArray.getJSONObject(i);

                        HashMap<String,String> map = new HashMap<>();
                        map.put("title",obj.getString("title"));
                        map.put("author", obj.getString("author"));
                        map.put("price", obj.getString("price"));
                        map.put("image", obj.getString("image"));

                        array.add(map);

                    }
                    System.out.println("사이즈    :   " +array.size());

                }catch (Exception e){
                }
                adapter.notifyDataSetChanged();

                super.onPostExecute(s);
            }
        }
    }



