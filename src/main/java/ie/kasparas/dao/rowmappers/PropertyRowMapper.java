package ie.kasparas.dao.rowmappers;

import ie.kasparas.entities.Property;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PropertyRowMapper implements RowMapper<Property> {
    public Property mapRow(ResultSet rs, int rowNumber) throws SQLException{
        Property property = new Property();
        property.setCapacity(rs.getInt("capacity"));
        property.setCost(rs.getFloat("cost"));
        property.setEircode(rs.getString("eircode"));
        property.setOccupants(rs.getInt("occupants"));
        return property;
    }
}
