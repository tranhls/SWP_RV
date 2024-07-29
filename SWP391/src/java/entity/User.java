package entity;

public class User {

    private int employeeID;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String accountID;
    private boolean isAdmin; // Thêm trường isAdmin
    private boolean isSeller; // Thêm trường isSeller

    public User() {
    }

    public User(int employeeID, String name, String email, String phone, String address, String accountID, boolean isAdmin, boolean isSeller) {
        this.employeeID = employeeID;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.accountID = accountID;
        this.isAdmin = isAdmin;
        this.isSeller = isSeller;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean isSeller() {
        return isSeller;
    }

    public void setSeller(boolean isSeller) {
        this.isSeller = isSeller;
    }

}
