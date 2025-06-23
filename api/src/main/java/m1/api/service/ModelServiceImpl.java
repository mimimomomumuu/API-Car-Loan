package m1.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.var;
import m1.api.model.CarModelDTO;
import m1.api.repository.ModelRepository;
import m1.api.response.CarModelDetailResponse;
import m1.api.response.ListCarModelResponse;

@Service
@Transactional
public class ModelServiceImpl implements ModelService {
    
    @Autowired
    ModelRepository modelRepository;

    @Override
    public ListCarModelResponse listModel(int makeId) {

        ListCarModelResponse response = new ListCarModelResponse();

        var models = modelRepository.findByMakeId(makeId);

        if(models == null || models.isEmpty()){
            response.setErrorCode("0004");
            response.setErrorMessage("ไม่พบรุ่นรถสำหรับยี่ห้อนี้");
            response.setData(List.of());
            return response;
        }

        ModelMapper mapper = new ModelMapper();

        var return_models = models.stream().map(model -> mapper.map(model, CarModelDTO.class)).collect(Collectors.toList());

        response.setErrorCode("0000");
        response.setErrorMessage("Success");
        response.setData(return_models);
        return response;
    }

    @Override
    public CarModelDetailResponse getModelDetailById(int modelId) {

        CarModelDetailResponse response = new CarModelDetailResponse();

        var price = modelRepository.findById(modelId);

        if (price == null || price.isEmpty()) { // ถ้าหาไม่เจอ
            response.setErrorCode("0004");
            response.setErrorMessage("ไม่พบข้อมูลรุ่นรถที่ระบุ");
            response.setData(null); // ตั้งค่า data เป็น null เพราะไม่มีข้อมูล
            return response;
        }

        ModelMapper mapper = new ModelMapper();

        CarModelDTO carModelDTO = mapper.map(price.get(), CarModelDTO.class);

        response.setErrorCode("0000");
        response.setErrorMessage("สำเร็จ");
        response.setData(carModelDTO); // ตั้งค่า data เป็น DTO เพียงตัวเดียว
        return response;
    }
}