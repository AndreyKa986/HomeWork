package finalProject.entities;

public class SimpleClass implements Comparable {
    public String shopName;
    public String typeOfShop;
    public String categoryOfProduct;
    public String ratingOfProduct;
    public String productName;

    public SimpleClass(String shopName, String typeOfShop, String categoryOfProduct, String ratingOfProduct, String productName) {
        this.shopName = shopName;
        this.typeOfShop = typeOfShop;
        this.categoryOfProduct = categoryOfProduct;
        this.ratingOfProduct = ratingOfProduct;
        this.productName = productName;
    }

    @Override
    public int compareTo(Object o) {
        SimpleClass that = (SimpleClass) o;
        if (this.shopName.compareTo(that.shopName) > 0) {
            return 1;
        } else if (this.shopName.compareTo(that.shopName) < 0) {
            return -1;
        } else {
            if (this.typeOfShop.compareTo(that.typeOfShop) > 0) {
                return 1;
            } else if (this.typeOfShop.compareTo(that.typeOfShop) < 0) {
                return -1;
            } else {
                if (this.categoryOfProduct.compareTo(that.categoryOfProduct) > 0) {
                    return 1;
                } else if (this.categoryOfProduct.compareTo(that.categoryOfProduct) < 0) {
                    return -1;
                } else {
                    if (this.ratingOfProduct.compareTo(that.ratingOfProduct) > 0) {
                        return 1;
                    } else if (this.ratingOfProduct.compareTo(that.ratingOfProduct) < 0) {
                        return -1;
                    } else return this.productName.compareTo(that.productName);
                }
            }
        }
    }
}