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
        Property property;
        List<Tenant> tenants;
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

                }
                case 10 -> System.exit(0);
            }
        }
    }
}
