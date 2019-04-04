package finalProject;

import finalProject.entities.Product;
import finalProject.entities.Store;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Testing {
    private Store store;
    private Store store2;
    private StatisticPrinter sp;
    private Product product;
    private Product product2;

    @Before
    public void init() {
        store = new Store("shop", "hp", "somewhere", " ");
        store2 = new Store();
        sp = new StatisticPrinter();
        product = new Product();
        product2 = new Product();
    }

    @After
    public void tearDown() {
        store = null;
        store2 = null;
        sp = null;
        product = null;
        product2 = null;
    }

    @Test
    public void enterCorrectDate() {
        assertTrue(Main.validationCheck("Feb 22, 2020"));
        assertTrue(Main.validationCheck("Apr 2, 2034"));
        assertFalse(Main.validationCheck("Май 22, 2020"));
        assertFalse(Main.validationCheck("Feb 22, 20"));
        assertFalse(Main.validationCheck("02, 22, 20"));
    }

    @Test
    public void quantityProduct() {
        assertEquals(0, sp.quantityAllProduct(store));
        assertEquals(0, sp.quantityAllProduct(store2));
    }

    @Test
    public void equalsObject() {
        assertNotEquals(store, store2);
        assertEquals(product, product2);
    }
}
