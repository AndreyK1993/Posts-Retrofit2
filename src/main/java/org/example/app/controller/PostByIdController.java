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

    public void getUserById() {
        view.getOutput(readUserById(
                Integer.parseInt(view.getData())
        ));
        AppStarter.startApp();
    }

    private String readUserById(int id) {
        Optional<Response<PostResponse>> optional = model.fetchUserById(id);

        if (optional.isEmpty()) {
            return Constants.NO_DATA_MSG;
        } else {
            Gson gson = new Gson();
            Post user = gson.fromJson(String.valueOf(optional.get().body()),
                    new TypeToken<Post>() {}.getType());
            return "User: id " + user.getId() + ", " + user.getLastName() +
                    " " + user.getFirstName() + ", " + user.getEmail();
        }
    }
}
