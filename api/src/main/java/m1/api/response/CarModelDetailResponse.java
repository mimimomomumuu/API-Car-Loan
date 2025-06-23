package m1.api.response;

import lombok.Data;
import m1.api.model.CarModelDTO;

@Data
public class CarModelDetailResponse extends BaseResponse{
    
    private CarModelDTO data;
}
