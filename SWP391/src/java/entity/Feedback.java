/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author DO NHAT TRUNG ANH
 */
public class Feedback {
    private int feedBackID;
    private int customerID;
    private int orderID;
    private int rating;
    private String comments;

    // Constructor
    public Feedback(int customerID, int orderID, int rating, String comments) {
        this.customerID = customerID;
        this.orderID = orderID;
        this.rating = rating;
        this.comments = comments;
    }

    // Getters and setters
    public int getFeedBackID() {
        return feedBackID;
    }

    public void setFeedbackID(int feedbackID) {
        this.feedBackID = feedbackID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}

