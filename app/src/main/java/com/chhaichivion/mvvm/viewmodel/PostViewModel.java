package com.chhaichivion.mvvm.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.chhaichivion.mvvm.data.remote.response.Post;
import com.chhaichivion.mvvm.data.repository.PostRepository;

import java.util.List;

/**
 * Copyright (c) PRASAC MFI, Ltd. All rights reserved. (https://www.prasac.com.kh/)
 * Author	: Chhai Chivon (chivon.chhai@prasac.com.kh) on 4/22/21.
 * Position : Senior Application Development Officer
 */
public class PostViewModel extends ViewModel {

    private Context context;
    private MutableLiveData<List<Post>> mPostMutableLiveData;
    private PostRepository mRepo;

    public void init(Context context){
        this.context = context;
        if(mPostMutableLiveData != null){
            return;
        }
        mRepo = PostRepository.getInstance();
        mPostMutableLiveData = mRepo.getPosts();
    }

    public LiveData<List<Post>> getPostsRepository(){
        return mPostMutableLiveData;
    }
}
