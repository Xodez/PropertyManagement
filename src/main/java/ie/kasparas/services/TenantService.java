package ie.kasparas.services;

import ie.kasparas.entities.Tenant;

import java.util.List;

public interface TenantService {
    // Add a new tenant
    int addNewTenant(String eircode, String phone, String firstName, String lastName, String email);

    // Move a tenant from one property to another
    int moveTenant(String email);

    // Delete a tenant
    int deleteTenant(String email);

    // Search for tenants by Eircode
    List<Tenant> searchTenantsByEircode(String email);

    List<Tenant> tenants();

    Tenant searchTenantsByEmail(String email);

    void updateTenantProperty(String email, String eircode);
}
