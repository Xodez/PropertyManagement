package ie.kasparas.dao;

import ie.kasparas.dao.rowmappers.PropertyRowMapper;
import ie.kasparas.dao.rowmappers.TenantRowMapper;
import ie.kasparas.entities.Property;
import ie.kasparas.entities.Tenant;
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
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Repository
@Slf4j
public class TenantRepositorySQL implements TenantRepository {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public int addNewTenant(String eircode, String phone, String firstName, String lastName, String email) {
        String SQL = "INSERT INTO tenants(email, firstname, lastname, phone, eircode) VALUES (:email, :firstName, :lastName, :phone, :eircode)";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("email", email)
                .addValue("firstName", firstName).addValue("lastName", lastName).addValue("phone", phone).addValue("eircode", eircode);
        return namedParameterJdbcTemplate.update(SQL, namedParameters);
    }

    @Override
    public int moveTenant(String email) {
        return 0;
    }

    @Override
    public int deleteTenant(String email) {
        String SQL = "DELETE FROM tenants WHERE email = :email";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("email", email);
        return namedParameterJdbcTemplate.update(SQL, namedParameters);
    }

    @Override
    public List<Tenant> searchTenantsByEircode(String eircode) {
        try {
            String SQL = "SELECT * FROM tenants WHERE eircode = :key";
            SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("key", eircode);
            return namedParameterJdbcTemplate.query(SQL, namedParameters, new TenantRowMapper());
        } catch (Exception ex) {
            log.error("Error");
            return null;
        }
    }

    @Override
    public boolean exists(String email) {
        try {
            String SQL = "SELECT * FROM tenants WHERE email = :key";
            SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("key", email);
            List<Tenant> result = namedParameterJdbcTemplate.query(SQL, namedParameters, new TenantRowMapper());
            return result.size() != 0;
        } catch (Exception ex) {
            log.error("Error");
            return true;
        }
    }

    @Override
    public List<Tenant> tenants() {
        try {
            return namedParameterJdbcTemplate.query("SELECT * FROM tenants", new TenantRowMapper());
        } catch (Exception ex) {
            log.error("Error");
            return null;
        }
    }

    @Override
    public Tenant searchTenantsByEmail(String email) {
        try {
            String SQL = "SELECT * FROM tenants WHERE email = :key";
            SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("key", email);
            return namedParameterJdbcTemplate.queryForObject(SQL, namedParameters, new TenantRowMapper());
        } catch (Exception ex) {
            log.error("Error");
            return null;
        }
    }

    @Override
    public void updateTenantProperty(String email, String eircode) {
        try {
            String SQL = "UPDATE tenants SET eircode = :eircode WHERE email = :email";
            SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("eircode", eircode).addValue("email", email);
            namedParameterJdbcTemplate.update(SQL, namedParameters);
        } catch (Exception ex) {
            log.error("Error");
        }
    }
}
