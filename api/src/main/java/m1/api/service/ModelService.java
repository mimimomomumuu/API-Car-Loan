package m1.api.service;

import m1.api.response.CarModelDetailResponse;
import m1.api.response.ListCarModelResponse;

public interface ModelService {

    //ดึงรายการรุ่นรถตาม makeId
    public ListCarModelResponse listModel(int makeId);

    //สำหรับดึงรายละเอียดรุ่นรถ 1 คัน ด้วย modelId
    public CarModelDetailResponse getModelDetailById(int modelId);
}
