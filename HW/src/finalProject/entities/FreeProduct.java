package finalProject.entities;

import java.util.Objects;

public class FreeProduct extends Product {
    public int quantityInOneHand;

    public FreeProduct() {
    }

    public FreeProduct(String id, String name, String barcode, double rating, String category, int quantityInStock,
                       String expirationDate, int quantityInOneHand) {
        super(id, name, barcode, rating, category, quantityInStock, expirationDate);
        this.quantityInOneHand = quantityInOneHand;
}

    @Override
    void print() {
        System.out.println("Название тавара: "+name);
        System.out.println("\tid: "+id);
        System.out.println("\tШтрихкод: "+barcode);
        System.out.printf("\tРейтинг: %.1f\n",rating);
        System.out.println("\tКатегория: "+category);
        System.out.println("\tКоличество на складе: "+quantityInStock);
        System.out.println("\tСрок службы: "+expirationDate);
        System.out.println("\tКоличество товара в одни руки: "+quantityInOneHand);
        System.out.println();
    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FreeProduct that=(FreeProduct) o;
        return Objects.equals(id,that.id)&&
                Objects.equals(name,that.name)&&
                Objects.equals(barcode,that.barcode)&&
                Objects.equals(rating,that.rating)&&
                Objects.equals(category,that.category)&&
                Objects.equals(quantityInStock,that.quantityInStock)&&
                Objects.equals(expirationDate,that.expirationDate)&&
                Objects.equals(quantityInOneHand,that.quantityInOneHand);
    }
}
