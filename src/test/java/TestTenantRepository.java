import ie.kasparas.Config.Config;
import ie.kasparas.dao.TenantRepository;
import ie.kasparas.entities.Tenant;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Config.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestTenantRepository {
    @Autowired
    TenantRepository tenantRepository;

    @Test
    @Order(1)
    public void testRemoveAllTenant() {
        List<Tenant> tenants = tenantRepository.tenants();
        for (Tenant value : tenants) {
            tenantRepository.deleteTenant(value.getEmail());
        }
        tenants = tenantRepository.tenants();
        assertTrue(tenants.isEmpty());
    }

    @Test
    @Order(2)
    public void testAddTenant() {
        tenantRepository.addNewTenant("G58F52", "354968283", "person", "people", "person@gmail.com");
        assertTrue(tenantRepository.exists("person@gmail.com"));
    }

    @Test
    @Order(3)
    public void testChangeTenantProperty() {
        tenantRepository.updateTenantProperty("person@gmail.com", "E46Z78");
        Tenant tenant = tenantRepository.searchTenantsByEmail("person@gmail.com");
        assertEquals("E46Z78", tenant.getEircode());
    }
}
