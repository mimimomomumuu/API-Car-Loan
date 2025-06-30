package m1.api.request;

import lombok.Data;

@Data
public class LoanCalculationRequest {
    
    private double carPrice;

    private int downPayment;

    private int modelYear;

}
