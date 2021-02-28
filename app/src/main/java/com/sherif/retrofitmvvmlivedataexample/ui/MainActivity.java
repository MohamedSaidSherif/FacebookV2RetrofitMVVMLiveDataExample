package com.sherif.retrofitmvvmlivedataexample.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.sherif.retrofitmvvmlivedataexample.R;
import com.sherif.retrofitmvvmlivedataexample.models.PostModel;

import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private PostViewModel postViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView postsRecyclerView = findViewById(R.id.activity_main_posts_recycler_view);
        postsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        postsRecyclerView.setHasFixedSize(true);
        PostAdapter postAdapter = new PostAdapter();
        postsRecyclerView.setAdapter(postAdapter);

        postViewModel = new ViewModelProvider(this).get(PostViewModel.class);
        postViewModel.getAllPosts();

        postViewModel.allPostsListMutableLiveData.observe(this, new Observer<List<PostModel>>()
        {
            @Override
            public void onChanged(List<PostModel> postModels)
            {
                postAdapter.submitList(postModels);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.main_menu_search_for_post:
            {
                postViewModel.searchForPosts(3);
                return true;
            }

            case R.id.main_menu_get_specific_post:
            {
                postViewModel.getSpecificPost(1);
                return true;
            }

            default:
            {
                return super.onOptionsItemSelected(item);
            }
        }
    }
}