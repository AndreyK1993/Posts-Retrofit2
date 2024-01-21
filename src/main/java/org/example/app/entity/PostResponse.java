package org.example.app.entity;

public class PostResponse {

    private final Post data;

    public PostResponse(Post data) {
        this.data = data;
    }

//    @Override
//    public String toString() {
//        return "UserResponse{" +
//                "data=" + data +
//                '}';
//    }

    @Override
    public String toString() {
        return "" + data;
    }
}
