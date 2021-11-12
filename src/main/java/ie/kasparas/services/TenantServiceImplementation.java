package ie.kasparas.services;

import ie.kasparas.dao.TenantRepository;
import ie.kasparas.entities.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TenantServiceImplementation implements TenantService{
    @Autowired
    TenantRepository tenantRepository;

    @Override
    public int addNewTenant(String eircode, int phone, String firstName, String lastName, String email) {
        return 0;
    }

    @Override
    public int moveTenant(String email) {
        return 0;
    }

    @Override
    public int deleteTenant(String email) {
        return 0;
    }

    @Override
    public List<Tenant> searchTenantsByEircode(String eircode) {
        return tenantRepository.searchTenantsByEircode(eircode);
    }
}
