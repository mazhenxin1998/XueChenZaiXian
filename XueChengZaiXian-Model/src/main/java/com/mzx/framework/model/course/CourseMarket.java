package com.mzx.framework.model.course;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ZhenXinMa
 * @date 2020/3/22 22:02
 */
@Data
@ToString
@Getter
@Setter
public class CourseMarket implements Serializable {

    private static final long serialVersionUID = -916357110051689486L;

    private String id;
    private String charge;
    private String valid;
    private String qq;
    private Float price;
    private Float price_old;
    /**
     * private Date expires.
     */
    private Date expires;
    private Date startTime;
    private Date endTime;

}
