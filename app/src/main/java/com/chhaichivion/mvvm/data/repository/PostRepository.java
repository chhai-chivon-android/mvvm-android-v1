package com.chhaichivion.mvvm.data.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.chhaichivion.mvvm.data.remote.network.ApiService;
import com.chhaichivion.mvvm.data.remote.network.PostApi;
import com.chhaichivion.mvvm.data.remote.network.TodoApi;
import com.chhaichivion.mvvm.data.remote.response.Post;
import com.chhaichivion.mvvm.data.remote.response.Todo;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Copyright (c) PRASAC MFI, Ltd. All rights reserved. (https://www.prasac.com.kh/)
 * Author	: Chhai Chivon (chivon.chhai@prasac.com.kh) on 4/22/21.
 * Position : Senior Application Development Officer
 */
public class PostRepository {

    private static PostRepository instance;
    private ArrayList<Post> dataSet = new ArrayList<>();
    private PostApi postApi;

    public static PostRepository getInstance(){
        if(instance == null){
            instance = new PostRepository();
        }
        return instance;
    }

    public PostRepository(){
        postApi = ApiService.createService(PostApi.class);
    }

    public MutableLiveData<List<Post>> getPosts(){
        MutableLiveData<List<Post>> data = new MutableLiveData<>();
        postApi.getPostsFromServer().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()){
                    List<Post> posts = response.body();
                    Log.d("Response : ",new Gson().toJson(posts));
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                t.printStackTrace();
                data.setValue(null);
            }
        });
        data.setValue(dataSet);
        return data;
    }
}
