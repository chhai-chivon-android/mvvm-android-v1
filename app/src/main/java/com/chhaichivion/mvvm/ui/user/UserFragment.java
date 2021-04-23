package com.chhaichivion.mvvm.ui.user;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.chhaichivion.mvvm.R;
import com.chhaichivion.mvvm.data.remote.response.User;
import com.chhaichivion.mvvm.ui.adapter.UserAdapter;
import com.chhaichivion.mvvm.viewmodel.UserViewModel;

import java.util.ArrayList;
import java.util.List;


public class UserFragment extends Fragment {

    private List<User> users = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private UserAdapter mAdapter;
    private ProgressBar mProgressBar;
    private UserViewModel userViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = view.findViewById(R.id.rvUser);
        mProgressBar = view.findViewById(R.id.pbLoading);

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.init();
        showProgressBar();
        userViewModel.getUsersRepository().observe(this, usersResponse -> {
            users.addAll(usersResponse);
            mAdapter.notifyDataSetChanged();
        });
        initRecyclerView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    private void initRecyclerView() {
        if (mAdapter == null) {
            mAdapter = new UserAdapter(this.getContext(), users);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
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