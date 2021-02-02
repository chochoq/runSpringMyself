package com.example.ex07;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    FloatingActionButton btnWrite;
    ArrayList<memoVO> array = new ArrayList<>(); //해쉬맵대신사용가능한 어레이리스트~~ 어레이리스트를 사용하면  vo사용~~
    MemoAdapter adapter = new MemoAdapter();

    FirebaseAuth mAuth;
    FirebaseDatabase db;
    DatabaseReference ref;
    String strID;

    //어댑터정의 3(writeActivity만든후) 임포트 ㅇㄹ 시키는거
    class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.ViewHolder> {

        @NonNull
        @Override
        public MemoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = getLayoutInflater().inflate(R.layout.item_memo, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            //8
            final memoVO vo = array.get(position);

            if (vo.getUpdateData() == null) {
                holder.txtDate.setText(vo.getCreateDate());
                holder.txtDate.setTextColor(Color.BLUE);

            } else {
                holder.txtDate.setText(vo.getUpdateData());
                holder.txtDate.setTextColor(Color.RED);

            }
            holder.txtContent.setText(vo.getContent());


            //레이아웃 짝수별로 색깔바뀌게하기
            if (position % 3 == 0) {
                holder.layout.setBackgroundColor(Color.parseColor("#B9ABD1"));//컬러사용시 두가지방법

            } else if (position % 3 == 1) {
                holder.layout.setBackgroundColor(Color.WHITE);//컬러사용시 두가지방법

            } else {
                holder.layout.setBackgroundColor(Color.parseColor("#FF9800"));
            }
            holder.txtContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ListActivity.this, ReadActivity.class);

                    //수정시 정보를 담아가기
                    intent.putExtra("key", vo.getKey());
                    intent.putExtra("content", vo.getContent());
                    intent.putExtra("createDate", vo.getCreateDate());

                    startActivityForResult(intent, 2); //리퀘스트코드 2는 수정 1은 입력
                }
            });
            holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder box = new AlertDialog.Builder(ListActivity.this);
                    box.setTitle("ask");
                    box.setMessage("delete?");
                    box.setNegativeButton("n",null);
                    box.setPositiveButton("y", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mAuth = FirebaseAuth.getInstance();
                            FirebaseUser user = mAuth.getCurrentUser();
                            strID = user.getUid();
                            DatabaseReference dbref =db.getReference("memos/" + strID + "/" + vo.getKey());
                            dbref.removeValue();
                            readMemo();
                        }
                    });
                    box.show();
                }
            });


        }

        @Override
        public int getItemCount() {
            return array.size();
        }


        //7
        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView txtContent, txtDate;
            RelativeLayout layout;
            ImageView btnDelete;


            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                txtContent = itemView.findViewById(R.id.txtContent);
                txtDate = itemView.findViewById(R.id.txtDate);
                layout = itemView.findViewById(R.id.layout);
                btnDelete = itemView.findViewById(R.id.btnDelete);
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        getSupportActionBar().setTitle("메모장");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnWrite = findViewById(R.id.btnWrite);
        //글쓰기버튼을 눌렀을때 1
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this, WriteActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        //create 될때 4
        RecyclerView list = findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

        mAuth=FirebaseAuth.getInstance();



        //데이터만들기 5
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        strID = user.getUid();

        //id에있는 데이터?를 가져와 읽어줌 6
        getSupportActionBar().setTitle(user.getEmail());
        db = FirebaseDatabase.getInstance();
        ref = db.getReference("memos/" + strID);
        readMemo();

    }

    //메모 목록 읽기
    public void readMemo() {
        array = new ArrayList<>();
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                memoVO vo = (memoVO) dataSnapshot.getValue(memoVO.class);
                vo.setKey(dataSnapshot.getKey());
                array.add(vo);
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                adapter.notifyDataSetChanged();//삭제했을때 리프레쉬


            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


    //저장버튼을 눌렀을때 2
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //입력시
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Toast.makeText(this, "저장완료", Toast.LENGTH_SHORT).show();
            readMemo();

        } else if (requestCode == 1 && resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "취소", Toast.LENGTH_SHORT).show();

        }

        //수정시
        if (requestCode == 2 && resultCode == RESULT_OK) {
            Toast.makeText(this, "수정완료", Toast.LENGTH_SHORT).show();
            //수정완료시 리프레시해서 새로운 내용이 뜨게끔
            readMemo();
        } else if (requestCode == 2 && resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "취소", Toast.LENGTH_SHORT).show();
        }

        //삭제시
        if (requestCode == 2 && resultCode == 3) {
            Toast.makeText(this, "삭제완료", Toast.LENGTH_SHORT).show();
            //수정완료시 리프레시해서 새로운 내용이 뜨게끔
            readMemo();
        }
    }
}
