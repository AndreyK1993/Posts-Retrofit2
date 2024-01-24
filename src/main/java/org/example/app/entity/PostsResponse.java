package org.example.app.entity;

import java.util.List;

public class PostsResponse {

    private final List<Post> data;

    public PostsResponse(List<Post> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "" + data;
    }

}
