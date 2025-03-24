package br.com.facol.model;

public class UserReview {
    private int Review_id;
    private int User_id;
    private int Restaurant_id;
    private String Restaurant_name;
    private String Review_text;
    private int score;

    public UserReview() {
    }

    public UserReview(int review_id, int score, String restaurant_name, int restaurant_id, String review_text, int user_id) {
        Review_id = review_id;
        this.score = score;
        Restaurant_name = restaurant_name;
        Restaurant_id = restaurant_id;
        Review_text = review_text;
        User_id = user_id;
    }

    public int getRestaurant_id(Restaurant restaurant) {
        return restaurant.getId();
    }

    public String getRestaurant_name(Restaurant restaurant) {
        return restaurant.getNome();
    }

    public int getReview_id() {
        return Review_id;
    }

    public void setReview_id(int review_id) {
        Review_id = review_id;
    }

    public int getUser_id() {
        return User_id;
    }

    public void setUser_id(int user_id) {
        User_id = user_id;
    }

    public String getReview_text() {
        return Review_text;
    }

    public void setReview_text(String review_text) {
        Review_text = review_text;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
