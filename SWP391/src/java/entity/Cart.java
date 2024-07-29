/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author chi
 */
public class Cart {

    private int cartID;
    private int productID;
    private double totalAmount;
    private int totalQuantity;
    private Product product;
    private int accountId;

    public Cart() {
    }

    public Cart(int cartID, int productID, double totalAmount, int totalQuantity, Product product) {
        this.cartID = cartID;
        this.productID = productID;
        this.totalAmount = totalAmount;
        this.totalQuantity = totalQuantity;
        this.product = product;
    }

    public Cart(int cartID, int productID, double totalAmount, int totalQuantity, int accountId) {
        this.cartID = cartID;
        this.productID = productID;
        this.totalAmount = totalAmount;
        this.totalQuantity = totalQuantity;
        this.accountId = accountId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    @Override
    public String toString() {
        return "Cart{" + "cartID=" + cartID + ", productID=" + productID + ", totalAmount=" + totalAmount + ", totalQuantity=" + totalQuantity + ", product=" + product + ", accountId=" + accountId + '}';
    }

}
