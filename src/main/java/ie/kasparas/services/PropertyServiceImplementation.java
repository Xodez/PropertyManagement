package ie.kasparas.services;

import ie.kasparas.dao.PropertyRepository;
import ie.kasparas.entities.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyServiceImplementation implements PropertyService {
    @Autowired
    PropertyRepository propertyRepository;

    @Override
    public Property searchPropertyByEircode(String eircode) {
        return propertyRepository.searchPropertyByEircode(eircode);
    }

    @Override
    public List<Property> properties() {
        return propertyRepository.properties();
    }

    @Override
    public List<Property> propertiesWithSpace() {
        return propertyRepository.propertiesWithSpace();
    }

    @Override
    public int addNewProperty(String eircode, int capacity, int occupants, float cost) {
        if (!propertyRepository.exists(eircode)){
            return propertyRepository.addNewProperty(eircode, capacity, occupants, cost);
        }
        return 0;
    }

}
