package m1.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import m1.api.entity.InterestRate;

@Repository
public interface InterestRateRepository extends JpaRepository<InterestRate, Integer> {
    

}
