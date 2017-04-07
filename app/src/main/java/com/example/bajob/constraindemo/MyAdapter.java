package com.example.bajob.constraindemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by bajob on 4/7/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
    private List<String> commentList;
    public MyAdapter(List<String> commentList) {
        this.commentList = commentList;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new MyHolder(rootView);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.textView.setText(commentList.get(position));
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        public MyHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.row_text);
            imageView = (ImageView) itemView.findViewById(R.id.user_image);
        }
    }
}
