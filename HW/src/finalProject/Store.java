package finalProject;

import java.util.ArrayList;
import java.util.Objects;

public class Store {
    String id;
    String name;
    String address;
    String typeOfStore;
    ArrayList<Product> listOfProduct;
    ArrayList<PromotionalProduct> listOfPromotionalProduct;
    ArrayList<FreeProduct> listOfFreeProduct;

    public Store() {
        listOfProduct = new ArrayList<>();
        listOfPromotionalProduct = new ArrayList<>();
        listOfFreeProduct = new ArrayList<>();
    }

    public Store(String id, String name, String address, String typeOfStore) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.typeOfStore = typeOfStore;
        listOfProduct = new ArrayList<>();
        listOfPromotionalProduct = new ArrayList<>();
        listOfFreeProduct = new ArrayList<>();
    }

    void print() {
        System.out.println("Название магазина: " + name);
        System.out.println("\tid: " + id);
        System.out.println("\tАдресс: " + address);
        System.out.println("\tТип магазина: " + typeOfStore);
        System.out.println("\nСписок товаров:\n");
        if (listOfProduct != null) {
            listOfProduct.forEach(Product::print);
        }
        System.out.println("\nСписок акционных товаров:\n");
        if (listOfPromotionalProduct != null) {
            listOfPromotionalProduct.forEach(PromotionalProduct::print);
        }
        System.out.println("\nСписок подарков:\n");
        if (listOfFreeProduct != null) {
            listOfFreeProduct.forEach(FreeProduct::print);
        }
        System.out.println("\n\t\t\t******\n");
    }

    void show() {
        System.out.println("Название магазина: " + name);
        System.out.println("\tid: " + id);
        System.out.println("\tАдресс: " + address);
        System.out.println("\tТип магазина: " + typeOfStore);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store that = (Store) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(address, that.address) &&
                Objects.equals(typeOfStore, that.typeOfStore);
    }
}
