package com.example.a1106;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.a1106.RemoteService.BASE_URL;

public class MainActivity3 extends AppCompatActivity {
    EditText edtName,edtTel, edtAddr;
    int seq;

    Retrofit retrofit;
    RemoteService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getSupportActionBar().setTitle("수정");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent =getIntent();
        seq=intent.getIntExtra("seq",0);

        edtName=findViewById(R.id.edtName);
        edtTel=findViewById(R.id.edtTel);
        edtAddr=findViewById(R.id.edtAdd);

        edtName.setText(intent.getStringExtra("name"));
        edtTel.setText(intent.getStringExtra("tel"));
        edtAddr.setText(intent.getStringExtra("addr"));

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        service=retrofit.create(RemoteService.class);

        FloatingActionButton btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder box = new AlertDialog.Builder(MainActivity3.this);
                box.setTitle("ask");
                box.setMessage("ㅅ정?");
                box.setPositiveButton("y", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = edtName.getText().toString();
                        String tel = edtTel.getText().toString();
                        String addr = edtAddr.getText().toString();
                        Call<Void> call = service.update(name,tel,addr,seq);
                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                setResult(RESULT_OK);
                                finish();
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Toast.makeText(MainActivity3.this,"수정실패",Toast.LENGTH_SHORT).show();
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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}