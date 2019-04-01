package finalProject;

import java.util.Objects;

public class PromotionalProduct extends Product {
    String validityPeriod;

    public PromotionalProduct() {
    }

    public PromotionalProduct(String id, String name, String barcode, double price, double rating, String category,
                              int quantityInStock, String expirationDate, String validityPeriod) {
        super(id, name, barcode, price, rating, category, quantityInStock, expirationDate);
        this.validityPeriod = validityPeriod;
    }

    @Override
    void print() {
        System.out.println("Название тавара: " + name);
        System.out.println("\tid: " + id);
        System.out.println("\tШтрихкод: " + barcode);
        System.out.printf("\tЦена: %.2f руб.\n", price);
        System.out.printf("\tРейтинг: %.1f\n", rating);
        System.out.println("\tКатегория: " + category);
        System.out.println("\tКоличество на складе: " + quantityInStock);
        System.out.println("\tСрок службы: " + expirationDate);
        System.out.println("\tДата окончания акции: " + validityPeriod);
        System.out.println();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PromotionalProduct that=(PromotionalProduct) o;
        return Objects.equals(id,that.id)&&
                Objects.equals(name,that.name)&&
                Objects.equals(barcode,that.barcode)&&
                Objects.equals(price,that.price)&&
                Objects.equals(rating,that.rating)&&
                Objects.equals(category,that.category)&&
                Objects.equals(quantityInStock,that.quantityInStock)&&
                Objects.equals(expirationDate,that.expirationDate)&&
                Objects.equals(validityPeriod,that.validityPeriod);
    }
}
