package com.example.a1102;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class ShoppingFragment extends Fragment {
    String url="https://openapi.naver.com/v1/search/shop.json";
    String query="";
    int start = 1;
    RecyclerView list;
    ArrayList<HashMap<String,String>> array = new ArrayList<>();
    ShopAdater adapter = new ShopAdater();
    EditText search;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book, container, false);

        search= view.findViewById(R.id.search);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int intStart, int before, int count) {
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


    //검색 어탭터 파싱
    class ShopAdater extends RecyclerView.Adapter<ShopAdater.ViewHolder>{


        @NonNull
        @Override
        public ShopAdater.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.item_shop,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ShopAdater.ViewHolder holder, int position) {
            HashMap<String,String> map = array.get(position);
            holder.title.setText(Html.fromHtml(map.get("title")));
            holder.mallName.setText(Html.fromHtml(map.get("mallName")));
            holder.lprice.setText(map.get("lprice"));
            if(!map.get("image").equals("")){
                Picasso.with(getActivity()).load(map.get("image")).into(holder.image);
            }

        }

        @Override
        public int getItemCount() {return array.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView title,mallName,lprice;
            ImageView image;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                title=itemView.findViewById(R.id.title);
                mallName=itemView.findViewById(R.id.mallName);
                image=itemView.findViewById(R.id.image);
                lprice=itemView.findViewById(R.id.lprice);

            }
        }
    }

    class NaverThread extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {
            return NaverAPI.connect(url,query,start);
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONArray jArray = new JSONObject(s).getJSONArray("items");
                for(int i = 0; i<jArray.length(); i++){
                    JSONObject obj = jArray.getJSONObject(i);

                    HashMap<String,String> map = new HashMap<>();
                    map.put("title",obj.getString("title"));
                    map.put("mallName",obj.getString("mallName"));
                    map.put("lprice",obj.getString("lprice"));
                    map.put("image",obj.getString("image"));

                    array.add(map);
                }
                System.out.println("사이즈"+array.size());

            }catch (Exception e){
                e.printStackTrace();
            }
            adapter.notifyDataSetChanged();

            super.onPostExecute(s);
        }
    }
}