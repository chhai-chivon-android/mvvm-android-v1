package com.chhaichivion.mvvm.ui.user;

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
import com.chhaichivion.mvvm.ui.listener.InfiniteScrollListener;
import com.chhaichivion.mvvm.viewmodel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity implements InfiniteScrollListener.OnLoadMoreListener{

    // https://ayusch.com/android-paginated-recyclerview-with-progress-bar/

    private List<User> users = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private UserAdapter mAdapter;
    private ProgressBar mProgressBar;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

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
            mAdapter = new UserAdapter(UserActivity.this, users);
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

    @Override
    public void onLoadMore() {

    }
}