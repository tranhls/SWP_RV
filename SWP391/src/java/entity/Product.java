package entity;

public class Product {

    private int productID;
    private String name;
    private String description;
    private double price;
    private String category;
    private String image;
    private int catID;
    private int sellID;

    public Product() {
    }

    public Product(int productID, String name, double price, String image) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public Product(int productID, String name, String description, double price, String category, String image, int catID, int sellID) {
        this.productID = productID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.image = image;
        this.catID = catID;
        this.sellID = sellID;
    }

    public Product(String name, String description, double price, String category, String image, int catID, int sellID) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.image = image;
        this.catID = catID;
        this.sellID = sellID;
    }

    public Product(String name, String description, double price, String category, String image) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.image = image;
    }

    public int getSellID() {
        return sellID;
    }

    public void setSellID(int sellID) {
        this.sellID = sellID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCatID() {
        return catID;
    }

    public void setCatID(int catID) {
        this.catID = catID;
    }

    @Override
    public String toString() {
        return "Product{"
                + "productID=" + productID
                + ", name='" + name + '\''
                + ", description='" + description + '\''
                + ", price=" + price
                + ", category='" + category + '\''
                + ", image='" + image + '\''
                + ", catID=" + catID
                + '}';
    }
}
