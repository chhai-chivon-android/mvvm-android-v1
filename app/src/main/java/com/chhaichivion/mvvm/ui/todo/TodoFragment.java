package com.chhaichivion.mvvm.ui.todo;

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
import com.chhaichivion.mvvm.data.remote.response.Todo;
import com.chhaichivion.mvvm.ui.adapter.TodoAdapter;
import com.chhaichivion.mvvm.viewmodel.TodoViewModel;

import java.util.ArrayList;
import java.util.List;


public class TodoFragment extends Fragment {

    private List<Todo> todos = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private TodoAdapter mAdapter;
    private ProgressBar mProgressBar;
    private TodoViewModel todoViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = view.findViewById(R.id.rvTodo);
        mProgressBar = view.findViewById(R.id.pbLoading);

        todoViewModel = ViewModelProviders.of(this).get(TodoViewModel.class);
        todoViewModel.init();
        //showProgressBar();
        todoViewModel.getTodosRepository().observe(this, usersResponse -> {
            todos.addAll(usersResponse);
            mAdapter.notifyDataSetChanged();
        });
        initRecyclerView();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_todo, container, false);
    }

    private void initRecyclerView() {
        if (mAdapter == null) {
            mAdapter = new TodoAdapter(this.getContext(), todos);
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