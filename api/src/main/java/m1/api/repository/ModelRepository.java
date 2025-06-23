package m1.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import m1.api.entity.CarModel;

@Repository
public interface ModelRepository extends JpaRepository<CarModel, Integer> {
    
    List<CarModel> findByMakeId(Integer makeId);
}
