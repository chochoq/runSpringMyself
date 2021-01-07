package com.example.a1104;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChatActivity extends AppCompatActivity {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    String strEmail;
    ImageView btnSend;
    TextView edtContent;
    RecyclerView list;


    ArrayList<ChatVO> array = new ArrayList<>();

    ChatAdapter adapter = new ChatAdapter();

    //어댑터정의
    class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
        @NonNull
        @Override
        public ChatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.item_chat, parent, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ChatAdapter.ViewHolder holder, final int position) {
            //말풍선 오른쪽 보내기 1
            LinearLayout.LayoutParams pContent= (LinearLayout.LayoutParams)holder.txtContent.getLayoutParams();
            LinearLayout.LayoutParams pEmail= (LinearLayout.LayoutParams)holder.txtEmail.getLayoutParams();
            LinearLayout.LayoutParams pDate= (LinearLayout.LayoutParams)holder.txtDate.getLayoutParams();


            final ChatVO vo = array.get(position);
            holder.txtEmail.setText(vo.getEmail());
            holder.txtDate.setText(vo.getDate());
            holder.txtContent.setText(vo.getContent());

            //대화창에 메일주소와 서로 내용이 다르게
            if (vo.getEmail().equals(strEmail)) {
                holder.txtContent.setTextColor(Color.LTGRAY);
                holder.txtEmail.setVisibility(View.GONE);
                //말풍선 오른쪽 보내기 2
                pContent.gravity= Gravity.RIGHT;
                pEmail.gravity=Gravity.RIGHT;
                pDate.gravity=Gravity.RIGHT;

                holder.txtContent.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        final AlertDialog.Builder box = new AlertDialog.Builder(ChatActivity.this);
                        box.setItems(new String[]{"삭제", "복사"}, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(which==0){
                                    AlertDialog.Builder b = new AlertDialog.Builder(ChatActivity.this);
                                    b.setTitle("ask");
                                    b.setMessage(vo.getDate()+"delete?");
                                    b.setPositiveButton("y", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            //삭제
                                            ref=db.getReference("chats/"+vo.getDate());
                                            ref.removeValue();
                                            array.remove(position);
                                        }
                                    });
                                    b.setNegativeButton("n",null);
                                    b.show();
                                }
                            }
                        });
                        box.show();
                        return true;
                    }
                });

            } else {
                holder.txtContent.setTextColor(Color.BLUE);
                holder.txtEmail.setVisibility(View.VISIBLE);
                //말풍선 왼쪽 보내기
                pContent.gravity= Gravity.LEFT;
                pEmail.gravity=Gravity.LEFT;
                pDate.gravity=Gravity.LEFT;


            }


        }

        @Override
        public int getItemCount() {
            return array.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView txtContent, txtDate, txtEmail;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                txtContent = itemView.findViewById(R.id.txtContent);
                txtDate = itemView.findViewById(R.id.txtDate);
                txtEmail = itemView.findViewById(R.id.txtEmail);
            }
        }
    }


    //db사용시 필요
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        btnSend = findViewById(R.id.btnSend);
        edtContent = findViewById(R.id.edtContent);

        //파이어베이스아넹있는 email을 가져옴
        FirebaseUser user = mAuth.getCurrentUser();
        strEmail = user.getEmail();

        //액션바 이름 유저네임으로
        getSupportActionBar().setTitle(strEmail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//백버튼

        list = findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

        //샌드버튼을 누르면 저장
        ref = db.getReference("chats");
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                ChatVO vo = dataSnapshot.getValue(ChatVO.class);
                array.add(vo);
                list.scrollToPosition(array.size() - 1);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
               adapter.notifyDataSetChanged();//삭제후 바로 볼수있게 만들기
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strContent = edtContent.getText().toString();

                if (strContent.equals("")) {
                    AlertDialog.Builder box = new AlertDialog.Builder(ChatActivity.this);
                    box.setTitle("알림");
                    box.setMessage("내용");
                    box.setPositiveButton("닫기", null);
                    box.show();
                } else {
                    ChatVO vo = new ChatVO();
                    vo.setContent(strContent);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String strDate = sdf.format(new Date());
                    vo.setDate(strDate);
                    vo.setEmail(strEmail);
                    ref = db.getReference("chats").child(strDate);
                    ref.setValue(vo);
                    edtContent.setText("");

                }
            }
        });
    }

    //백버튼
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}