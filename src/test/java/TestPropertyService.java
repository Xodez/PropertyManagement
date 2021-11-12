import ie.kasparas.Config.Config;
import ie.kasparas.entities.Property;
import ie.kasparas.services.PropertyService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Config.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestPropertyService {
    @Autowired
    PropertyService propertyService;

    @Test
    @Order(1)
    public void testAverageTenants(){
        float avg = propertyService.averageTenants();
        assertEquals(1, avg, 0.5);
    }

    @Test
    @Order(2)
    public void testRent(){
        float rent = propertyService.rent();
        assertEquals(39045.99, rent, 0.9);
        propertyService.updateOccupants("G58F52", "add");
        rent = propertyService.rent();
        assertEquals(42489.48, rent, 0.9);
    }

    @Test
    @Order(3)
    public void testPropertyExists(){
        boolean property = propertyService.exists("G58F52");
        assertTrue(property);
    }
}
