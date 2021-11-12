package ie.kasparas;

import ie.kasparas.Config.Config;
import ie.kasparas.entities.Property;
import ie.kasparas.entities.Tenant;
import ie.kasparas.services.PropertyService;
import ie.kasparas.services.PropertyServiceImplementation;
import ie.kasparas.services.TenantService;
import ie.kasparas.services.TenantServiceImplementation;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        PropertyService propertyService = context.getBean(PropertyServiceImplementation.class);
        TenantService tenantService = context.getBean(TenantServiceImplementation.class);
        Menu menu = new Menu();
        int option = 0;
        List<Property> properties;
        String eircode;
        String email;
        Property property;
        List<Tenant> tenants;
        Tenant tenant;
        float averageTenants;
        float rent;
        int housesWithNoSpace;
        while (true) {
            option = menu.menuStart();
            switch (option) {
                case 1 -> {
                    properties = propertyService.properties();
                    for (Property value : properties) {
                        System.out.println(value.getEircode());
                    }
                    eircode = menu.askForEircode();
                    property = propertyService.searchPropertyByEircode(eircode);
                    tenants = tenantService.searchTenantsByEircode(eircode);
                    menu.printProperty(property);
                    if (property.getOccupants() != 0) {
                        menu.printTenants(tenants);
                    } else {
                        System.out.println("This property has no tenants");
                    }
                }
                case 2 -> {
                    properties = propertyService.properties();
                    menu.printProperties(properties);
                }
                case 3 -> {
                    properties = propertyService.propertiesWithSpace();
                    menu.printProperties(properties);
                }
                case 4 -> {
                    property = menu.addNewPropertyDetails();
                    int confirmation = propertyService.addNewProperty(property.getEircode(), property.getCapacity(), property.getOccupants(), property.getCost());
                    if (confirmation != 0) {
                        System.out.println("Added successfully");
                    } else {
                        System.out.println("Property with the same eircode already exists in the database");
                    }
                }
                case 5 -> {
                    properties = propertyService.properties();
                    for (Property value : properties) {
                        System.out.println(value.getEircode());
                    }
                    tenant = menu.addNewTenantDetails();
                    int confirmation = 0;
                    if (propertyService.exists(tenant.getEircode())) {
                        if (propertyService.hasCapacity(tenant.getEircode())) {
                            confirmation = tenantService.addNewTenant(tenant.getEircode(), tenant.getPhone(), tenant.getFirstName(), tenant.getLastName(), tenant.getEmail());
                        } else {
                            System.out.println("This property has no more capacity");
                            break;
                        }
                    } else {
                        System.out.println("Eircode entered does not exist in database");
                        break;
                    }
                    if (confirmation != 0) {
                        System.out.println("Added successfully");
                        propertyService.updateOccupants(tenant.getEircode(), "add");
                    } else {
                        System.out.println("Tenant with the same email already exists in the database");
                    }
                }
                case 6 -> {
                    tenants = tenantService.tenants();
                    for (Tenant value : tenants) {
                        System.out.println(value.getEmail());
                    }
                    email = menu.askForEmail();
                    tenant = tenantService.searchTenantsByEmail(email);
                    properties = propertyService.properties();
                    for (Property value : properties) {
                        System.out.println(value.getEircode());
                    }
                    eircode = menu.askForEircode();
                    propertyService.updateOccupants(tenant.getEircode(), "remove");
                    propertyService.updateOccupants(eircode, "add");
                    tenantService.updateTenantProperty(email, eircode);
                    System.out.println("Tenant " + email + " has been moved to the property " + eircode);
                }
                case 7 -> {
                    properties = propertyService.properties();
                    for (Property value : properties) {
                        System.out.println(value.getEircode());
                    }
                    eircode = menu.askForEircode();
                    tenants = tenantService.searchTenantsByEircode(eircode);
                    for (Tenant value : tenants) {
                        tenantService.deleteTenant(value.getEmail());
                    }
                    propertyService.deleteProperty(eircode);
                    System.out.println("Property " + eircode + " along with all of its tenants has been deleted successfully");
                }
                case 8 -> {
                    tenants = tenantService.tenants();
                    for (Tenant value : tenants) {
                        System.out.println(value.getEmail());
                    }
                    email = menu.askForEmail();
                    eircode = tenantService.searchTenantsByEmail(email).getEircode();
                    tenantService.deleteTenant(email);
                    propertyService.updateOccupants(eircode, "remove");
                    System.out.println("Tenant " + email + " has been removed");
                }
                case 9 -> {
                    averageTenants = propertyService.averageTenants();
                    rent = propertyService.rent();
                    housesWithNoSpace = propertyService.housesWithNoSpace();

                    System.out.println("Average tenants per property: " + averageTenants);
                    System.out.printf("Total income from properties: $%.2f\n", rent);
                    System.out.println("Houses with no space: " + housesWithNoSpace);
                }
                case 10 -> System.exit(0);
            }
        }
    }
}
