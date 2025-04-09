package br.com.facol.model;

public class UserReview {
    private int Review_id;
    private int user_id;
    private int restaurant_id;
    private String Review_text;
    private double score;

    public UserReview() {
    }

    public UserReview(String review_text, int restaurant_id, int user_id, double score) {
        Review_text = review_text;
        this.restaurant_id = restaurant_id;
        this.score = score;
        this.user_id = user_id;
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

    public String getReview_text() {
        return Review_text;
    }

    public void setReview_text(String review_text) {
        Review_text = review_text;
    }

    public int getUserId() {
        return user_id;
    }

    public int getRestaurantId() {
        return restaurant_id;
    }


    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }


    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
