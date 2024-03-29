package com.chhaichivion.mvvm.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.chhaichivion.mvvm.data.remote.response.Photo;
import com.chhaichivion.mvvm.data.repository.PhotoRepository;

import java.util.List;

/**
 * Copyright (c) PRASAC MFI, Ltd. All rights reserved. (https://www.prasac.com.kh/)
 * Author	: Chhai Chivon (chivon.chhai@prasac.com.kh) on 4/22/21.
 * Position : Senior Application Development Officer
 */
public class PhotoViewModel extends ViewModel {

    private Context context;
    private MutableLiveData<List<Photo>> mPhotoMutableLiveData;
    private PhotoRepository mRepo;

    public void init(Context context){
        this.context = context;
        if(mPhotoMutableLiveData != null){
            return;
        }
        mRepo = PhotoRepository.getInstance(context);
        mPhotoMutableLiveData = mRepo.getPhotos();
    }

    public LiveData<List<Photo>> getPhotosRepository(){
        return mPhotoMutableLiveData;
    }
}
