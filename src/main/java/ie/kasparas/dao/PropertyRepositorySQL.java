package ie.kasparas.dao;

import ie.kasparas.dao.rowmappers.PropertyRowMapper;
import ie.kasparas.entities.Property;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Repository
@Slf4j
public class PropertyRepositorySQL implements PropertyRepository {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Property searchPropertyByEircode(String eircode) {
        try {
            String SQL = "SELECT * FROM property WHERE eircode = :key";
            SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("key", eircode);
            return namedParameterJdbcTemplate.queryForObject(SQL, namedParameters, new PropertyRowMapper());
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<Property> properties() {
        try {
            return namedParameterJdbcTemplate.query("SELECT * FROM property", new PropertyRowMapper());
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<Property> propertiesWithSpace() {
        try {
            return namedParameterJdbcTemplate.query("SELECT * FROM property WHERE capacity > occupants", new PropertyRowMapper());
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public int addNewProperty(String eircode, int capacity, int occupants, float cost) {
        String SQL = "INSERT INTO property(eircode, capacity, occupants, cost) VALUES (:eircode, :capacity, :occupants, :cost)";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("eircode", eircode)
                .addValue("capacity", capacity).addValue("occupants", occupants).addValue("cost", cost);
        return namedParameterJdbcTemplate.update(SQL, namedParameters);
    }

    @Override
    public boolean exists(String eircode) {
        try {
            String SQL = "SELECT * FROM property WHERE eircode = :key";
            SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("key", eircode);
            List<Property> result = namedParameterJdbcTemplate.query(SQL, namedParameters, new PropertyRowMapper());
            return result.size() != 0;
        } catch (Exception ex) {
            return true;
        }
    }

}
