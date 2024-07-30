/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entity.Account;
import entity.Cart;
import entity.Category;
import entity.Customer;
import entity.Feedback;
import entity.Order;
import entity.OrderDetails;
import entity.Product;
import entity.voucher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author buidu
 * @author LamHP
 */
public class DAO {

    Connection conn = null; //ket noi sql
    PreparedStatement ps = null; //nem cau lenh query qua sql
    ResultSet rs = null; //nhan ket qua tra ve

    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String query = "select * from product";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getInt(8)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
        String query = "select * from category";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getInt(1),
                        rs.getString(2)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Cart> listCart(int accountId) {
        List<Cart> list = new ArrayList<>();
        String query = "SELECT [cartID]\n"
                + "      ,c.productID as productID\n"
                + "      ,[totalAmount]\n"
                + "      ,accountID\n"
                + "      ,[totalQuantity],\n"
                + "	  p.name as pname,\n"
                + "	  p.price as pprice,\n"
                + "	  p.image as pimage\n"
                + "  FROM cart c JOIN product p ON c.productID = p.productID WHERE accountID = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, accountId);

            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Cart(rs.getInt("cartID"), rs.getInt("productID"), rs.getDouble("totalAmount"),
                        rs.getInt("totalQuantity"), new Product(rs.getInt("productID"), rs.getString("pname"), rs.getDouble("pprice"), rs.getString("pimage"))));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<BillResponse> bill(int orderID) {
        List<BillResponse> list = new ArrayList<>();
        String query = "SELECT o.orderID as orderId\n"
                + "	  , p.name as pname,\n"
                + "	  o.quantiy as quantity\n"
                + "	  , p.price as price\n"
                + "	  , od.orderDate as orderDate\n"
                + "  FROM orderDetail o JOIN product p ON o.productID = p.productID \n"
                + "  JOIN [order] od ON od.orderID = o.orderID WHERE o.orderID = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, orderID);

            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new BillResponse(
                        rs.getInt("orderId"),
                        rs.getInt("quantity"),
                        rs.getString("pname"),
                        rs.getDouble("price"),
                        rs.getString("orderDate")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public class BillResponse {

        private int orderId;
        private int quantity;
        private String pname;
        private double price;
        private String orderDate;

        public BillResponse(int orderId, int quantity, String pname, double price, String orderDate) {
            this.orderId = orderId;
            this.quantity = quantity;
            this.pname = pname;
            this.price = price;
            this.orderDate = orderDate;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public String getPname() {
            return pname;
        }

        public void setPname(String pname) {
            this.pname = pname;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getOrderDate() {
            return orderDate;
        }

        public void setOrderDate(String orderDate) {
            this.orderDate = orderDate;
        }

        @Override
        public String toString() {
            return "BillResponse{" + "orderId=" + orderId + ", quantity=" + quantity + ", pname=" + pname + ", price=" + price + ", orderDate=" + orderDate + '}';
        }

    }

    public void addToCart(int accountId, int quantity, int productId, double amount) {
        String query = "INSERT INTO [dbo].[cart]\n"
                + "           ([productID]\n"
                + "           ,[totalAmount]\n"
                + "           ,[accountID]\n"
                + "           ,[totalQuantity])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "		   ,?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, productId);
            ps.setDouble(2, amount);
            ps.setInt(3, accountId);
            ps.setInt(4, quantity);
            ps.executeUpdate();
        } catch (Exception e) {
            // Xử lý exception, có thể log hoặc throw ra ngoài để báo lỗi
            return;
        }
    }

    public void deleteCart(int cartId) {
        String query = "DELETE FROM [dbo].[cart]\n"
                + "      WHERE cartID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, cartId);
            ps.executeUpdate();
        } catch (Exception e) {
            // Xử lý exception, có thể log hoặc throw ra ngoài để báo lỗi
            return;
        }
    }

    public void updateToCart(int cartId, int quantity, double amount) {
        String query = "UPDATE [dbo].[cart]\n"
                + "   SET [totalAmount] = ?\n"
                + "      ,[totalQuantity] = ?\n"
                + " WHERE cartID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setDouble(1, amount);
            ps.setInt(2, quantity);
            ps.setInt(3, cartId);
            ps.executeUpdate();
        } catch (Exception e) {
            // Xử lý exception, có thể log hoặc throw ra ngoài để báo lỗi
            return;
        }
    }

    public void order(int accountId, double amount) {
        String query = "INSERT INTO [dbo].[order]\n"
                + "           ([customerID]\n"
                + "           ,[orderDate]\n"
                + "           ,[totalAmount]\n"
                + "           ,[status])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,GETDATE()\n"
                + "           ,?\n"
                + "           ,'Completed')";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, accountId);
            ps.setDouble(2, amount);
            ps.executeUpdate();
        } catch (Exception e) {
            // Xử lý exception, có thể log hoặc throw ra ngoài để báo lỗi
            return;
        }
    }

    public void orderDetail(int orderId, int productId, int quantity) {
        String query = "INSERT INTO [dbo].[orderDetail]\n"
                + "           ([orderID]\n"
                + "           ,[productID]\n"
                + "           ,[quantiy]\n"
                + "           ,[image])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,null)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, orderId);
            ps.setInt(2, productId);
            ps.setInt(3, quantity);
            ps.executeUpdate();
        } catch (Exception e) {
            // Xử lý exception, có thể log hoặc throw ra ngoài để báo lỗi
            return;
        }
    }

    public int getLastOrderId() {
        String query = "select top 1 orderID from [order] ORDER BY orderID DESC";
        int lastOrderId = -1; // Giá trị mặc định khi không tìm thấy hoặc có lỗi
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                lastOrderId = rs.getInt("orderID");
            }
        } catch (Exception e) {
            // Xử lý exception, có thể log hoặc throw ra ngoài để báo lỗi
        }
        return lastOrderId;
    }

    public Cart checkCartExist(int accountId, int productId) {
        String query = "SELECT  [cartID]\n"
                + "      ,[productID]\n"
                + "      ,[totalAmount]\n"
                + "      ,[accountID]\n"
                + "      ,[totalQuantity]\n"
                + "  FROM [cart] WHERE accountID = ? AND productID = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, accountId);
            ps.setInt(2, productId);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Cart(rs.getInt(1),
                        rs.getInt(2),
                        rs.getDouble(3),
                        rs.getInt(5),
                        rs.getInt(4));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public Customer getCustomerByAccountId(int accountId) {
        String query = "SELECT [customerID]\n"
                + "      ,[name]\n"
                + "      ,[email]\n"
                + "      ,[phone]\n"
                + "      ,[address]\n"
                + "      ,[accountID]\n"
                + "  FROM [customer] where accountID = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, accountId);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Customer(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<Product> getTopProduct() {
        List<Product> list = new ArrayList<>();
        String query = "select top 3 p.productID, p.name, p.description, p.price,\n"
                + "p.category, p.image, p.catID, p.SellID,\n"
                + "Sum(od.quantiy) as total_quantity from product p \n"
                + "join orderDetail od on p.productID = od.productID\n"
                + "group by p.productID, p.name, p.description, p.price,\n"
                + "p.category, p.image, p.catID, p.SellID\n"
                + "order by total_quantity desc";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getInt(8)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> getTop1Product() {
        List<Product> list = new ArrayList<>();
        String query = "select top 1 p.productID, p.name, p.description, p.price,\n"
                + "p.category, p.image, p.catID, p.SellID,\n"
                + "Sum(od.quantiy) as total_quantity from product p \n"
                + "join orderDetail od on p.productID = od.productID\n"
                + "group by p.productID, p.name, p.description, p.price,\n"
                + "p.category, p.image, p.catID, p.SellID\n"
                + "order by total_quantity desc";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getInt(8)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> getProductByCatID(int catID) {
        List<Product> list = new ArrayList<>();
        String query = "select * from product\n"
                + "where catID = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, catID);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getInt(8)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> getProductBySellID(int sellID) {
        List<Product> list = new ArrayList<>();
        String query = "select * from product\n"
                + "where SellID= ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, sellID);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getInt(8)
                ));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Product getProductByID(int id) {
        String query = "select * from product\n"
                + "where productID = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getInt(8));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<Product> getProductByName(String name) {
        List<Product> list = new ArrayList<>();
        String query = "select * from product\n"
                + "where [name] like ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + name + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getInt(8)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Account getAccount(String user, String pass) {
        String query = "select * from account\n"
                + "where [user] = ? and pass = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public Account checkAccExist(String user) {
        String query = "select * from account\n"
                + "where [user] = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public Customer checkEmailExist(String email) {
        String query = "select * from customer\n"
                + "where email = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Customer(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void signUp(String user, String pass) throws Exception {
        String query = "INSERT INTO account([user], pass, isAdmin, isSeller) VALUES (?, ?, 0, 0);";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            ps.executeUpdate();
        } catch (Exception e) {
            // Xử lý exception, có thể log hoặc throw ra ngoài để báo lỗi
            e.printStackTrace();
        }
    }

    public void updateAcc(int id, String user, String pass) {
        String query = "UPDATE account\n"
                + "SET [user] = ?, pass = ?\n"
                + "WHERE accountID = ?;";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (Exception e) {
            // Xử lý exception, có thể log hoặc throw ra ngoài để báo lỗi
            return;
        }
    }

    public void updateCus(int id, String name, String email, String phone, String address) {
        String query = "UPDATE customer\n"
                + "SET [name] = ?, email = ?, phone = ?, [address] = ?\n"
                + "WHERE accountID = ?;";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, address);
            ps.setInt(5, id);
            ps.executeUpdate();
        } catch (Exception e) {
            // Xử lý exception, có thể log hoặc throw ra ngoài để báo lỗi
            return;
        }
    }

    public void signUpUser(String name, String email, String phone, String address) throws Exception {
        String customerQuery = "INSERT INTO customer(name, email, phone, address) VALUES (?, ?, ?, ?);";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(customerQuery);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, address); // Sửa "position" thành "address"
            ps.executeUpdate();
        } catch (Exception e) {
            // Xử lý exception
            e.printStackTrace();
        }
    }

    public Customer getCustomerByID(int id) {
        String query = "select * from customer\n"
                + "where accountID = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Customer(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public Account getAccountByID(int id) {
        String query = "select * from account\n"
                + "where accountID = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void addProduct(Product product) {
        String query = "INSERT INTO product(name, description, price, image, catID) VALUES (?, ?, ?, ?, ?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            ps.setString(4, product.getImage());
            ps.setInt(5, product.getCatID());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteProductId(String pid) {
        String query = "delete from product \n"
                + "where productID =?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, pid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void insertProduct(String name, String description,
            String price, String category, String image, String catID, int sellID) {
        String query = "INSERT [dbo].[product] \n"
                + "([name], [description], [price], [category], [image], [catID],[SellID])\n"
                + "VALUES(?,?,?,?,?,?,?)";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);

            ps.setString(1, name);
            ps.setString(2, description);
            ps.setString(3, price);
            ps.setString(4, category);
            ps.setString(5, image);
            ps.setString(6, catID);
            ps.setInt(7, sellID);
            ps.executeUpdate();
        } catch (Exception e) {
        }

    }

    public static void main(String[] args) {
        //Product p = new Product( "Cứt", "haha", 1000, "cafe", "https://media-cdn-v2.laodong.vn/storage/newsportal/2023/8/26/1233821/Giai-Nhi-1--Nang-Tre.jpg", 3, 1);
        DAO d = new DAO();
        //d.insertProduct("Cứt", "haha", "1000", "cafe", "https://media-cdn-v2.laodong.vn/storage/newsportal/2023/8/26/1233821/Giai-Nhi-1--Nang-Tre.jpg", "3", 1);
        List<BillResponse> c = d.bill(17);
        for (BillResponse cart : c) {
            System.out.println(cart.toString());
        }
    }

    public void editProduct(String name, String description, String price, String image, String catID, String category, String productID) {
        String query = "update product\n"
                + "set [name] =?,\n"
                + "[description]=?,\n"
                + "price=?,\n"
                + "[image]=?,\n"
                + "catID =?,\n"
                + "category =?\n"
                + "where productID = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setString(3, price);
            ps.setString(4, image);
            ps.setString(5, catID);
            ps.setString(6, category);
            ps.setString(7, productID);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public boolean updatePassword(String email, String pass) {
        String query = "UPDATE account SET pass = ? FROM account JOIN customer ON account.accountID = customer.accountID WHERE email = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, pass);
            ps.setString(2, email);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            // Xử lý exception, có thể log hoặc throw ra ngoài để báo lỗi
            return false;
        }
    }

    public List<Account> getAllAccount() {
        List<Account> list = new ArrayList<>();
        String query = "select * from account";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Customer> getAllCustomer() {
        List<Customer> list = new ArrayList<>();
        String query = "select * from customer";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Customer(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void deleteAccount(String aid) {
        String query = "-- Bắt đầu giao dịch\n"
                + "BEGIN TRANSACTION;\n"
                + "\n"
                + "-- Xóa các dòng trong bảng 'orderDetail'\n"
                + "DELETE FROM orderDetail\n"
                + "WHERE orderID IN (SELECT orderID FROM [order] WHERE customerID IN (SELECT customerID FROM customer WHERE accountID = ?));\n"
                + "\n"
                + "-- Xóa các dòng trong bảng 'order'\n"
                + "DELETE FROM [order]\n"
                + "WHERE customerID IN (SELECT customerID FROM customer WHERE accountID = ?);\n"
                + "\n"
                + "-- Xóa các dòng trong bảng 'customer'\n"
                + "DELETE FROM customer\n"
                + "WHERE accountID = ?;\n"
                + "\n"
                + "-- Xóa các dòng trong bảng 'account'\n"
                + "DELETE FROM account\n"
                + "WHERE accountID = ?;\n"
                + "\n"
                + "-- Hoàn thành giao dịch\n"
                + "COMMIT;";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, aid);
            ps.setString(2, aid);
            ps.setString(3, aid);
            ps.setString(4, aid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public List<Order> getAllOrders() {
        List<Order> list = new ArrayList<>();
        String query = "select * from [order]";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Order(rs.getInt(1),
                        rs.getInt(2),
                        rs.getDate(3),
                        rs.getDouble(4),
                        rs.getString(5)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<OrderDetails> getAllOrderDetails() {
        List<OrderDetails> list = new ArrayList<>();
        String query = "select * from orderDetail";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new OrderDetails(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getString(5)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    //dem so luong product trong db
    public int getTotalProduct() {
        String query = "select count(*) from product";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);//boi vi count chi ra 1 ket qua
            }
        } catch (Exception e) {
        }

        return 0;
    }

    public List<Product> pagingProduct(int index) {//index khi click se truyen so 1 2 3 vao db
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM product\n"
                + "order by productID\n"
                + "offset ? rows fetch next 9 rows only;";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, (index - 1) * 9);//index = 1 ==> 1-1*3 thi offset se bat dau tu 0 ==> 9 product dau
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getInt(8)));
            }
        } catch (Exception e) {
        }

        return list;
    }

    public void AddAcc(String user, String pass, int role) throws Exception {
        String query = "INSERT INTO account([user], pass, isAdmin, isSeller) VALUES (?, ?, 0, ?);";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            ps.setInt(3, role);
            ps.executeUpdate();
        } catch (Exception e) {
            // Xử lý exception, có thể log hoặc throw ra ngoài để báo lỗi
            e.printStackTrace();
        }
    }

    public boolean addCategory(String categoryName) {
        String queryCheckExistence = "SELECT COUNT(*) FROM category WHERE category = ?";
        String queryInsert = "INSERT INTO category (catID, category) VALUES (?, ?)";

        try (Connection conn = new DBContext().getConnection(); PreparedStatement psCheck = conn.prepareStatement(queryCheckExistence); PreparedStatement psInsert = conn.prepareStatement(queryInsert)) {

            // Kiểm tra xem category đã tồn tại chưa
            psCheck.setString(1, categoryName);
            ResultSet rs = psCheck.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                // Category đã tồn tại, xử lý thông báo lỗi hoặc cập nhật lại thông tin (tùy theo yêu cầu)
                System.out.println("Category '" + categoryName + "' already exists.");
                return false;
            }

            // Thêm category vào bảng
            String getMaxCatIDQuery = "SELECT MAX(catID) FROM category";
            PreparedStatement psMax = conn.prepareStatement(getMaxCatIDQuery);
            ResultSet rsMax = psMax.executeQuery();

            int nextCatID = 1; // Giá trị mặc định nếu bảng trống
            if (rsMax.next()) {
                nextCatID = rsMax.getInt(1) + 1;
            }

            psInsert.setInt(1, nextCatID); // Đặt giá trị cho catID
            psInsert.setString(2, categoryName); // Đặt giá trị cho category

            int rowsAffected = psInsert.executeUpdate();

            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public voucher getPercent(String code) {
        String query = "select * from voucher where code = ?";;
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, code);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new voucher(rs.getString(1),
                        rs.getInt(2));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<voucher> getAllVoucher() {
        List<voucher> list = new ArrayList<>();
        String query = "select * from voucher";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new voucher(rs.getString(1),
                        rs.getInt(2)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void addNewVoucher(String code, int persent) {
        String query = "INSERT INTO voucher(code, [percent]) VALUES (?, ?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, code);
            ps.setInt(2, persent);
            ps.executeUpdate();
        } catch (Exception e) {
            // Xử lý exception, có thể log hoặc throw ra ngoài để báo lỗi
            return;
        }
    }

    public void deleteVoucher(String code) {
        String query = "delete from voucher where code = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, code);
            ps.executeUpdate();
        } catch (Exception e) {
            // Xử lý exception, có thể log hoặc throw ra ngoài để báo lỗi
            return;
        }
    }

    public Category getCategoyByName(String name) {
        String query = "select * from category where category = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Category(rs.getInt(1),
                        rs.getString(2));
            }
        } catch (Exception e) {
        }
        return null;
    }

    // get order by id
    public List<Order> getOrderByID(int id) {
        List<Order> list = new ArrayList<>();
        String query = "SELECT * FROM [dbo].[order] WHERE customerID = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Order(rs.getInt(1),
                        rs.getInt(2),
                        rs.getDate(3),
                        rs.getDouble(4),
                        rs.getString(5)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public boolean addFeedback(Feedback feedback) throws Exception {
        String sql = "INSERT INTO feedback (customerID, orderID, rating, comments) VALUES (?, ?, ?, ?)";
        boolean success = false;

        try (Connection conn = new DBContext().getConnection(); // Khởi tạo kết nối
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set parameters
            if (feedback.getCustomerID() > 0) {
                stmt.setInt(1, feedback.getCustomerID());
            } else {
                stmt.setNull(1, java.sql.Types.INTEGER); // Set customerID to NULL in database
            }

            if (feedback.getOrderID() > 0) {
                stmt.setInt(2, feedback.getOrderID());
            } else {
                stmt.setNull(2, java.sql.Types.INTEGER); // Set orderID to NULL in database
            }

            stmt.setInt(3, feedback.getRating());
            stmt.setString(4, feedback.getComments());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
            }

        } catch (SQLException e) {
            // Xử lý và log lỗi một cách cụ thể
            System.err.println("Error adding feedback: " + e.getMessage());
        }

        return success;
    }

    public List<Feedback> getAllFeedbacks() throws SQLException, Exception {
        List<Feedback> feedbacks = new ArrayList<>();
        String sql = "SELECT * FROM feedback";

        try (Connection conn = new DBContext().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int feedBackID = rs.getInt("feedBackID");
                int customerID = rs.getInt("customerID");
                int orderID = rs.getInt("orderID");
                int rating = rs.getInt("rating");
                String comments = rs.getString("comments");

                Feedback feedback = new Feedback(customerID, orderID, rating, comments);
                feedback.setFeedbackID(feedBackID);

                feedbacks.add(feedback);
            }
        }

        return feedbacks;
    }
}
