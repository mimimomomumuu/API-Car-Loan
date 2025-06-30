package m1.api.model;

import lombok.Data;

@Data
public class InstallmentOptionDTO {
    
    private int installmentMonths;
    private double monthlyPayment;
    private double totalInterest;
    private double totalPayment;

}
