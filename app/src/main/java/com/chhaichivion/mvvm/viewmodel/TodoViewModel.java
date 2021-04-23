package com.chhaichivion.mvvm.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.chhaichivion.mvvm.data.remote.response.Todo;
import com.chhaichivion.mvvm.data.repository.TodoRepository;

import java.util.List;

/**
 * Copyright (c) PRASAC MFI, Ltd. All rights reserved. (https://www.prasac.com.kh/)
 * Author	: Chhai Chivon (chivon.chhai@prasac.com.kh) on 4/22/21.
 * Position : Senior Application Development Officer
 */
public class TodoViewModel extends ViewModel {

    private MutableLiveData<List<Todo>> mTodoMutableLiveData;
    private TodoRepository mRepo;

    public void init(){
        if(mTodoMutableLiveData != null){
            return;
        }
        mRepo = TodoRepository.getInstance();
        mTodoMutableLiveData = mRepo.getTodos();
    }

    public LiveData<List<Todo>> getTodosRepository(){
        return mTodoMutableLiveData;
    }
}
