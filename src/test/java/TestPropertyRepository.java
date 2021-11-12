import ie.kasparas.Config.Config;
import ie.kasparas.dao.PropertyRepository;
import ie.kasparas.entities.Property;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Config.class)
public class TestPropertyRepository {
    @Autowired
    PropertyRepository propertyRepository;

    @Test
    public void testFindPropertyByEircode(){
        Property property = propertyRepository.searchPropertyByEircode("G58F52");
        assertEquals(3443.49, property.getCost(), 0.9);
    }

    @Test
    public void testFindPropertyByEircodeDoesNotExist(){
        Property property = propertyRepository.searchPropertyByEircode("DOESNTEXIST");
        assertNull(property);
    }

    @Test
    public void testAddNewProperty(){
        int property = propertyRepository.addNewProperty("AAAAAA", 4, 2, 1010.00f);
        assertNotEquals(0, property);
    }

    @Test
    public void testHouseWithNoSpace(){
        int property = propertyRepository.houseWithNoSpace();
        assertEquals(1, property);
    }
}
