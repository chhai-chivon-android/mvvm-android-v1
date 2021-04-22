package com.chhaichivion.mvvm.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.chhaichivion.mvvm.R;
import com.chhaichivion.mvvm.data.remote.response.User;
import com.chhaichivion.mvvm.ui.adapter.UserAdapter;
import com.chhaichivion.mvvm.viewmodel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private List<User> users = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private UserAdapter mAdapter;
    private ProgressBar mProgressBar;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.rvUser);
        mProgressBar = findViewById(R.id.pbLoading);

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.init();
        showProgressBar();
        userViewModel.getUsersRepository().observe(this, usersResponse -> {
            users.addAll(usersResponse);
            mAdapter.notifyDataSetChanged();
        });
        initRecyclerView();
    }

    private void initRecyclerView() {
        if (mAdapter == null) {
            mAdapter = new UserAdapter(MainActivity.this, users);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setNestedScrollingEnabled(true);
        } else {
            mAdapter.notifyDataSetChanged();
        }
        hideProgressBar();
    }

    private void showProgressBar(){
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar(){
        mProgressBar.setVisibility(View.GONE);
    }
}