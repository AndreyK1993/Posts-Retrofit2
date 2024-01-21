package org.example.app.network;

import org.example.app.entity.PostResponse;
import org.example.app.entity.PostsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("posts")
    Call<PostsResponse> getPosts();

    @GET("posts/{id}")
    Call<PostResponse> getPostById(@Path("id") int id);
}
