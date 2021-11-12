package ie.kasparas.dao;

import ie.kasparas.dao.rowmappers.TenantRowMapper;
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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Repository
@Slf4j
public class TenantRepositorySQL implements TenantRepository {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public int addNewTenant(String eircode, int phone, String firstName, String lastName, String email) {
        String SQL = "INSERT INTO property(email, firstname, lastname, phone, eircode) VALUES (:email, :firstName, :lastName, :phone, :eircode)";
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
        return 0;
    }

    @Override
    public List<Tenant> searchTenantsByEircode(String eircode) {
        try {
            String SQL = "SELECT * FROM tenants WHERE eircode = :key";
            SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("key", eircode);
            return namedParameterJdbcTemplate.query(SQL, namedParameters, new TenantRowMapper());
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public boolean exists(String eircode) {
        return false;
    }
}
