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
    public int addNewTenant(String eircode, String phone, String firstName, String lastName, String email) {
        if (!tenantRepository.exists(email)){
            return tenantRepository.addNewTenant(eircode, phone, firstName, lastName,email);
        }
        return 0;
    }

    @Override
    public int moveTenant(String email) {
        return 0;
    }

    @Override
    public int deleteTenant(String email) {
        return tenantRepository.deleteTenant(email);
    }

    @Override
    public List<Tenant> searchTenantsByEircode(String eircode) {
        return tenantRepository.searchTenantsByEircode(eircode);
    }

    @Override
    public List<Tenant> tenants() {
        return tenantRepository.tenants();
    }

    @Override
    public Tenant searchTenantsByEmail(String email) {
        return tenantRepository.searchTenantsByEmail(email);
    }

    @Override
    public void updateTenantProperty(String email, String eircode) {
        tenantRepository.updateTenantProperty(email, eircode);
    }
}
