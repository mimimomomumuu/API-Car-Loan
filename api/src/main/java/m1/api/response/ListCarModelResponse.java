package m1.api.response;

import java.util.List;

import lombok.Data;
import m1.api.model.CarModelDTO;

@Data
public class ListCarModelResponse extends BaseResponse {
    
    List<CarModelDTO> data;
}
