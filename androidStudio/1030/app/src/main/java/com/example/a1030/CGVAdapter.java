package com.example.a1030;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class CGVAdapter extends RecyclerView.Adapter<CGVAdapter.ViewHolder> {
    //4번? 알트엔터해야함 ViewHolder에서 계속 빨간줄뜸

    Context context;
    ArrayList<HashMap<String,String>> array;
    //제네레이터에서 생성자 2 개만들기
    public CGVAdapter(Context context, ArrayList<HashMap<String, String>> array) {
        this.context = context;
        this.array = array;
    }


    @NonNull
    @Override
    public CGVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cgv,parent,false);
        return new ViewHolder(view);
    }
    //바인딩해주기
    @Override
    public void onBindViewHolder(@NonNull CGVAdapter.ViewHolder holder, int position) {
        holder.title.setText(array.get(position).get("title"));
        holder.rank.setText(array.get(position).get("rank"));
        Picasso.with(context).load(array.get(position).get("image")).into(holder.image);
        holder.date.setText(array.get(position).get("date"));
        holder.booking.setText(array.get(position).get("booking"));
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,rank,date,booking;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            rank=itemView.findViewById(R.id.rank);
            image=itemView.findViewById(R.id.image);
            date=itemView.findViewById(R.id.date);
            booking=itemView.findViewById(R.id.booking);

        }
    }
}
