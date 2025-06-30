package m1.api.response;

import java.util.List;

import lombok.Data;
import m1.api.model.InstallmentOptionDTO;

@Data
public class LoanCalculationResponse extends BaseResponse {
    
    List<InstallmentOptionDTO> data;
}
