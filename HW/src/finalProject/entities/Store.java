package finalProject.entities;

import java.util.ArrayList;
import java.util.Objects;

public class Store {
    public String id;
    public String name;
    public String address;
    public String typeOfStore;
    public ArrayList<Product> listOfProduct;
    public ArrayList<PromotionalProduct> listOfPromotionalProduct;
    public ArrayList<FreeProduct> listOfFreeProduct;

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

    public void print() {
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

    public void show() {
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

    public void showList(ArrayList<Store>listStores) {
        listStores.forEach(Store::print);
    }

    public void showStores(ArrayList<Store>listStores) {
        for (Store store : listStores) {
            System.out.println("Название магазина: " + store.name);
            System.out.println("\tid: " + store.id);
            System.out.println("\tАдресс: " + store.address);
            System.out.println("\tТип магазина: " + store.typeOfStore);
        }
    }

    public void sortStores(int i,ArrayList<Store>listStores) {
        listStores.sort((o1, o2) -> {
            switch (i) {
                case 1:
                    return o1.id.compareTo(o2.id);
                case 2:
                    return o1.name.compareTo(o2.name);
                case 3:
                    return o1.address.compareTo(o2.address);
                case 4:
                    return o1.typeOfStore.compareTo(o2.typeOfStore);
                default:
                    return 0;
            }
        });
    }

    public void printInfoForSortShop() {
        System.out.println("\nВыберите по какому полю сортировать магазины:\n" +
                "1 - id\n" +
                "2 - Название\n" +
                "3 - Адрес\n" +
                "4 - Тип магазина\n");
    }
}
