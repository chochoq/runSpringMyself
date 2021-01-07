package com.example.a1106;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.a1106.RemoteService.BASE_URL;

public class MainActivity extends AppCompatActivity {
    List<AddrVO> array = new ArrayList<>();

    AddAdapter adapter = new AddAdapter();

    Retrofit retrofit;
    RemoteService service;

    final static int ADD_ACTIVITY=1; //상수
    final static int EDIT_ACTIVITY=2; //상수

    //검색
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView)search.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                callData(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==ADD_ACTIVITY && resultCode ==RESULT_OK){
            Toast.makeText(this,"등록완료",Toast.LENGTH_SHORT).show();
            callData("");
        }else if(requestCode==ADD_ACTIVITY && resultCode==RESULT_CANCELED){
            Toast.makeText(this,"등록취소",Toast.LENGTH_SHORT).show();
        }
        if(requestCode==EDIT_ACTIVITY && resultCode ==RESULT_OK){
            Toast.makeText(this,"수정완료",Toast.LENGTH_SHORT).show();
            callData("");
        }else if(requestCode==EDIT_ACTIVITY && resultCode==RESULT_CANCELED){
            Toast.makeText(this,"수정취소",Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("주소목록");

        FloatingActionButton btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                startActivityForResult(intent,ADD_ACTIVITY);
            }
        });

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        service=retrofit.create(RemoteService.class);
        callData("");

        RecyclerView list = findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);
    }

    //ㅋ콜데이터를 만들어서 필요할때마다 쓰기위해 메소드화함
    public void callData(String query){
        Call<List<AddrVO>> call = service.list(query);
        call.enqueue(new Callback<List<AddrVO>>() {
            @Override
            public void onResponse(Call<List<AddrVO>> call, Response<List<AddrVO>> response) {
                array.remove(array);//모든내용이 지워지고 검색할때 검색한내용만 출력
                array=response.body();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<AddrVO>> call, Throwable t) {
                System.out.println(t.toString());
            }
        });
    }

    //주소어댑터정의
    class AddAdapter extends RecyclerView.Adapter<AddAdapter.ViewHolder>{

        @NonNull
        @Override
        public AddAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.item,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull AddAdapter.ViewHolder holder, final int position) {
            final AddrVO vo = array.get(position);

            holder.layout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    final AlertDialog.Builder box = new AlertDialog.Builder(MainActivity.this);
                    box.setTitle("메뉴선택");
                    box.setItems(new String[]{"수정", "삭제"}, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which){
                                case 0:
                                    Intent intent = new Intent(MainActivity.this,MainActivity3.class);
                                    intent.putExtra("seq",vo.getSeq());
                                    intent.putExtra("name",vo.getName());
                                    intent.putExtra("tel",vo.getTel());
                                    intent.putExtra("addr",vo.getAddr());

                                    startActivityForResult(intent,EDIT_ACTIVITY);
                                    break;
                                case 1:
                                    break;
                            }
                        }
                    });
                    box.show();
                    return true;
                }
            });

            holder.txtName.setText(vo.getName());
            holder.txtTel.setText(vo.getTel());
            holder.txtAdd.setText(vo.getAddr());

            //삭제버튼
            holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder box = new AlertDialog.Builder(MainActivity.this);
                    box.setTitle("ask");
                    box.setMessage("delete?");
                    box.setPositiveButton("y", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Call<Void> call = service.delete(vo.getSeq());
                            call.enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    Toast.makeText(MainActivity.this,"삭제완료",Toast.LENGTH_SHORT).show();
                                    array.remove(position);
                                    adapter.notifyDataSetChanged();
                                }

                                @Override
                                public void onFailure(Call<Void> call, Throwable t) {
                                    Toast.makeText(MainActivity.this,"삭제실패",Toast.LENGTH_SHORT).show();

                                }
                            });

                        }
                    });
                    box.setNegativeButton("n",null);
                    box.show();
                }
            });

        }

        @Override
        public int getItemCount() {
            return array.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView txtName, txtTel, txtAdd;
            Button btnDelete;

            RelativeLayout layout;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                layout=itemView.findViewById(R.id.layout);

                btnDelete = itemView.findViewById(R.id.btnDelete);

                txtName = itemView.findViewById(R.id.txtName);
                txtTel=itemView.findViewById(R.id.txtTel);
                txtAdd=itemView.findViewById(R.id.txtAdd);
            }
        }
    }


}