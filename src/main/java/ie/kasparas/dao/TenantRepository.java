package ie.kasparas.dao;

import ie.kasparas.entities.Tenant;

import java.util.List;

public interface TenantRepository {
    // Add a new tenant
    int addNewTenant(String eircode, String phone, String firstName, String lastName, String email);

    // Delete a tenant
    int deleteTenant(String email);

    // Search for tenants by Eircode
    List<Tenant> searchTenantsByEircode(String eircode);

    // Check if tenant exists
    boolean exists(String email);

    // Get a list of tenants
    List<Tenant> tenants();

    // Search for a tenant by email
    Tenant searchTenantsByEmail(String email);

    // Move a tenant ot a new property
    void updateTenantProperty(String email, String eircode);
}
