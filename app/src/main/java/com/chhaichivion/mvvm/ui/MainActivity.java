package com.chhaichivion.mvvm.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;

import com.chhaichivion.mvvm.R;
import com.chhaichivion.mvvm.data.remote.response.Post;
import com.chhaichivion.mvvm.ui.home.HomeFragment;
import com.chhaichivion.mvvm.ui.photo.PhotoFragment;
import com.chhaichivion.mvvm.ui.post.PostFragment;
import com.chhaichivion.mvvm.ui.todo.TodoFragment;
import com.chhaichivion.mvvm.ui.user.UserFragment;
import com.chhaichivion.mvvm.utility.Utility;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    // https://ayusch.com/android-paginated-recyclerview-with-progress-bar/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        final DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        toolbar.setTitle("Home");
        HomeFragment homeFragment = new HomeFragment();
        Utility.displayFragment(homeFragment, getSupportFragmentManager(), R.id.lyt_fragment_container);

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                drawerLayout.closeDrawers();
                if (menuItem.getItemId() == R.id.mnu_home) {
                    toolbar.setTitle("Home");
                    HomeFragment homeFragment = new HomeFragment();
                    Utility.displayFragment(homeFragment, getSupportFragmentManager(), R.id.lyt_fragment_container);
                } else if (menuItem.getItemId() == R.id.mnu_user) {
                    toolbar.setTitle("User");
                    UserFragment userFragment = new UserFragment();
                    Utility.displayFragment(userFragment, getSupportFragmentManager(), R.id.lyt_fragment_container);
                } else if(menuItem.getItemId() == R.id.mnu_photo) {
                    toolbar.setTitle("Photo");
                    PhotoFragment photoFragment = new PhotoFragment();
                    Utility.displayFragment(photoFragment, getSupportFragmentManager(), R.id.lyt_fragment_container);
                }else if(menuItem.getItemId() == R.id.mnu_post) {
                    toolbar.setTitle("Post");
                    PostFragment postFragment = new PostFragment();
                    Utility.displayFragment(postFragment, getSupportFragmentManager(), R.id.lyt_fragment_container);
                }else if(menuItem.getItemId() == R.id.mnu_todo) {
                    toolbar.setTitle("Todo");
                    TodoFragment todoFragment = new TodoFragment();
                    Utility.displayFragment(todoFragment, getSupportFragmentManager(), R.id.lyt_fragment_container);
                }
                return true;
            }
        });
    }
}