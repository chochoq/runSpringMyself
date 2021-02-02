package com.example.a1104withoracle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.security.Provider;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.a1104withoracle.RemoteService.BASE_URL;

public class MainActivity extends AppCompatActivity {
    List<addVO> array = new ArrayList<>();
    AddAdapter addAdapter = new AddAdapter();
    RecyclerView list;
    RemoteService service;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list=findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(addAdapter);

        retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        service=retrofit.create(RemoteService.class);
        Call<List<addVO>> call= service.list("");
        call.enqueue(new Callback<List<addVO>>() {
            @Override
            public void onResponse(Call<List<addVO>> call, Response<List<addVO>> response) {
                array=response.body();
                addAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<addVO>> call, Throwable t) {

            }
        });


    }

    //주소어댑터
    class AddAdapter extends RecyclerView.Adapter<AddAdapter.ViewHolder>{

        @NonNull
        @Override
        public AddAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.item_xml,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull AddAdapter.ViewHolder holder, int position) {
            addVO vo = array.get(position);
            holder.txtTel.setText(vo.getTel());
            holder.txtAddr.setText(vo.getAddr());
            holder.txtName.setText(vo.getName());

        }

        @Override
        public int getItemCount() {
            return array.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView txtName, txtTel, txtAddr;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                txtName=itemView.findViewById(R.id.txtName);
                txtAddr=itemView.findViewById(R.id.txtAddr);
                txtTel=itemView.findViewById(R.id.txtTel);
            }
        }
    }
}