package finalProject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import finalProject.comparators.FreeProductComparator;
import finalProject.comparators.ProductComparator;
import finalProject.comparators.PromotionalProductComparator;
import finalProject.entities.*;
import finalProject.threads.ThreadFromHDD;
import finalProject.threads.ThreadFromServer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    private static ArrayList<Store> listStores;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException, IOException {
        FileWriter writer = new FileWriter("text.txt");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Store str=new Store();
        Product prd=new Product();
        ArrayList<Store> listFromHDD = new ArrayList<>();
        ArrayList<Store> listFromServer = new ArrayList<>();
        ThreadFromHDD threadFromHDD = new ThreadFromHDD(listFromHDD::addAll);
        ThreadFromServer threadFromServer = new ThreadFromServer(listFromServer::addAll);
        threadFromHDD.start();
        threadFromServer.start();
        while (threadFromHDD.isAlive()) {
            Thread.sleep(100);
        }
        while (threadFromServer.isAlive()) {
            Thread.sleep(100);
        }
        createList(listFromHDD, listFromServer);
        int key = 1;
        boolean isChanged = false;
        while (key != 0) {
            System.out.println("Выберите действие:\n" +
                    "1 - просматреть все данные (отсортированные по id)\n" +
                    "2 - просматреть данные отсортированные по любому из полей\n" +
                    "3 - создавать запись\n" +
                    "4 - изменить запись\n" +
                    "5 - удалить запись\n" +
                    "6 - поиск записи по любому полю\n" +
                    "7 - сохранить результат своей работы в файл\n" +
                    "8 - сформировать html отчет\n" +
                    "9 - получить статистику\n" +
                    "0 - выйти из программы\n");
            key = scanner.nextInt();
            switch (key) {
                case 1:
                    str.sortStores(1,listStores);
                    for (Store store : listStores) {
                        store.listOfProduct.sort(new ProductComparator<>(1));
                        store.listOfPromotionalProduct.sort(new PromotionalProductComparator(1));
                        store.listOfFreeProduct.sort(new FreeProductComparator(1));
                    }
                    str.showList(listStores);
                    break;
                case 2:
                    System.out.println("\nВыберите данные для просмотра и сортировки:\n" +
                            "1 - Все магазины и товары\n" +
                            "2 - Все магазины без списков товаров\n" +
                            "3 - Список товаров одного магазина\n" +
                            "0 - Ввернуться в главное меню\n");
                    int type;
                    switch (scanner.nextInt()) {
                        case 1:
                            str.printInfoForSortShop();
                            str.sortStores(scanner.nextInt(),listStores);
                            prd.printInfoForProd();
                            type = scanner.nextInt();
                            for (Store store : listStores) {
                                store.listOfProduct.sort(new ProductComparator<>(type));
                                store.listOfPromotionalProduct.sort(new PromotionalProductComparator(type));
                                store.listOfFreeProduct.sort(new FreeProductComparator(type));
                            }
                            str.showList(listStores);
                            break;
                        case 2:
                            str.printInfoForSortShop();
                            str.sortStores(scanner.nextInt(),listStores);
                            str.showStores(listStores);
                            break;
                        case 3:
                            int shop = numberOfShop();
                            type = typeOfProduct();
                            prd.printInfoForProd();
                            try {
                                switch (type) {
                                    case 1:
                                        listStores.get(shop).listOfProduct.sort(new ProductComparator<>(scanner.nextInt()));
                                        listStores.get(shop).listOfProduct.forEach(Product::print);
                                        break;
                                    case 2:
                                        listStores.get(shop).listOfPromotionalProduct.sort(new PromotionalProductComparator(scanner.nextInt()));
                                        listStores.get(shop).listOfPromotionalProduct.forEach(PromotionalProduct::print);
                                        break;
                                    case 3:
                                        listStores.get(shop).listOfFreeProduct.sort(new FreeProductComparator(scanner.nextInt()));
                                        listStores.get(shop).listOfFreeProduct.forEach(FreeProduct::print);
                                        break;
                                    default:
                                        System.out.println("\nНеправильно введён номер типа товара!!!\n");
                                }
                            } catch (IndexOutOfBoundsException e) {
                                System.out.println("\nНеправильно введён номер магазина!!!\n");
                            }
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("\nНеправильно введён номер операции\n");
                    }
                    break;
                case 3:
                    System.out.println("\nВыберите что требуется создать:\n" +
                            "1 - новый магазин\n" +
                            "2 - новый товар\n");
                    switch (scanner.nextInt()) {
                        case 1:
                            Store store = new Store();
                            System.out.println("\nВведите id:\n");
                            scanner.nextLine();
                            store.id = scanner.nextLine();
                            System.out.println("\nВведите название:\n");
                            store.name = scanner.nextLine();
                            System.out.println("\nВведите адрес:\n");
                            store.address = scanner.nextLine();
                            System.out.println("\nВведите тип магазина:\n");
                            store.typeOfStore = scanner.nextLine();
                            listStores.add(store);
                            isChanged = true;
                            System.out.println("\nНовый магазин успешно создан\n");
                            break;
                        case 2:
                            int shop = numberOfShop();
                            if (shop < 0 || listStores.size() <= shop) {
                                System.out.println("\nНеправильно введён номер магазина!!!\n");
                                continue;
                            }
                            type = typeOfProduct();
                            if (type < 0 || 3 < type) {
                                System.out.println("\nНеправильно введён номер типа товара!!!\n");
                                continue;
                            }
                            System.out.println("\nВведите id нового товара:\n");
                            scanner.nextLine();
                            String id = scanner.nextLine();
                            System.out.println("\nВведите название:\n");
                            String name = scanner.nextLine();
                            System.out.println("\nВведите штрих-код:\n");
                            String barcode = scanner.nextLine();
                            System.out.println("\nВведите рейтинг:\n");
                            double rating = scanner.nextDouble();
                            System.out.println("\nВведите категорию:\n");
                            scanner.nextLine();
                            String category = scanner.nextLine();
                            System.out.println("\nВведите количество товара:\n");
                            int quantityInStock = scanner.nextInt();
                            System.out.println("\nВведите срок службы (например: Feb 7, 2019):\n");
                            scanner.nextLine();
                            String expirationDate = scanner.nextLine();
                            switch (type) {
                                case 1:
                                    System.out.println("\nВведите цену:\n");
                                    double price = scanner.nextDouble();
                                    listStores.get(shop).listOfProduct.add(new Product(id, name, barcode, price, rating, category, quantityInStock, expirationDate));
                                    break;
                                case 2:
                                    System.out.println("\nВведите цену:\n");
                                    double price2 = scanner.nextDouble();
                                    System.out.println("\nВведите срок действия акции (например: Feb 7, 2019):\n");
                                    scanner.nextLine();
                                    String validityPeriod = scanner.nextLine();
                                    listStores.get(shop).listOfPromotionalProduct.add(new PromotionalProduct(id, name, barcode, price2, rating, category, quantityInStock, expirationDate, validityPeriod));
                                    break;
                                case 3:
                                    System.out.println("\nВведите количество товара в одни руки:\n");
                                    int quantityInOneHand = scanner.nextInt();
                                    listStores.get(shop).listOfFreeProduct.add(new FreeProduct(id, name, barcode, rating, category, quantityInStock, expirationDate, quantityInOneHand));
                            }
                            isChanged = true;
                            System.out.println("\nНовый продукт успешно создан\n");
                            break;
                        default:
                            System.out.println("\nНеправильно выбран номер!!!\n");
                    }
                    break;
                case 4:
                    System.out.println("\nВыберите магазин в который желаете внести изменения\n");
                    int shop = numberOfShop();
                    if (shop < 0 || listStores.size() <= shop) {
                        System.out.println("\nНеправильно введён номер магазина!!!\n");
                        continue;
                    }
                    System.out.println("\nВыберите поле или спиок для редоктирования\n" +
                            "1 - id\n" +
                            "2 - название\n" +
                            "3 - адрес\n" +
                            "4 - тип магазина\n" +
                            "5 - список продуктов\n" +
                            "6 - список акционных товаров\n" +
                            "7 - список бесплатных товаров\n");
                    type = scanner.nextInt();
                    if (type < 0 || 7 < type) {
                        System.out.println("\nНеправильно выбран номер!!!\n");
                        continue;
                    }
                    boolean flag = true;
                    switch (type) {
                        case 1:
                            System.out.println("\nВведите новый id\n");
                            scanner.nextLine();
                            listStores.get(shop).id = scanner.nextLine();
                            System.out.println("\nЗапись успешно обновлена\n");
                            break;
                        case 2:
                            System.out.println("\nВведите новое название\n");
                            scanner.nextLine();
                            listStores.get(shop).name = scanner.nextLine();
                            System.out.println("\nЗапись успешно обновлена\n");
                            break;
                        case 3:
                            System.out.println("\nВведите новый адрес\n");
                            scanner.nextLine();
                            listStores.get(shop).address = scanner.nextLine();
                            System.out.println("\nЗапись успешно обновлена\n");
                            break;
                        case 4:
                            System.out.println("\nВведите новый тип магазина\n");
                            scanner.nextLine();
                            listStores.get(shop).typeOfStore = scanner.nextLine();
                            System.out.println("\nЗапись успешно обновлена\n");
                            break;
                        case 5:
                            type = numberOfProduct(listStores.get(shop).listOfProduct);
                            if (type < 0 || listStores.get(shop).listOfProduct.size() <= type) {
                                System.out.println("\nНеправильно выбран номер!!!\n");
                                continue;
                            }
                            Product product = listStores.get(shop).listOfProduct.get(type);
                            prd.printInfoForProd();
                            flag = changeObjectField(product, scanner.nextInt());
                            break;
                        case 6:
                            type = numberOfProduct(listStores.get(shop).listOfPromotionalProduct);
                            if (type < 0 || listStores.get(shop).listOfPromotionalProduct.size() <= type) {
                                System.out.println("\nНеправильно выбран номер!!!\n");
                                continue;
                            }
                            PromotionalProduct promotionalProduct = listStores.get(shop).listOfPromotionalProduct.get(type);
                            prd.printInfoForProd();
                            flag = changeObjectField(promotionalProduct, scanner.nextInt());
                            break;
                        case 7:
                            type = numberOfProduct(listStores.get(shop).listOfFreeProduct);
                            if (type < 0 || listStores.get(shop).listOfFreeProduct.size() <= type) {
                                System.out.println("\nНеправильно выбран номер!!!\n");
                                continue;
                            }
                            FreeProduct freeProduct = listStores.get(shop).listOfFreeProduct.get(type);
                            prd.printInfoForProd();
                            flag = changeObjectField(freeProduct, scanner.nextInt());
                    }
                    if (flag) {
                        isChanged = true;
                    }
                    break;
                case 5:
                    System.out.println("\nВыберите что требуется удалить:\n" +
                            "1 - магазин\n" +
                            "2 - товар\n");
                    switch (scanner.nextInt()) {
                        case 1:
                            int shop2 = numberOfShop();
                            if (shop2 < 0 || listStores.size() <= shop2) {
                                System.out.println("\nНеправильно введён номер магазина!!!\n");
                                continue;
                            }
                            listStores.remove(shop2);
                            System.out.println("\nМагазин успешно удалён\n");
                            isChanged = true;
                            break;
                        case 2:
                            System.out.println("\nДля удаления товара");
                            int shop3 = numberOfShop();
                            if (shop3 < 0 || listStores.size() <= shop3) {
                                System.out.println("\nНеправильно введён номер магазина!!!\n");
                                continue;
                            }
                            int number;
                            type = typeOfProduct();
                            switch (type) {
                                case 1:
                                    number = numberOfProduct(listStores.get(shop3).listOfProduct);
                                    if (number < 0 || listStores.get(shop3).listOfProduct.size() <= number) {
                                        System.out.println("\nНеправильно выбран номер!!!\n");
                                        continue;
                                    }
                                    listStores.get(shop3).listOfProduct.remove(number);
                                    System.out.println("\nТовар успешно удалён\n");
                                    isChanged = true;
                                    break;
                                case 2:
                                    number = numberOfProduct(listStores.get(shop3).listOfPromotionalProduct);
                                    if (number < 0 || listStores.get(shop3).listOfPromotionalProduct.size() <= number) {
                                        System.out.println("\nНеправильно выбран номер!!!\n");
                                        continue;
                                    }
                                    listStores.get(shop3).listOfPromotionalProduct.remove(number);
                                    System.out.println("\nТовар успешно удалён\n");
                                    isChanged = true;
                                    break;
                                case 3:
                                    number = numberOfProduct(listStores.get(shop3).listOfFreeProduct);
                                    if (number < 0 || listStores.get(shop3).listOfFreeProduct.size() <= number) {
                                        System.out.println("\nНеправильно выбран номер!!!\n");
                                        continue;
                                    }
                                    listStores.get(shop3).listOfFreeProduct.remove(number);
                                    System.out.println("\nТовар успешно удалён\n");
                                    isChanged = true;
                                    break;
                                default:
                                    System.out.println("\nНеправильно выбран номер!!!\n");
                            }
                            break;
                        default:
                            System.out.println("\nНеправильно выбран номер!!!\n");
                    }
                    break;
                case 6:
                    System.out.println("\nВведите строку для поиска:\n");
                    scanner.nextLine();
                    String stringForSearching = scanner.nextLine();
                    boolean search=false;
                    for (Store store : listStores) {
                        if (store.id.contains(stringForSearching) || store.name.contains(stringForSearching) ||
                                store.address.contains(stringForSearching) || store.typeOfStore.contains(stringForSearching)) {
                            System.out.println("\nНайденно совподение в магазине:\n");
                            search=true;
                            store.show();
                        }
                        for (Product product : store.listOfProduct) {
                            if (product.id.contains(stringForSearching) || product.name.contains(stringForSearching) ||
                                    product.barcode.contains(stringForSearching) || product.category.contains(stringForSearching) ||
                                    Double.toString(product.price).contains(stringForSearching) ||
                                    Double.toString(product.rating).contains(stringForSearching) ||
                                    product.expirationDate.contains(stringForSearching) ||
                                    Integer.toString(product.quantityInStock).contains(stringForSearching)) {
                                System.out.println("\nНайденно совподение в товаре:\n");
                                search=true;
                                product.print();
                            }
                        }
                        for (PromotionalProduct promotionalProduct : store.listOfPromotionalProduct) {
                            if (promotionalProduct.id.contains(stringForSearching) || promotionalProduct.name.contains(stringForSearching) ||
                                    promotionalProduct.barcode.contains(stringForSearching) || promotionalProduct.category.contains(stringForSearching) ||
                                    Double.toString(promotionalProduct.price).contains(stringForSearching) ||
                                    Double.toString(promotionalProduct.rating).contains(stringForSearching) ||
                                    promotionalProduct.expirationDate.contains(stringForSearching) ||
                                    Integer.toString(promotionalProduct.quantityInStock).contains(stringForSearching) ||
                                    promotionalProduct.validityPeriod.contains(stringForSearching)) {
                                System.out.println("\nНайденно совподение в товаре:\n");
                                search=true;
                                promotionalProduct.print();
                            }
                        }
                        for (FreeProduct freeProduct : store.listOfFreeProduct) {
                            if (freeProduct.id.contains(stringForSearching) || freeProduct.name.contains(stringForSearching) ||
                                    freeProduct.barcode.contains(stringForSearching) || freeProduct.category.contains(stringForSearching) ||
                                    Double.toString(freeProduct.rating).contains(stringForSearching) ||
                                    freeProduct.expirationDate.contains(stringForSearching) ||
                                    Integer.toString(freeProduct.quantityInStock).contains(stringForSearching) ||
                                    Integer.toString(freeProduct.quantityInOneHand).contains(stringForSearching)) {
                                System.out.println("\nНайденно совподение в товаре:\n");
                                search=true;
                                freeProduct.print();
                            }
                        }
                    }
                    if(!search){
                        System.out.println("\nСовпадение не найденно\n");
                    }
                    break;
                case 7:
                    writer.write(gson.toJson(listStores));
                    writer.flush();
                    System.out.println("\nФайл успешно сохранён\n");
                    isChanged = false;
                    break;
                case 8:
                    ArrayList<SimpleClass>list=new ArrayList<>();
                    for(Store store:listStores){
                        for(Product product:store.listOfProduct){
                            list.add(new SimpleClass(store.name,store.typeOfStore,product.category,Double.toString(product.rating),product.name));
                        }
                        for(PromotionalProduct product:store.listOfPromotionalProduct){
                            list.add(new SimpleClass(store.name,store.typeOfStore,product.category,Double.toString(product.rating),product.name));
                        }
                        for(FreeProduct product:store.listOfFreeProduct){
                            list.add(new SimpleClass(store.name,store.typeOfStore,product.category,Double.toString(product.rating),product.name));
                        }
                    }
                    Collections.sort(list);
                    FileWriter writerHTML=new FileWriter("table.html");
                    for(SimpleClass sim:list){
                        writerHTML.write(String.format("%-20s%-20s%-20s%-20s%-20s\n",sim.shopName,sim.typeOfShop,sim.categoryOfProduct,sim.ratingOfProduct,sim.productName));
                    }
                    writerHTML.flush();
                    writerHTML.close();
                    System.out.println("\nHTML отчёт успешно создан\n");
                    break;
                case 9:
                    System.out.println("\nВывести статистику для:\n" +
                            "1 - всех магазинов\n" +
                            "2 - одного магазина\n");
                    scanner.nextLine();
                    switch (scanner.nextInt()) {
                        case 1:
                            showStatistic(listStores);
                            break;
                        case 2:
                            shop = numberOfShop();
                            if (shop >= 0 && shop < listStores.size()) {
                                showStatistic(listStores.get(shop));
                            } else {
                                System.out.println("\nНеправильно введён номер магазина.\n");
                            }
                            break;
                        default:
                            System.out.println("\nНеправильно введён номер операции.\n");
                    }
                    break;
                case 0:
                    if (isChanged) {
                        System.out.println("\nЕсть не сохраненные данныею\n" +
                                "Желаете сохранить?\n1 - да\n2 - нет\n");
                        int choice = scanner.nextInt();
                        if (choice == 1) {
                            writer.write(gson.toJson(listStores));
                            writer.flush();
                        }
                        if (choice != 1 && choice != 2) {
                            System.out.println("\nНеправильный ввод!!!\n");
                            key = 1;
                            continue;
                        }
                    }
                    scanner.close();
                    writer.close();
                    break;
                default:
                    System.out.println("\nНеправильно введён номер операции.\n");
            }
        }
    }

    private static void createList(ArrayList<Store> listFromHDD, ArrayList<Store> listFromServer) {
        listStores = new ArrayList<>(listFromHDD);
        int number;
        boolean isThere;
        for (Store storeFromServer : listFromServer) {
            isThere = false;
            for (Store store : listStores) {
                if (storeFromServer.id.equals(store.id)) {
                    isThere = true;
                    if (!storeFromServer.equals(store)) {
                        System.out.println("\nОбнаружины различия в магазинах.\n" +
                                "Введите номер магазин который следует сохранить:\n");
                        System.out.printf("%20s   %-20s\n", "1 - данные с диска", "2 - данные с сервера");
                        System.out.printf("%20s - %-20s\n", store.id, storeFromServer.id);
                        System.out.printf("%20s - %-20s\n", store.name, storeFromServer.name);
                        System.out.printf("%20s - %-20s\n", store.address, storeFromServer.address);
                        System.out.printf("%20s - %-20s\n", store.typeOfStore, storeFromServer.typeOfStore);
                        number = scanner.nextInt();
                        if (number == 2) {
                            store.name = storeFromServer.name;
                            store.address = storeFromServer.address;
                            store.typeOfStore = storeFromServer.typeOfStore;
                            System.out.println("Установленны данные с сервера.");
                        }
                        if (number == 1) {
                            System.out.println("Установлены данные с диска.");
                        }
                    }
                    if (!Objects.deepEquals(storeFromServer.listOfProduct, store.listOfProduct)) {
                        compareAndChoiceOfListProducts(store.name, store.listOfProduct, storeFromServer.listOfProduct);
                    }
                    if (!Objects.deepEquals(storeFromServer.listOfPromotionalProduct, store.listOfPromotionalProduct)) {
                        compareAndChoiceOfListProducts(store.name, store.listOfPromotionalProduct, storeFromServer.listOfPromotionalProduct);
                    }
                    if (!Objects.deepEquals(storeFromServer.listOfFreeProduct, store.listOfFreeProduct)) {
                        compareAndChoiceOfListProducts(store.name, store.listOfFreeProduct, storeFromServer.listOfFreeProduct);
                    }
                }
            }
            if (!isThere) {
                listStores.add(storeFromServer);
            }
        }
        System.out.println("\nСоздание списка магазинов завершенно.\n");
    }

    private static <T extends Product> void compareAndChoiceOfListProducts(String nameOfStore, ArrayList<T> thisList, ArrayList<T> thatList) {
        int number;
        System.out.println("В магазине " + nameOfStore + " в данных с сервера и диска есть различия.");
        System.out.println("\nСписок с диска:\n");
        thisList.forEach(Product::print);
        System.out.println("\nСписок с сервера\n");
        thatList.forEach(Product::print);
        System.out.println("Выберите какой список следует сохранить:\n" +
                "1 - данные с диска, 2 - данные с сервера");
        number = scanner.nextInt();
        if (number == 2) {
            thisList.clear();
            thisList.addAll(thatList);
            System.out.println("Установленны данные с сервера.");
        }
        if (number == 1) {
            System.out.println("Установлены данные с диска.");
        }
    }

    private static int numberOfShop() {
        System.out.println("\nВведите номер магазина:\n");
        for (int i = 0; i < listStores.size(); i++) {
            System.out.println(++i + " - " + listStores.get(--i).name);
        }
        System.out.println();
        return scanner.nextInt() - 1;
    }

    private static int typeOfProduct() {
        System.out.println("\nВыберите тип товаров:\n" +
                "1 - Простой товар\n" +
                "2 - Товар на скидке\n" +
                "3 - Бесмлатный товар\n");
        return scanner.nextInt();
    }

    private static <T extends Product> int numberOfProduct(ArrayList<T> arrayList) {
        System.out.println("\nВедите номер товара:\n");
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(++i + " - " + arrayList.get(--i).name);
        }
        System.out.println();
        return scanner.nextInt() - 1;
    }

    private static <T extends Product> boolean changeObjectField(T product, int choice) {
        if (0 < choice && choice < 11) {
            switch (choice) {
                case 1:
                    System.out.println("\nВведите новый id:\n");
                    scanner.nextLine();
                    product.id = scanner.nextLine();
                    break;
                case 2:
                    System.out.println("\nВведите новое имя:\n");
                    scanner.nextLine();
                    product.name = scanner.nextLine();
                    break;
                case 3:
                    System.out.println("\nВведите новый штрих-код:\n");
                    scanner.nextLine();
                    product.barcode = scanner.nextLine();
                    break;
                case 4:
                    System.out.println("\nВведите новую цену:\n");
                    product.price = scanner.nextDouble();
                    break;
                case 5:
                    System.out.println("\nВведите новый рейтинг:\n");
                    product.rating = scanner.nextDouble();
                    break;
                case 6:
                    System.out.println("\nВведите новое название котегории:\n");
                    scanner.nextLine();
                    product.category = scanner.nextLine();
                    break;
                case 7:
                    System.out.println("\nВведите новое количество товара:\n");
                    product.quantityInStock = scanner.nextInt();
                    break;
                case 8:
                    System.out.println("\nВведите новую дату окончания срока службы (например: Feb 7, 2019):\n");
                    scanner.nextLine();
                    product.expirationDate = scanner.nextLine();
                    break;
                case 9:
                    System.out.println("\nВведите новую дату окончания акции (например: Feb 7, 2019):\n");
                    scanner.nextLine();
                    ((PromotionalProduct)product).validityPeriod = scanner.nextLine();
                    break;
                case 10:
                    System.out.println("\nВведите новое количество товара отпускаемого в одни руки:\n");
                    ((FreeProduct)product).quantityInOneHand = scanner.nextInt();
            }
            System.out.println("\nИзменения успешно внесены\n");
            return true;
        } else {
            System.out.println("\nНеправильно введён номер поля.\n");
            return false;}
    }

    private static void showStatistic(Store store) {
        System.out.println("\nВыберите тип статистики:\n" +
                "1 - среднюю цену каждой категории\n" +
                "2 - суммарное количество товаров по категориям\n" +
                "3 - среднее количество товаров по категориям\n" +
                "4 - суммарное количество всех товаров\n");
        GetStatistic stat = GetStatistic.getInstance();
        switch (scanner.nextInt()) {
            case 1:
                stat.showMiddlePriceOfAllCategory(store);
                break;
            case 2:
                stat.showQuantityInAllCategory(store);
                break;
            case 3:
                stat.showMiddleQuantityproductFormCategory(store);
                break;
            case 4:
                stat.printQuantityAllProduct(store);
                break;
            default:
                System.out.println("\nНеправильно введён номер операции.\n");
        }
        System.out.println();
    }

    private static void showStatistic(ArrayList<Store> arrayList) {
        System.out.println("\nВыберите тип статистики:\n" +
                "1 - среднюю цену каждой категории\n" +
                "2 - суммарное количество товаров по категориям\n" +
                "3 - среднее количество товаров по категориям\n" +
                "4 - суммарное количество всех товаров\n");
        GetStatistic stat = GetStatistic.getInstance();
        switch (scanner.nextInt()) {
            case 1:
                stat.showMiddlePriceOfAllCategory(arrayList);
                break;
            case 2:
                stat.showQuantityInAllCategory(arrayList);
                break;
            case 3:
                stat.showMiddleQuantityproductFormCategory(arrayList);
                break;
            case 4:
                stat.printQuantityAllProduct(arrayList);
                break;
            default:
                System.out.println("\nНеправильно введён номер операции.\n");
        }
        System.out.println();
    }
}
