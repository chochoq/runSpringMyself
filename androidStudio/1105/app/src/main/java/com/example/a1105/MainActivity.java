package com.example.a1105;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.a1105.RemoteService.BASE_URL;

public class MainActivity extends AppCompatActivity {
    List<ProductVO> array = new ArrayList<>();
    Retrofit retrofit;
    RemoteService service;
    //어댑터
    ProductAdapter adapter = new ProductAdapter();
    RecyclerView list;

    //검색을위한
    String query = "";

    String key = "code";
    String order = "asc";

    public void permissionCheck(){
        String perRead= Manifest.permission.READ_EXTERNAL_STORAGE;
        int granted= PackageManager.PERMISSION_GRANTED;

        if(ActivityCompat.checkSelfPermission(this,perRead) != granted){
            ActivityCompat.requestPermissions(this,new String[]{perRead},100);
        }
    }

    //어댑터정의 ->여기서 각각의 데이터 가져오고 넣어줄수있음
    class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

        @NonNull
        @Override
        public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, final int position) {
            final ProductVO vo = array.get(position);
            holder.txtPrice.setText(vo.getPrice());
            holder.txtName.setText(vo.getName());
            holder.txtCode.setText(vo.getCode());
            Picasso.with(MainActivity.this).load("http://192.168.0.106:8088/img/" + vo.getImage()).into(holder.image);
            holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder box = new AlertDialog.Builder(MainActivity.this);
                    box.setTitle("ask");
                    box.setMessage("지워?");
                    box.setPositiveButton("y", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Call<Void> call=service.delete(vo.getCode());
                            call.enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    array.remove(position);
                                    adapter.notifyDataSetChanged();
                                    Toast.makeText(MainActivity.this,"삭제완료",Toast.LENGTH_SHORT).show();
                                }
                                @Override
                                public void onFailure(Call<Void> call, Throwable t) {

                                }
                            });

                        }
                    });
                    box.setNegativeButton("n",null);
                    box.show();
                }
            });

            //상세페이지
            holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Call<ProductVO> call=service.read(vo.getCode());
                    call.enqueue(new Callback<ProductVO>() {
                        @Override
                        public void onResponse(Call<ProductVO> call, Response<ProductVO> response) {
                            ProductVO v = response.body();
                            LinearLayout layout =(LinearLayout)getLayoutInflater().inflate(R.layout.read,null,false);
                            ImageView img = layout.findViewById(R.id.image);
                            TextView name = layout.findViewById(R.id.txtName);
                            TextView code = layout.findViewById(R.id.txtCode);
                            TextView price = layout.findViewById(R.id.txtPrice);

                            name.setText(v.getName());
                            code.setText(v.getCode());
                            price.setText(v.getPrice()+"원");
                            Picasso.with(MainActivity.this).load("http://192.168.0.106:8088/img/" + v.getImage()).into(img);

                            AlertDialog.Builder box = new AlertDialog.Builder(MainActivity.this);
                            box.setView(layout);
                            box.setPositiveButton("닫기",null);
                            box.show();
                        }

                        @Override
                        public void onFailure(Call<ProductVO> call, Throwable t) {
                            System.out.println(t.toString());
                        }
                    });
                }
            });
        }
        @Override
        public int getItemCount() {
            return array.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView txtCode, txtName, txtPrice;
            ImageView image, btnDelete;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                txtCode = itemView.findViewById(R.id.txtCode);
                txtName = itemView.findViewById(R.id.txtName);
                txtPrice = itemView.findViewById(R.id.txtPrice);
                image = itemView.findViewById(R.id.image);
                btnDelete = itemView.findViewById(R.id.btnDelete);
            }
        }
    }

    //글쓰기
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==1){
            Toast.makeText(MainActivity.this,"등록완료",Toast.LENGTH_SHORT).show();
            callDate(query,key,order);
        }else if(resultCode==RESULT_CANCELED && requestCode==1){
            Toast.makeText(MainActivity.this,"등록취소",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        permissionCheck();
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("상품목록");

        FloatingActionButton btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                startActivityForResult(intent,1);
            }
        });

        //레트로핏 실행
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        service = retrofit.create(RemoteService.class);
        callDate(query, key, order);

        list = findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);
    }

    //Data Call
    public void callDate(String query, String key, String order) {
        Call<List<ProductVO>> call = service.list(query, key, order);

        call.enqueue(new Callback<List<ProductVO>>() {
            @Override
            public void onResponse(Call<List<ProductVO>> call, Response<List<ProductVO>> response) {
                array.removeAll(array);
                //웹에서 출력한 값들을 array에 넣어준다
                array = response.body();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ProductVO>> call, Throwable t) {
                System.out.println("에러유                : " + t.toString());
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        //삭제
        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) search.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                callDate(newText, key, order);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


    //검색
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.code:
                key = "code";
                break;
            case R.id.name:
                key = "name";
                break;
            case R.id.desc:
                key = "price";
                order = "desc";
                break;
            case R.id.asc:
                key = "price";
                order = "asc";
                break;
        }
        callDate(query, key, order);
        adapter.notifyDataSetChanged();
        return super.onOptionsItemSelected(item);
    }
}
