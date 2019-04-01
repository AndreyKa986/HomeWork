package finalProject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Locale;

public class ProductComparator <T extends Product> implements Comparator<T> {

    protected int sortType;

    public ProductComparator(int sortType) {
        this.sortType = sortType;
    }

    @Override
    public int compare(T o1, T o2) {
        switch (sortType) {
            case 1:
                return o1.id.compareTo(o2.id);
            case 2:
                return o1.name.compareTo(o2.name);
            case 3:
                return o1.barcode.compareTo(o2.barcode);
            case 4:
                return Double.compare(o1.price, o2.price);
            case 5:
                return Double.compare(o1.rating, o2.rating);
            case 6:
                return o1.category.compareTo(o2.category);
            case 7:
                return Integer.compare(o1.quantityInStock, o2.quantityInStock);
            case 8:
                SimpleDateFormat format = new SimpleDateFormat("MMM d, yyyy", Locale.US);
                try {
                    return format.parse(o1.expirationDate).compareTo(format.parse(o2.expirationDate));
                } catch (ParseException e) {
                    return 0;
                }
            default:
                return 0;
        }
    }
}
