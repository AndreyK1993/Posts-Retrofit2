package org.example.app.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.app.entity.Post;
import org.example.app.entity.PostsResponse;
import org.example.app.model.PostModel;
import org.example.app.utils.AppStarter;
import org.example.app.utils.Constants;
import org.example.app.view.PostsView;
import retrofit2.Response;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class PostsController {

    PostModel model;
    PostsView view;

    public PostsController(PostModel model, PostsView view) {
        this.model = model;
        this.view = view;
    }

    public void getUsers() {
        view.getOutput(readUsers());
        AppStarter.startApp();
    }

    private String readUsers() {
        Optional<Response<PostsResponse>> optional = model.fetchUsers();
        if (optional.isEmpty()) {
            return Constants.NO_DATA_MSG;
        } else {

            Gson gson = new Gson();
            List<Post> users = gson.fromJson(String.valueOf(optional.get().body()),
                    new TypeToken<List<Post>>() {}.getType());

            StringBuilder stringBuilder = new StringBuilder();
            AtomicInteger cnt = new AtomicInteger(0);
            String str;

            for (Post user : users) {
                str = cnt.incrementAndGet() + ") User: id " + user.getId() + ", " +
                        user.getLastName() + " " + user.getFirstName() + ", " +
                        user.getEmail() + "\n";
                stringBuilder.append(str);
            }
            return stringBuilder.toString();
        }
    }
}
