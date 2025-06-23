package m1.api.service;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import m1.api.model.CarMakeDTO;
import m1.api.repository.MakeRepository;
import m1.api.response.ListCarMakeResponse;

@Service
@Transactional
public class MakeServiceImpl implements MakeService {
    
    @Autowired
    MakeRepository makeRepository;

    @Override
    public ListCarMakeResponse listMake() {

        ListCarMakeResponse response = new ListCarMakeResponse();

        var makes = makeRepository.findAll();

         if(makes == null || makes.isEmpty()){
            response.setErrorCode("0004");
            response.setErrorMessage("ไม่พบรถยี่ห้อนี้");
            return response;
        }
        response.setErrorCode("0000");
        response.setErrorMessage("Success");

        ModelMapper mapper = new ModelMapper();

        var return_makes = makes.stream().map(make -> mapper.map(make, CarMakeDTO.class)).collect(Collectors.toList());

        response.setData(return_makes);

       return response;
    }
    
}
