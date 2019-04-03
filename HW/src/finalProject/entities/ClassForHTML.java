package finalProject.entities;

public class ClassForHTML implements Comparable {
    public String shopName;
    public String typeOfShop;
    public String categoryOfProduct;
    public String ratingOfProduct;
    public String productName;

    public ClassForHTML(String shopName, String typeOfShop, String categoryOfProduct, String ratingOfProduct, String productName) {
        this.shopName = shopName;
        this.typeOfShop = typeOfShop;
        this.categoryOfProduct = categoryOfProduct;
        this.ratingOfProduct = ratingOfProduct;
        this.productName = productName;
    }

    @Override
    public int compareTo(Object o) {
        ClassForHTML that = (ClassForHTML) o;
        if (!this.shopName.equals(that.shopName)) {
            return this.shopName.compareTo(that.shopName);
        } else if (!this.typeOfShop.equals(that.typeOfShop)) {
            return this.typeOfShop.compareTo(that.typeOfShop);
        } else if (!this.categoryOfProduct.equals(that.categoryOfProduct)) {
            return this.categoryOfProduct.compareTo(that.categoryOfProduct);
        } else if (!this.ratingOfProduct.equals(that.ratingOfProduct)) {
            return this.ratingOfProduct.compareTo(that.ratingOfProduct);
        } else return this.productName.compareTo(that.productName);
    }
}