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
@Table(name = "MST_CAR_MAKE")
@Data
public class CarMake {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CAR_MAKE_ID")
    private int makeId;

    @Column(name = "CAR_MAKE")
    private String makeName;

    @Column(name = "CAR_MAKE_LOGO_PATH")
    private String makeLogo;

    @Column(name = "ACTIVE_STATUS")
    private boolean activeStatus;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "UPDATED_DATE")
    private Date updatedDate;

    @Column(name = "UPDATED_BY")
    private String updatedBy;
}
