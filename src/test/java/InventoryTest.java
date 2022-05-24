import com.example.inventorymanagementsystem.data.*;
import javafx.collections.ObservableList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class InventoryTest {

    private Inventory SUT;

    private final Part[] testParts = { new InHouse(0, "Wheel", 99.9, 3, 1, 5, 199), new Outsourced(1, "Bike Frame", 159.90, 5, 5, 10, "RAZOR") };
    private final Product[] testProducts = { new Product(0, "Wheel", 99.9, 3, 1, 5), new Product(1, "Bike Frame", 159.90, 5, 5, 10) };

    @Before
    public void setup() {
        SUT = new Inventory();
        SUT.addPart(testParts[0]);
        SUT.addPart(testParts[1]);
        SUT.addProduct(testProducts[0]);
        SUT.addProduct(testProducts[1]);
    }

    @Test
    public void lookupPart_GivenPartOfName_ReturnsProductWithName() {
        ObservableList<Part> results = SUT.lookupPart("Wh");
        assert results.toArray()[0].equals(testParts[0]);
    }

    @Test
    public void lookupPart_GivenWholeName_ReturnsProductWithName() {
        ObservableList<Part> results = SUT.lookupPart("Bike Frame");
        assert results.toArray()[0].equals(testParts[1]);
    }
}