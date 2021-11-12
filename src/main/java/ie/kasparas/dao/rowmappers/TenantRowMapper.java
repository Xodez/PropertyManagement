package ie.kasparas.dao.rowmappers;

import ie.kasparas.entities.Tenant;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TenantRowMapper implements RowMapper<Tenant> {
    public Tenant mapRow(ResultSet rs, int rowNumber) throws SQLException{
        Tenant tenant = new Tenant();
        tenant.setEmail(rs.getString("email"));
        tenant.setFirstName(rs.getString("firstName"));
        tenant.setEircode(rs.getString("eircode"));
        tenant.setLastName(rs.getString("lastName"));
        tenant.setPhone(rs.getInt("phone"));
        return tenant;
    }
}
