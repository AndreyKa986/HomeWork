package finalProject.entities;

import java.util.Objects;

public class Product {
    public String id;
    public String name;
    public String barcode;
    public double price;
    public double rating;
    public String category;
    public int quantityInStock;
    public String expirationDate;

    public Product() {
    }

    public Product(String id, String name, String barcode, double price, double rating, String category,
                   int quantityInStock, String expirationDate) {
        this.id = id;
        this.name = name;
        this.barcode = barcode;
        this.price = price;
        this.rating = rating;
        this.category = category;
        this.quantityInStock = quantityInStock;
        this.expirationDate = expirationDate;
    }

    Product(String id, String name, String barcode, double rating, String category, int quantityInStock,
            String expirationDate) {
        this(id, name, barcode, .0D, rating, category, quantityInStock, expirationDate);
    }

    public void print() {
        System.out.println("Название тавара: " + name);
        System.out.println("\tid: " + id);
        System.out.println("\tШтрихкод: " + barcode);
        System.out.printf("\tЦена: %.2f руб.\n", price);
        System.out.printf("\tРейтинг: %.1f\n", rating);
        System.out.println("\tКатегория: " + category);
        System.out.println("\tКоличество на складе: " + quantityInStock);
        System.out.println("\tСрок службы: " + expirationDate);
        System.out.println();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product that = (Product) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(barcode, that.barcode) &&
                Objects.equals(price, that.price) &&
                Objects.equals(rating, that.rating) &&
                Objects.equals(category, that.category) &&
                Objects.equals(quantityInStock, that.quantityInStock) &&
                Objects.equals(expirationDate, that.expirationDate);
    }

    public void printInfoForProd() {
        System.out.println("\nВыберите поле товара:\n" +
                "1 - id\n" +
                "2 - наименование\n" +
                "3 - штрих-код\n" +
                "4 - цена (отсутствует у бесплатного товара)\n" +
                "5 - рейтинг\n" +
                "6 - категория\n" +
                "7 - количество товара\n" +
                "8 - срок службы\n" +
                "9 - дата окончания акции (только для акционного товара)\n" +
                "10 - количество товара в одни руки (только для бесплатного товара)\n");
    }
}
