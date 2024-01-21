package org.example.app.entity;

public class Post {

    private final int userId;
    private final int id;
    private final String title;
    private final String body;

    public Post(int userId, int id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public int getPostId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    //    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", email='" + email + '\'' +
//                '}';
//    }


    @Override
    public String toString() {
        return "{\"userId\":" + userId + "," +
                "\"id\":\"" + id + "\"," +
                "\"title\":\"" + title + "\"," +
                "\"body\":\"" + body + "\"}";
    }

}
