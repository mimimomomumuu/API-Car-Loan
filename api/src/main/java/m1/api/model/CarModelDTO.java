package m1.api.model;

import lombok.Data;

@Data
public class CarModelDTO {
   
    private int modelId;

    private String modelName;

    private int modelYear;
    
    private int MakeId;

    private double carPrice;

    private boolean activeStatus;
}
