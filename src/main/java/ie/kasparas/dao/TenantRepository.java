package ie.kasparas.dao;

import ie.kasparas.entities.Tenant;

import java.util.List;

public interface TenantRepository {
    // Add a new tenant
    int addNewTenant(String eircode, int phone, String firstName, String lastName, String email);

    // Move a tenant from one property to another
    int moveTenant(String email);

    // Delete a tenant
    int deleteTenant(String email);

    // Search for tenants by Eircode
    List<Tenant> searchTenantsByEircode(String eircode);

    // Check if tenant exists
    boolean exists(String eircode);
}
