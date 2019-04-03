package finalProject;

import finalProject.entities.FreeProduct;
import finalProject.entities.Product;
import finalProject.entities.PromotionalProduct;
import finalProject.entities.Store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StatisticPrinter {
    private static StatisticPrinter instance;

    private StatisticPrinter() {
    }

    public static StatisticPrinter getInstance() {
        if (instance == null) {
            instance = new StatisticPrinter();
        }
        return instance;
    }

    void showMiddlePriceOfAllCategory(Store store) {
        Map<String, Price> priceMap = new HashMap<>();
        middlePriceOfAllCategory(priceMap, store);
        if (!priceMap.isEmpty()) {
            System.out.println("\nСписок средней стоимости магазина " + store.name + " (id: " + store.id + "):");
            priceMap.forEach((key, value) -> System.out.printf("%s - %.1f\n", key, value.price / value.number));
        } else System.out.println("\nПродукция отсутсвует\n");
    }

    void showMiddlePriceOfAllCategory(ArrayList<Store> arrayList) {
        Map<String, Price> priceMap = new HashMap<>();
        for (Store store : arrayList) {
            middlePriceOfAllCategory(priceMap, store);
        }
        if (!priceMap.isEmpty()) {
            System.out.println("\nСписок средней стоимости во всех магазинах:");
            priceMap.forEach((key, value) -> System.out.printf("%s - %.1f\n", key, value.price / value.number));
        } else System.out.println("\nПродукция отсутсвует\n");
    }

    private void middlePriceOfAllCategory(Map<String, Price> priceMap, Store store) {
        Price price;
        if (!store.listOfProduct.isEmpty()) {
            for (Product product : store.listOfProduct) {
                if (priceMap.isEmpty()) {
                    priceMap.put(product.category, new Price(product.price, 1));
                } else {
                    if (priceMap.containsKey(product.category)) {
                        price = priceMap.get(product.category);
                        priceMap.put(product.category, new Price(product.price + price.price, ++price.number));
                    } else {
                        priceMap.put(product.category, new Price(product.price, 1));
                    }
                }
            }
        }
        if (!store.listOfPromotionalProduct.isEmpty()) {
            for (PromotionalProduct promotionalProduct : store.listOfPromotionalProduct) {
                if (priceMap.isEmpty()) {
                    priceMap.put(promotionalProduct.category, new Price(promotionalProduct.price, 1));
                } else {
                    if (priceMap.containsKey(promotionalProduct.category)) {
                        price = priceMap.get(promotionalProduct.category);
                        priceMap.put(promotionalProduct.category, new Price(promotionalProduct.price + price.price, ++price.number));
                    } else {
                        priceMap.put(promotionalProduct.category, new Price(promotionalProduct.price, 1));
                    }
                }
            }
        }
    }

    void showQuantityInAllCategory(Store store) {
        Map<String, Price> priceMap = new HashMap<>();
        quantityInAllCategory(priceMap, store);
        if (!priceMap.isEmpty()) {
            System.out.println("\nСписок суммарного количества товаров магазина " + store.name + " (id: " + store.id + "):");
            priceMap.forEach((key, value) -> System.out.println(key + " - " + value.number));
        } else System.out.println("\nПродукция отсутсвует\n");
    }

    void showQuantityInAllCategory(ArrayList<Store> arrayList) {
        Map<String, Price> priceMap = new HashMap<>();
        for (Store store : arrayList) {
            quantityInAllCategory(priceMap, store);
        }
        if (!priceMap.isEmpty()) {
            System.out.println("\nСписок суммарного количества товаров во всех магазинах:");
            priceMap.forEach((key, value) -> System.out.println(key + " - " + value.number));
        } else System.out.println("\nПродукция отсутсвует\n");
    }

    private void quantityInAllCategory(Map<String, Price> priceMap, Store store) {
        Price price;
        if (!store.listOfProduct.isEmpty()) {
            for (Product product : store.listOfProduct) {
                if (priceMap.isEmpty()) {
                    priceMap.put(product.category, new Price(product.quantityInStock));
                } else {
                    if (priceMap.containsKey(product.category)) {
                        price = priceMap.get(product.category);
                        priceMap.put(product.category, new Price(price.number + product.quantityInStock));
                    } else {
                        priceMap.put(product.category, new Price(product.quantityInStock));
                    }
                }
            }
        }
        if (!store.listOfPromotionalProduct.isEmpty()) {
            for (PromotionalProduct promotionalProduct : store.listOfPromotionalProduct) {
                if (priceMap.isEmpty()) {
                    priceMap.put(promotionalProduct.category, new Price(promotionalProduct.quantityInStock));
                } else {
                    if (priceMap.containsKey(promotionalProduct.category)) {
                        price = priceMap.get(promotionalProduct.category);
                        priceMap.put(promotionalProduct.category, new Price(price.number + promotionalProduct.quantityInStock));
                    } else {
                        priceMap.put(promotionalProduct.category, new Price(promotionalProduct.quantityInStock));
                    }
                }
            }
        }
        if (!store.listOfFreeProduct.isEmpty()) {
            for (FreeProduct freeProduct : store.listOfFreeProduct) {
                if (priceMap.isEmpty()) {
                    priceMap.put(freeProduct.category, new Price(freeProduct.quantityInStock));
                } else {
                    if (priceMap.containsKey(freeProduct.category)) {
                        price = priceMap.get(freeProduct.category);
                        priceMap.put(freeProduct.category, new Price(price.number + freeProduct.quantityInStock));
                    } else {
                        priceMap.put(freeProduct.category, new Price(freeProduct.quantityInStock));
                    }
                }
            }
        }
    }

    void showMiddleQuantityproductFromCategory(Store store) {
        Map<String, Price> priceMap = new HashMap<>();
        middleQuantityproductFromCategory(priceMap, store);
        if (!priceMap.isEmpty()) {
            System.out.println("\nСписок среднего количества товаров магазина " + store.name + " (id: " + store.id + "):");
            priceMap.forEach((key, value) -> System.out.printf("%s - %.1f\n", key, value.price / value.number));
        } else System.out.println("\nПродукция отсутсвует\n");
    }

    void showMiddleQuantityproductFromCategory(ArrayList<Store> arrayList) {
        Map<String, Price> priceMap = new HashMap<>();
        for (Store store : arrayList) {
            middleQuantityproductFromCategory(priceMap, store);
        }
        if (!priceMap.isEmpty()) {
            System.out.println("\nСписок среднего количества товаров во всех магазинах:");
            priceMap.forEach((key, value) -> System.out.printf("%s - %.1f\n", key, value.price / value.number));
        } else System.out.println("\nПродукция отсутсвует\n");
    }

    private void middleQuantityproductFromCategory(Map<String, Price> priceMap, Store store) {
        Price price;
        if (!store.listOfProduct.isEmpty()) {
            for (Product product : store.listOfProduct) {
                if (priceMap.isEmpty()) {
                    priceMap.put(product.category, new Price(product.quantityInStock, 1));
                } else {
                    if (priceMap.containsKey(product.category)) {
                        price = priceMap.get(product.category);
                        priceMap.put(product.category, new Price(price.price + product.quantityInStock, ++price.number));
                    } else {
                        priceMap.put(product.category, new Price(product.quantityInStock, 1));
                    }
                }
            }
        }
        if (!store.listOfPromotionalProduct.isEmpty()) {
            for (PromotionalProduct promotionalProduct : store.listOfPromotionalProduct) {
                if (priceMap.isEmpty()) {
                    priceMap.put(promotionalProduct.category, new Price(promotionalProduct.quantityInStock, 1));
                } else {
                    if (priceMap.containsKey(promotionalProduct.category)) {
                        price = priceMap.get(promotionalProduct.category);
                        priceMap.put(promotionalProduct.category, new Price(price.price + promotionalProduct.quantityInStock, ++price.number));
                    } else {
                        priceMap.put(promotionalProduct.category, new Price(promotionalProduct.quantityInStock, 1));
                    }
                }
            }
        }
        if (!store.listOfFreeProduct.isEmpty()) {
            for (FreeProduct freeProduct : store.listOfFreeProduct) {
                if (priceMap.isEmpty()) {
                    priceMap.put(freeProduct.category, new Price(freeProduct.quantityInStock, 1));
                } else {
                    if (priceMap.containsKey(freeProduct.category)) {
                        price = priceMap.get(freeProduct.category);
                        priceMap.put(freeProduct.category, new Price(price.price + freeProduct.quantityInStock, ++price.number));
                    } else {
                        priceMap.put(freeProduct.category, new Price(freeProduct.quantityInStock, 1));
                    }
                }
            }
        }
    }

    void printQuantityAllProduct(Store store) {
        int quantity = quantityAllProduct(store);
        System.out.println("Количество товаров в магазине " + store.name + " (id: " + store.id + ") - " + quantity);
    }

    void printQuantityAllProduct(ArrayList<Store> arrayList) {
        int quantity = 0;
        for (Store store : arrayList) {
            quantity += quantityAllProduct(store);
        }
        System.out.println("Количество товаров во всех магазинах - " + quantity);
    }

    private int quantityAllProduct(Store store) {
        int quantity = 0;
        if (!store.listOfProduct.isEmpty()) {
            for (Product product : store.listOfProduct) {
                quantity += product.quantityInStock;
            }
        }
        if (!store.listOfPromotionalProduct.isEmpty()) {
            for (PromotionalProduct promotionalProduct : store.listOfPromotionalProduct) {
                quantity += promotionalProduct.quantityInStock;
            }
        }
        if (!store.listOfFreeProduct.isEmpty()) {
            for (FreeProduct freeProduct : store.listOfFreeProduct) {
                quantity += freeProduct.quantityInStock;
            }
        }
        return quantity;
    }

    public static class Price {
        double price;
        int number;

        Price(double price, int number) {
            this.price = price;
            this.number = number;
        }

        Price(int number) {
            this(.0D, number);
        }
    }
}
