package com.chhaichivion.mvvm.ui.post;

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
import com.chhaichivion.mvvm.data.remote.response.Post;
import com.chhaichivion.mvvm.ui.adapter.PostAdapter;
import com.chhaichivion.mvvm.viewmodel.PostViewModel;

import java.util.ArrayList;
import java.util.List;

public class PostFragment extends Fragment {

    private List<Post> posts = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private PostAdapter mAdapter;
    private ProgressBar mProgressBar;
    private PostViewModel postViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = view.findViewById(R.id.rvPost);
        mProgressBar = view.findViewById(R.id.pbLoading);

        postViewModel = ViewModelProviders.of(this).get(PostViewModel.class);
        postViewModel.init(getContext());
        //showProgressBar();
        postViewModel.getPostsRepository().observe(this, usersResponse -> {
            posts.addAll(usersResponse);
            mAdapter.notifyDataSetChanged();
        });
        initRecyclerView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_post, container, false);
    }

    private void initRecyclerView() {
        if (mAdapter == null) {
            mAdapter = new PostAdapter(this.getContext(), posts);
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