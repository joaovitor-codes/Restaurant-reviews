package br.com.facol.model;

public class UserReview {
    private int Review_id;
    private User user;
    private Restaurant restaurant;
    private String Review_text;
    private double score;

    public UserReview() {
    }

    public UserReview(int review_id, String review_text, Restaurant restaurant, User user, double score) {
        Review_id = review_id;
        Review_text = review_text;
        this.restaurant = restaurant;
        this.score = score;
        this.user = user;
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
        return user.getUser_id();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getRestaurantId() {
        return restaurant.getId();
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void setRestaurant_id(int restaurant_id) {
        if (this.restaurant == null) {
            this.restaurant = new Restaurant();
        }
        this.restaurant.setId(restaurant_id);
    }

    public void setUser_id(int user_id) {
        if (this.user == null) {
            this.user = new User();
        }
        this.user.setId(user_id);
    }


    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
