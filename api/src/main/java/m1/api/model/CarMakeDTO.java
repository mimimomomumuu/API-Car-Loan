package m1.api.model;

import lombok.Data;

@Data
public class CarMakeDTO {
    
    private int makeId;

    private String makeName;

    private boolean activeStatus;
}
