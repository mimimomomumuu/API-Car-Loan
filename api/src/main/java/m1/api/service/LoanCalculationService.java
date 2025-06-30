package m1.api.service;

import m1.api.request.LoanCalculationRequest;
import m1.api.response.LoanCalculationResponse;

public interface LoanCalculationService {
    
    public LoanCalculationResponse loanCalculation(LoanCalculationRequest request);
}
