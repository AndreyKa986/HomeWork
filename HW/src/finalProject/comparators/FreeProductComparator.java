package finalProject.comparators;

import finalProject.entities.FreeProduct;

public class FreeProductComparator  extends ProductComparator<FreeProduct> {

    public FreeProductComparator(int sortType) {
        super(sortType);
    }

    @Override
    public int compare(FreeProduct o1, FreeProduct o2) {
        if (sortType == 10) {
            return Integer.compare(o1.quantityInOneHand, o2.quantityInOneHand);
        } else {
            return super.compare(o1, o2);
        }
    }
}
