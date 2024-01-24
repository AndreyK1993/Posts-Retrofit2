package org.example.app.controller;

import com.google.gson.Gson;
import org.example.app.entity.Post;
import org.example.app.entity.PostResponse;
import org.example.app.model.PostModel;
import org.example.app.utils.AppStarter;
import org.example.app.utils.Constants;
import org.example.app.view.PostByIdView;
import retrofit2.Response;

import java.util.Optional;

public class PostByIdController {

    private final PostModel model;
    private final PostByIdView view;

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
                PostResponse post = postResponse;
                return "Post: id " + post.getUserId() + ", " + post.getTitle() +
                        " " + post.getId() + ", " + post.getBody();
            } else {
                return "Failed to convert JSON to Post object";
            }
        }
    }

}
