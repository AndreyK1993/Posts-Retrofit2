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
        view.getOutput(readPostById(
                Integer.parseInt(view.getData())
        ));
        AppStarter.startApp();
    }

    private String readPostById(int id) {
        Optional<Response<PostResponse>> optional = model.fetchPostById(id);

        if (optional.isEmpty()) {
            return Constants.NO_DATA_MSG;
        } else {
            // Добавляем код для логирования JSON-ответа и объекта Post
            String jsonResponse = String.valueOf(optional.get().body());
            System.out.println("JSON Response: " + jsonResponse);

            Gson gson = new Gson();
            Post post = gson.fromJson(jsonResponse, new TypeToken<Post>() {}.getType());
            System.out.println("Converted Post: " + post);

            // Проверяем, не является ли post null перед его использованием
            if (post != null) {
                return "Post: id " + post.getPostId() + ", " + post.getTitle() +
                        " " + post.getId() + ", " + post.getBody();
            } else {
                return "Failed to convert JSON to Post object";
            }
        }
    }

}
