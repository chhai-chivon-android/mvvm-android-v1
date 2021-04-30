package com.chhaichivion.mvvm.ui.photo;

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
import android.widget.ProgressBar;

import com.chhaichivion.mvvm.R;
import com.chhaichivion.mvvm.data.remote.response.Photo;
import com.chhaichivion.mvvm.ui.adapter.PhotoAdapter;
import com.chhaichivion.mvvm.ui.adapter.PostAdapter;
import com.chhaichivion.mvvm.viewmodel.PhotoViewModel;
import com.chhaichivion.mvvm.viewmodel.PostViewModel;

import java.util.ArrayList;
import java.util.List;

public class PhotoFragment extends Fragment {

    private List<Photo> photos = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private PhotoAdapter mAdapter;
    private ProgressBar mProgressBar;
    private PhotoViewModel photoViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.rvPhoto);
        mProgressBar = view.findViewById(R.id.pbLoading);

        photoViewModel = ViewModelProviders.of(this).get(PhotoViewModel.class);
        photoViewModel.init();
        //showProgressBar();
        photoViewModel.getPhotosRepository().observe(this, usersResponse -> {
            photos.addAll(usersResponse);
            mAdapter.notifyDataSetChanged();
        });
        initRecyclerView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_photo, container, false);
    }

    private void initRecyclerView() {
        if (mAdapter == null) {
            mAdapter = new PhotoAdapter(this.getContext(), photos);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setNestedScrollingEnabled(true);
        } else {
            mAdapter.notifyDataSetChanged();
        }
        //hideProgressBar();
    }
}