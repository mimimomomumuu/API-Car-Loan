package m1.api.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table (name = "MST_INTEREST_RATE")
@Data
public class InterestRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INTEREST_RATE_ID")
    private int rateId;

    @Column(name = "INTEREST_RATE_CAR_YEAR_FROM")
    private int yearFrom;

    @Column(name = "INTEREST_RATE_CAR_YEAR_TO")
    private int yearTo;

    @Column(name = "INSTALLMENT_MONTHS")
    private int installmentMonths;

    @Column(name = "INTEREST_RATE")
    private double rate;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "UPDATED_DATE")
    private Date updatedDate;

    @Column(name = "UPDATED_BY")
    private String updatedBy;
    
}
