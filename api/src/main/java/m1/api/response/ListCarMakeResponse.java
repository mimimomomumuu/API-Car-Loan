package m1.api.response;

import java.util.List;

import lombok.Data;
import m1.api.model.CarMakeDTO;

@Data
public class ListCarMakeResponse extends BaseResponse {
    List<CarMakeDTO> data;
}
