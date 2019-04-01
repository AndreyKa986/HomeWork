package finalProject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class PromotionalProductComparator  extends ProductComparator<PromotionalProduct> {

    public PromotionalProductComparator(int sortType) {
        super(sortType);
    }

    @Override
    public int compare(PromotionalProduct o1, PromotionalProduct o2) {
        if (sortType == 9) {
            SimpleDateFormat format2 = new SimpleDateFormat("MMM d, yyyy", Locale.US);
            try {
                return format2.parse(o1.validityPeriod).compareTo(format2.parse(o2.validityPeriod));
            } catch (ParseException e) {
                return 0;
            }
        } else {
            return super.compare(o1, o2);
        }
    }
}
