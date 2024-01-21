package org.example.app.model;

import org.example.app.entity.PostResponse;
import org.example.app.entity.PostsResponse;
import org.example.app.network.ApiClient;
import org.example.app.network.ApiService;
import retrofit2.Call;
import retrofit2.Response;

import java.util.Optional;

public class PostModel {

    // REST api/users
    public Optional<Response<PostsResponse>> fetchUsers() {

        ApiClient client = new ApiClient();
        ApiService service = client.getApiService();
        Call<PostsResponse> call = service.getUsers();
        Optional<Response<PostsResponse>> optional;

        try {
            optional = Optional.of(call.execute());
        } catch (Exception ex) {
            optional = Optional.empty();
        }

        return optional;
    }

    // REST api/users/{id}
    public Optional<Response<PostResponse>> fetchUserById(int id) {
        ApiClient client = new ApiClient();
        ApiService service = client.getApiService();
        Call<PostResponse> call = service.getUserById(id);
        Optional<Response<PostResponse>> optional;

        try {
            optional = Optional.of(call.execute());
        } catch (Exception ex) {
            optional = Optional.empty();
        }

        return optional;
    }
}