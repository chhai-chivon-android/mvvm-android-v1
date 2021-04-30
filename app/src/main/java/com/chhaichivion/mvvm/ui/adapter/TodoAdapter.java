package com.chhaichivion.mvvm.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chhaichivion.mvvm.R;
import com.chhaichivion.mvvm.data.remote.response.Todo;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Copyright (c) PRASAC MFI, Ltd. All rights reserved. (https://www.prasac.com.kh/)
 * Author	: Chhai Chivon (chivon.chhai@prasac.com.kh) on 4/23/21.
 * Position : Senior Application Development Officer
 */
public class TodoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private Context mContext;
    private List<Todo> todos;


    public TodoAdapter(Context mContext,List<Todo> todos) {
        this.mContext = mContext;
        this.todos = todos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item_layout, parent, false);
        TodoAdapter.ViewHolder vh = new TodoAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // Set the name of the 'NicePlace'
        ((TodoAdapter.ViewHolder)holder).mName.setText(todos.get(position).getTitle());

        String imageUrl = "https://pas-wordpress-media.s3.amazonaws.com/wp-content/uploads/2014/01/Freelance-Writer-1024x743.jpg";

        // Set the image
        RequestOptions defaultOptions = new RequestOptions()
                .error(R.drawable.ic_launcher_background);
//        Glide.with(mContext)
//                .setDefaultRequestOptions(defaultOptions)
//                .load(users.get(position).getImageUrl())
//                .into(((ViewHolder)holder).mImage);
        Glide.with(mContext)
                .setDefaultRequestOptions(defaultOptions)
                .load(imageUrl)
                .into(((TodoAdapter.ViewHolder)holder).mImage);
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder{

        private CircleImageView mImage;
        private TextView mName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.image);
            mName = itemView.findViewById(R.id.image_name);
        }
    }
}
