import ie.kasparas.Config.Config;
import ie.kasparas.entities.Tenant;
import ie.kasparas.services.TenantService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Config.class)
public class TestTenantService {
    @Autowired
    TenantService tenantService;

    @Test
    public void testSearchTenantsByIncorrectEircode(){
        List<Tenant> tenants = tenantService.searchTenantsByEircode("AAAAAA");
        assertTrue(tenants.isEmpty());
    }

    @Test
    public void testReturnAllTenants(){
        List<Tenant> tenants = tenantService.tenants();
        assertEquals(10, tenants.size());
        tenantService.deleteTenant("byrdie.gardner@gmail.com");
        tenants = tenantService.tenants();
        assertEquals(9, tenants.size());
    }

    @Test
    public void addExistingTenant(){
        int t = tenantService.addNewTenant("AAAAAA", "39581985135", "First", "Last", "phil.younh@gmail.com");
        assertEquals(0, t);
    }
}
