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
import com.chhaichivion.mvvm.data.remote.response.User;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Copyright (c) PRASAC MFI, Ltd. All rights reserved. (https://www.prasac.com.kh/)
 * Author	: Chhai Chivon (chivon.chhai@prasac.com.kh) on 4/22/21.
 * Position : Senior Application Development Officer
 */
public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private List<User> users = new ArrayList<>();
    private Context mContext;

    public UserAdapter(Context context, List<User> users) {
        this.users = users;
        this.mContext = context;
    }
    
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // Set the name of the 'NicePlace'
        ((ViewHolder)holder).mName.setText(users.get(position).getUsername());

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
                .into(((ViewHolder)holder).mImage);
    }

    @Override
    public int getItemCount() {
        return users.size();
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
