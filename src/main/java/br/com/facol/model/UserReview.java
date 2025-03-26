package br.com.facol.model;

import br.com.facol.model.ENUM.Review;

public class UserReview {
    private int Review_id;
    private int User_id;
    private int Restaurant_id;
    private String Restaurant_name;
    private String Review_text;
    private Review tag;

    public UserReview() {
    }

    public UserReview(int review_id, String restaurant_name, int restaurant_id, String review_text, int user_id) {
        Review_id = review_id;
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

    public String getTag() {
        return tag.toString();
    }

    public void setTag(Review review) {
        tag = review;
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
