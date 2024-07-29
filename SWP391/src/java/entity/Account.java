/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author buidu
 */
public class Account {

    private int accountID;
    private String user;
    private String pass;
    private int isAdmin;
    private int isSeller;

    public String checkRoll() {
        String roll;
        if (isAdmin == 1 && isSeller == 1) {
            roll = "Admin";
        } else if (isSeller == 1) {
            roll = "Seller";
        } else {
            roll = "User";
        }
        return roll;
    }

    public Account() {
    }

    public Account(int accountID, String user, String pass, int isAdmin, int isSeller) {
        this.accountID = accountID;
        this.user = user;
        this.pass = pass;
        this.isAdmin = isAdmin;
        this.isSeller = isSeller;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public int getIsSeller() {
        return isSeller;
    }

    public void setIsSeller(int isSeller) {
        this.isSeller = isSeller;
    }

    @Override
    public String toString() {
        return "Account{" + "accountID=" + accountID + ", user=" + user + ", pass=" + pass + ", isAdmin=" + isAdmin + ", isSeller=" + isSeller + '}';
    }

}
