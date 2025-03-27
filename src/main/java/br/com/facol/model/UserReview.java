package br.com.facol.model;

import br.com.facol.model.ENUM.Review;

public class UserReview {
    private int Review_id;
    private User user;
    private Restaurant restaurant;
    private String Review_text;
    private Review tag;

    public UserReview() {
    }

    public UserReview(int review_id, Review tag, String review_text, Restaurant restaurant, User user) {
        Review_id = review_id;
        this.tag = tag;
        Review_text = review_text;
        this.restaurant = restaurant;
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

    public String getTag() {
        return tag.toString();
    }

    public void setTag(Review review) {
        tag = review;
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

    public void defReview(double rating, UserReview review) {
        try{
            if (rating < 5){
                review.tag = Review.RUIM;
            }else if (rating > 5 && rating < 8){
                review.tag = Review.BOM;
            }else if (rating >= 8){
                review.tag = Review.MUITO_BOM;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
