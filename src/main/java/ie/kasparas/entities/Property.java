package ie.kasparas.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Property {
    private String eircode;
    private int capacity;
    private int occupants;
    private float cost;
}
