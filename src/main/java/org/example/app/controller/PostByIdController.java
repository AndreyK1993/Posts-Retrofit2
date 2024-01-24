package org.example.app.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.app.entity.Post;
import org.example.app.entity.PostResponse;
import org.example.app.model.PostModel;
import org.example.app.utils.AppStarter;
import org.example.app.utils.Constants;
import org.example.app.view.PostByIdView;
import retrofit2.Response;

import java.util.Optional;

public class PostByIdController {

    PostModel model;
    PostByIdView view;

    public PostByIdController(PostModel model, PostByIdView view) {
        this.model = model;
        this.view = view;
    }

    public void getPostById() {
        view.getOutput(readPostById(Integer.parseInt(view.getData())));
        AppStarter.startApp();
    }

    private String readPostById(int id) {
        Optional<Response<PostResponse>> optional = model.fetchPostById(id);

        if (optional.isEmpty()) {
            return Constants.NO_DATA_MSG;
        } else {
            Response<PostResponse> response = optional.get();

            if (!response.isSuccessful()) {
                System.out.println("Error: " + response.code() + " - " + response.message());
                return Constants.NO_DATA_MSG;
            }

            PostResponse postResponse = response.body();

            if (postResponse != null) {
                Post post = convertPostResponseToPost(postResponse);
                if (post != null) {
                    return "Post: id " + post.getUserId() + ", " + post.getTitle() +
                            " " + post.getId() + ", " + post.getBody();
                } else {
                    return "Failed to convert PostResponse to Post object";
                }
            } else {
                return "Failed to convert JSON to PostResponse object";
            }
        }
    }

    private Post convertPostResponseToPost(PostResponse postResponse) {
        Gson gson = new Gson();
        String postJson = gson.toJson(postResponse);
        return gson.fromJson(postJson, Post.class);
    }
}
