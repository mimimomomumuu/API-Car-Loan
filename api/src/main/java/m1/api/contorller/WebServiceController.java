package m1.api.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import m1.api.response.CarModelDetailResponse;
import m1.api.response.ListCarMakeResponse;
import m1.api.response.ListCarModelResponse;
import m1.api.service.MakeService;
import m1.api.service.ModelService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/cars")
public class WebServiceController {
    
    @Autowired
    MakeService makeService;

    @Autowired
    ModelService modelService;

    @GetMapping("/makes")
    public ResponseEntity<ListCarMakeResponse> listMake() {
        try {
            var makes = makeService.listMake();

            return ResponseEntity.ok(makes);
        } catch(Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @GetMapping("/models/{makeId}")
    public ResponseEntity<ListCarModelResponse> listModel(@PathVariable int makeId) {
        
        try {
            var models = modelService.listModel(makeId);

            return ResponseEntity.ok(models);
        } catch(Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        
    }

    @GetMapping("/models/detail/{modelId}")
    public ResponseEntity<CarModelDetailResponse> getModelDetailById(@PathVariable int modelId) {
         try {
            var price = modelService.getModelDetailById(modelId);

            return ResponseEntity.ok(price);
        } catch(Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
}
