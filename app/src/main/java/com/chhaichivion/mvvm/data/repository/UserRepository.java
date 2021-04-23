package com.chhaichivion.mvvm.data.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.chhaichivion.mvvm.data.remote.network.ApiService;
import com.chhaichivion.mvvm.data.remote.network.UserApi;
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
public class UserRepository {

    private static UserRepository instance;
    private ArrayList<User> dataSet = new ArrayList<>();
    private UserApi userApi;

    public static UserRepository getInstance(){
        if(instance == null){
            instance = new UserRepository();
        }
        return instance;
    }

    public UserRepository(){
        userApi = ApiService.createService(UserApi.class);
    }

    public MutableLiveData<List<User>> getUsers(){
        MutableLiveData<List<User>> data = new MutableLiveData<>();
        userApi.getUsersFromServer().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()){
                    List<User> users = response.body();
                    Log.d("Response : ",new Gson().toJson(users));
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                t.printStackTrace();
                 data.setValue(null);
            }
        });
        data.setValue(dataSet);
        return data;
    }
}
