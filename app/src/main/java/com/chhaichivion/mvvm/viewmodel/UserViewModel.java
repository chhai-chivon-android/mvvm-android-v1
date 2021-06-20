package com.chhaichivion.mvvm.viewmodel;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.chhaichivion.mvvm.data.remote.response.User;
import com.chhaichivion.mvvm.data.repository.UserRepository;
import com.chhaichivion.mvvm.ui.listener.OnViewModelListener;
import com.chhaichivion.mvvm.utility.AppHelper;

import java.util.List;

/**
 * Copyright (c) PRASAC MFI, Ltd. All rights reserved. (https://www.prasac.com.kh/)
 * Author	: Chhai Chivon (chivon.chhai@prasac.com.kh) on 4/22/21.
 * Position : Senior Application Development Officer
 */
public class UserViewModel extends ViewModel implements OnViewModelListener<User> {

    private Context context;
    private MutableLiveData<List<User>> mUserMutableLiveData;
    private UserRepository mRepo;

    public void init(Context context){
        this.context = context;
        if(mUserMutableLiveData != null){
            return;
        }
        mRepo = UserRepository.getInstance(context);
        mUserMutableLiveData = mRepo.getUsers();
    }

    public LiveData<List<User>> getUsersRepository(){
        return mUserMutableLiveData;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onStarted() {
        if(AppHelper.getInstance().isNetworkConnected(context)){

        }else{

        }
    }

    @Override
    public void onSuccess(User response) {

    }

    @Override
    public void onFailure(String message) {

    }
}
