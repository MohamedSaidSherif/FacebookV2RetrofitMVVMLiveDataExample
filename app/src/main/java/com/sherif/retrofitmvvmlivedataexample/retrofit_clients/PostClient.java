package com.sherif.retrofitmvvmlivedataexample.retrofit_clients;

import com.sherif.retrofitmvvmlivedataexample.models.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostClient
{
    private static final String BASE_URL = "http://jsonplaceholder.typicode.com/";

    private static PostClient instance;
    private PostInterface postInterface;

    private PostClient()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        postInterface = retrofit.create(PostInterface.class);
    }

    public static PostClient getInstance()
    {
        if (instance == null)
        {
            instance = new PostClient();
        }
        return instance;
    }

    public Call<List<PostModel>> getAllPosts()
    {
        return postInterface.getAllPosts();
    }

    public Call<List<PostModel>> searchForPosts(int userId)
    {
        return postInterface.searchForPosts(userId);
    }

    public Call<PostModel> getSpecificPost(int id)
    {
        return postInterface.getSpecificPost(id);
    }
}
