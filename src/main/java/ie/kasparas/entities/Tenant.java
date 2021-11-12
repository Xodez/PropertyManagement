package ie.kasparas.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tenant {
    private String eircode;
    private String email;
    private String firstName;
    private String lastName;
    private int phone;
}
