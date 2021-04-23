package com.chhaichivion.mvvm.data.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.chhaichivion.mvvm.data.remote.network.ApiService;
import com.chhaichivion.mvvm.data.remote.network.PhotoApi;
import com.chhaichivion.mvvm.data.remote.response.Photo;
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
public class PhotoRepository {

    private static PhotoRepository instance;
    private ArrayList<Photo> dataSet = new ArrayList<>();
    private PhotoApi photoApi;

    public static PhotoRepository getInstance(){
        if(instance == null){
            instance = new PhotoRepository();
        }
        return instance;
    }

    public PhotoRepository(){
        photoApi = ApiService.createService(PhotoApi.class);
    }

    public MutableLiveData<List<Photo>> getPhotos(){
        MutableLiveData<List<Photo>> data = new MutableLiveData<>();
        photoApi.getPhotosFromServer().enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                if (response.isSuccessful()){
                    List<Photo> photos = response.body();
                    Log.d("Response : ",new Gson().toJson(photos));
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                t.printStackTrace();
                data.setValue(null);
            }
        });
        data.setValue(dataSet);
        return data;
    }
}
