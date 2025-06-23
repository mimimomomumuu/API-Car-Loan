package m1.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import m1.api.entity.CarMake;

@Repository
public interface MakeRepository extends JpaRepository<CarMake,Integer> {
    
}
