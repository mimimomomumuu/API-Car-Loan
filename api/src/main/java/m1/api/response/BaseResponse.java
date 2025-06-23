package m1.api.response;

import lombok.Data;

@Data
public class BaseResponse {
    private String errorCode;
    private String errorMessage;
}
