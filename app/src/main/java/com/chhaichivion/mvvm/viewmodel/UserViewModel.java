package com.chhaichivion.mvvm.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.chhaichivion.mvvm.data.remote.response.User;
import com.chhaichivion.mvvm.data.repository.UserRepository;

import java.util.List;

/**
 * Copyright (c) PRASAC MFI, Ltd. All rights reserved. (https://www.prasac.com.kh/)
 * Author	: Chhai Chivon (chivon.chhai@prasac.com.kh) on 4/22/21.
 * Position : Senior Application Development Officer
 */
public class UserViewModel extends ViewModel {

    private MutableLiveData<List<User>> mUserMutableLiveData;
    private UserRepository mRepo;

    public void init(){
        if(mUserMutableLiveData != null){
            return;
        }
        mRepo = UserRepository.getInstance();
        mUserMutableLiveData = mRepo.getUsers();
    }

    public LiveData<List<User>> getUsersRepository(){
        return mUserMutableLiveData;
    }
}
