package com.chhaichivion.mvvm.data.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.chhaichivion.mvvm.data.remote.network.ApiService;
import com.chhaichivion.mvvm.data.remote.network.TodoApi;
import com.chhaichivion.mvvm.data.remote.response.Todo;
import com.chhaichivion.mvvm.data.remote.response.User;
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
public class TodoRepository {

    private static TodoRepository instance;
    private ArrayList<Todo> dataSet = new ArrayList<>();
    private TodoApi todoApi;

    public static TodoRepository getInstance(){
        if(instance == null){
            instance = new TodoRepository();
        }
        return instance;
    }

    public TodoRepository(){
        todoApi = ApiService.createService(TodoApi.class);
    }

    public MutableLiveData<List<Todo>> getTodos(){
        MutableLiveData<List<Todo>> data = new MutableLiveData<>();
        todoApi.getTodosFromServer().enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                if (response.isSuccessful()){
                    List<Todo> todos = response.body();
                    Log.d("Response : ",new Gson().toJson(todos));
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Todo>> call, Throwable t) {
                t.printStackTrace();
                data.setValue(null);
            }
        });
        data.setValue(dataSet);
        return data;
    }
}
