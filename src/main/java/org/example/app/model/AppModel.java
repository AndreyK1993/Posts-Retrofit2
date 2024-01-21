package org.example.app.model;

import org.example.app.controller.PostByIdController;
import org.example.app.controller.PostsController;
import org.example.app.view.PostByIdView;
import org.example.app.view.PostsView;

public class AppModel {

    public void readUsers() {
        PostModel model = new PostModel();
        PostsView view = new PostsView();
        PostsController controller = new PostsController(model, view);
        controller.getUsers();
    }

    public void readUserById() {
        PostModel model = new PostModel();
        PostByIdView view = new PostByIdView();
        PostByIdController controller = new PostByIdController(model, view);
        controller.getUserById();
    }
}
