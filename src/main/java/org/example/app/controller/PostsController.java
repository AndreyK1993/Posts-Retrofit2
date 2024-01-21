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

    public void getPosts() {
        view.getOutput(readPosts());
        AppStarter.startApp();
    }

    private String readPosts() {
        Optional<Response<PostsResponse>> optional = model.fetchPosts();
        if (optional.isEmpty()) {
            return Constants.NO_DATA_MSG;
        } else {

            Gson gson = new Gson();
            List<Post> posts = gson.fromJson(String.valueOf(optional.get().body()),
                    new TypeToken<List<Post>>() {}.getType());

            StringBuilder stringBuilder = new StringBuilder();
            AtomicInteger cnt = new AtomicInteger(0);
            String str;

            for (Post post : posts) {
                str = cnt.incrementAndGet() + ") Post: id " + post.getUserId() + ", " +
                        post.getTitle() + " " + post.getId() + ", " +
                        post.getBody() + "\n";
                stringBuilder.append(str);
            }
            return stringBuilder.toString();
        }
    }
}
