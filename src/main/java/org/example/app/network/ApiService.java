package org.example.app.network;

import org.example.app.entity.PostResponse;
import org.example.app.entity.PostsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("api/users")
    Call<PostsResponse> getUsers();

    @GET("api/users/{id}")
    Call<PostResponse> getUserById(@Path("id") int id);
}
