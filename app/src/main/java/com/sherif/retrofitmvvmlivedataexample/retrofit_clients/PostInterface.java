package com.sherif.retrofitmvvmlivedataexample.retrofit_clients;

import com.sherif.retrofitmvvmlivedataexample.models.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PostInterface
{
    @GET("posts")
    Call<List<PostModel>> getAllPosts();

    @GET("posts")
    Call<List<PostModel>> searchForPosts(@Query("userId")   int userId);

    @GET("posts/{id}")
    Call<PostModel> getSpecificPost(@Path("id") int id);
}
