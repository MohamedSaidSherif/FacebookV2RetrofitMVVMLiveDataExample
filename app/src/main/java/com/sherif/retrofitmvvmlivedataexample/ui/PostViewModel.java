package com.sherif.retrofitmvvmlivedataexample.ui;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sherif.retrofitmvvmlivedataexample.models.PostModel;
import com.sherif.retrofitmvvmlivedataexample.retrofit_clients.PostClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostViewModel extends ViewModel
{
    private static final String TAG = "PostViewModel";
    MutableLiveData<List<PostModel>> allPostsListMutableLiveData = new MutableLiveData<>();

    public void getAllPosts()
    {
        Call<List<PostModel>> call = PostClient.getInstance().getAllPosts();
        call.enqueue(new Callback<List<PostModel>>()
        {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response)
            {
                allPostsListMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t)
            {
                Log.e(TAG, "Cannot get all posts: " + t.getMessage());
            }
        });
    }

    public void searchForPosts(int userId)
    {
        Call<List<PostModel>> call = PostClient.getInstance().searchForPosts(userId);
        call.enqueue(new Callback<List<PostModel>>()
        {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response)
            {
                allPostsListMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t)
            {
                Log.e(TAG, "Cannot get posts: " + t.getMessage());
            }
        });
    }

    public void getSpecificPost(int id)
    {
        Call<PostModel> call = PostClient.getInstance().getSpecificPost(id);
        call.enqueue(new Callback<PostModel>()
        {
            @Override
            public void onResponse(Call<PostModel> call, Response<PostModel> response)
            {
                List<PostModel> list = new ArrayList<>();
                list.add(response.body());
                allPostsListMutableLiveData.setValue(list);
            }

            @Override
            public void onFailure(Call<PostModel> call, Throwable t)
            {
                Log.e(TAG, "Cannot get post (" + id + ") :" + t.getMessage());
            }
        });
    }
}
