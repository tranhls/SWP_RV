/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author PC
 */
public class OrderDetails {

    private int orderDetail_ID;
    private int orderID;
    private int productID;
    private int quantity;
    private String image;

    public OrderDetails() {
    }

    public OrderDetails(int orderDetail_ID, int orderID, int productID, int quantity, String image) {
        this.orderDetail_ID = orderDetail_ID;
        this.orderID = orderID;
        this.productID = productID;
        this.quantity = quantity;
        this.image = image;
    }

    public int getOrderDetail_ID() {
        return orderDetail_ID;
    }

    public void setOrderDetail_ID(int orderDetail_ID) {
        this.orderDetail_ID = orderDetail_ID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "OrderDetails{" + "orderDetail_ID=" + orderDetail_ID + ", orderID=" + orderID + ", productID=" + productID + ", quantity=" + quantity + ", image=" + image + '}';
    }

}
