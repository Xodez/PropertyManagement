package ie.kasparas.services;

import ie.kasparas.dao.rowmappers.PropertyRowMapper;
import ie.kasparas.entities.Property;

import java.util.List;

public interface PropertyService {
    // Search for a house by Eircode
    Property searchPropertyByEircode(String eircode);

    // View a list of houses
    List<Property> properties();

    // View a list of houses with space in them
    List<Property> propertiesWithSpace();

    // Add a new property
    int addNewProperty(String eircode, int capacity, int occupants, float cost);

    boolean exists(String eircode);

    boolean hasCapacity(String eircode);

    void updateOccupants(String eircode, String addOrRemove);

    void deleteProperty(String eircode);
}
