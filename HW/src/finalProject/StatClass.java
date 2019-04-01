package finalProject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StatClass {
    private static StatClass instance;

    private StatClass() {
    }

    public static StatClass getInstance() {
        if (instance == null) {
            instance = new StatClass();
        }
        return instance;
    }

    public void showMiddlePriceOfAllCategory(Store store) {
        Map<String, Price> priceMap = new HashMap<>();
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
        if (!priceMap.isEmpty()) {
            System.out.println("\nСписок средней стоимости магазина " + store.name + " (id: " + store.id + "):");
            priceMap.forEach((key, value) -> {
                System.out.printf("%s - %.1f\n", key, value.price / value.number);
            });
        } else System.out.println("\nПродукция отсутсвует\n");
    }

    public void showMiddlePriceOfAllCategory(ArrayList<Store> arrayList) {
        Map<String, Price> priceMap = new HashMap<>();
        Price price;
        for (Store store : arrayList) {
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
        if (!priceMap.isEmpty()) {
            System.out.println("\nСписок средней стоимости во всех магазинах:");
            priceMap.forEach((key, value) -> {
                System.out.printf("%s - %.1f\n", key, value.price / value.number);
            });
        } else System.out.println("\nПродукция отсутсвует\n");
    }

    public void showQuantityInAllCategory(Store store) {
        Map<String, Price> priceMap = new HashMap<>();
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
        if (!priceMap.isEmpty()) {
            System.out.println("\nСписок суммарного количества товаров магазина " + store.name + " (id: " + store.id + "):");
            priceMap.forEach((key, value) -> {
                System.out.println(key + " - " + value.number);
            });
        } else System.out.println("\nПродукция отсутсвует\n");
    }

    public void showQuantityInAllCategory(ArrayList<Store> arrayList) {
        Map<String, Price> priceMap = new HashMap<>();
        Price price;
        for (Store store : arrayList) {
            if (!store.listOfProduct.isEmpty()) {
                for (Product product : store.listOfProduct) {
                    if (priceMap.isEmpty()) {
                        priceMap.put(product.category, new Price(product.quantityInStock));
                    } else {
                        if (priceMap.containsKey(product.category)) {
                            price = priceMap.get(product.category);
                            priceMap.put(product.category, new Price(product.quantityInStock + price.number));
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
                            priceMap.put(promotionalProduct.category, new Price(promotionalProduct.quantityInStock + price.number));
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
                            priceMap.put(freeProduct.category, new Price(freeProduct.quantityInStock + price.number));
                        } else {
                            priceMap.put(freeProduct.category, new Price(freeProduct.quantityInStock));
                        }
                    }
                }
            }
        }
        if (!priceMap.isEmpty()) {
            System.out.println("\nСписок суммарного количества товаров во всех магазинах:");
            priceMap.forEach((key, value) -> {
                System.out.println(key + " - " + value.number);
            });
        } else System.out.println("\nПродукция отсутсвует\n");
    }

    public void showMiddleQuantityproductFormCategory(Store store) {
        Map<String, Price> priceMap = new HashMap<>();
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
        if (!priceMap.isEmpty()) {
            System.out.println("\nСписок среднего количества товаров магазина " + store.name + " (id: " + store.id + "):");
            priceMap.forEach((key, value) -> {
                System.out.printf("%s - %.1f\n", key, value.price / value.number);
            });
        } else System.out.println("\nПродукция отсутсвует\n");
    }

    public void showMiddleQuantityproductFormCategory(ArrayList<Store> arrayList) {
        Map<String, Price> priceMap = new HashMap<>();
        Price price;
        for (Store store : arrayList) {
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
        if (!priceMap.isEmpty()) {
            System.out.println("\nСписок среднего количества товаров во всех магазинах:");
            priceMap.forEach((key, value) -> {
                System.out.printf("%s - %.1f\n", key, value.price / value.number);
            });
        } else System.out.println("\nПродукция отсутсвует\n");
    }

    public void printQuantityAllProduct(Store store) {
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
        System.out.println("Количество товаров в магазине " + store.name + " (id: " + store.id + ") - " + quantity);
    }

    public void printQuantityAllProduct(ArrayList<Store> arrayList) {
        int quantity = 0;
        for (Store store : arrayList) {
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
        }
        System.out.println("Количество товаров во всех магазинах - " + quantity);
    }

    public static class Price {
        double price;
        int number;

        public Price(double price, int number) {
            this.price = price;
            this.number = number;
        }

        public Price(int number) {
            this(.0D, number);
        }
    }
}
