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
@Table(name = "MST_CAR_MODEL")
@Data
public class CarModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CAR_MODEL_ID")
    private int modelId;

    @Column(name = "CAR_MODEL_NAME")
    private String modelName;

    @Column(name = "CAR_MODEL_YEAR")
    private int modelYear;
    
    @Column(name = "CAR_MAKE_ID")
    private int makeId;

    @Column(name = "CAR_PRICE")
    private double modelPrice;

    @Column(name = "CAR_MODEL_IMG_PATH")
    private String modelImg;

    @Column(name = "CAR_MODEL_VIDEO_PATH")
    private String modelVdo;

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
