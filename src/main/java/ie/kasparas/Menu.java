package ie.kasparas;

import ie.kasparas.entities.Property;
import ie.kasparas.entities.Tenant;

import java.util.List;
import java.util.Scanner;

public class Menu {
    public int menuStart() {
        int selection;
        Scanner input = new Scanner(System.in);
        System.out.println("\n");
        System.out.println("What would you like to do?");
        System.out.println("-------------------------\n");
        System.out.println("1 - Search for a property");
        System.out.println("2 - Show all properties");
        System.out.println("3 - Show properties with space");
        System.out.println("4 - Add new property");
        System.out.println("3 - Show properties with space");
        System.out.println("3 - Show properties with space");
        System.out.println("3 - Show properties with space");
        System.out.println("10 - Quit");

        selection = input.nextInt();
        return selection;
    }

    public String askForEircode() {
        Scanner input = new Scanner(System.in);

        System.out.println("Please type in the eircode you wish to select");

        return input.next();
    }

    public void printProperty(Property property) {
        System.out.println("Eircode: " + property.getEircode());
        System.out.println("Capacity: " + property.getCapacity());
        System.out.println("Occupants: " + property.getOccupants());
        System.out.println("Cost: " + property.getCost());
    }

    public void printProperties(List<Property> properties) {
        for (Property value : properties) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            printProperty(value);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
    }

    public void printTenant(Tenant tenant) {
        System.out.println("Eircode: " + tenant.getEircode());
        System.out.println("Email: " + tenant.getEmail());
        System.out.println("First Name: " + tenant.getFirstName());
        System.out.println("Last Name: " + tenant.getLastName());
        System.out.println("Phone Number: " + tenant.getPhone());
    }

    public void printTenants(List<Tenant> tenants) {

        for (Tenant value : tenants) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            printTenant(value);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
    }

    public Property addNewPropertyDetails() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please type in the eircode in the format of XXXXXX (e.g. A3MNE8)");
        String eircode = input.next().toUpperCase();
        System.out.println("Please enter the capacity");
        int capacity = input.nextInt();
        System.out.println("Please enter the cost in the format of XX~X.XX (e.g. 1500.00)");
        float cost = input.nextFloat();
        return new Property(eircode, capacity, 0, cost);
    }
}
