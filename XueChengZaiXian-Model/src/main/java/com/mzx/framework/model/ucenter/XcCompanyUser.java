package com.mzx.framework.model.ucenter;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * Created by admin on 2018/2/10.
 */
@Data
@ToString
public class XcCompanyUser implements Serializable {

    private static final long serialVersionUID = -916357110051689786L;
    @Id
    private String id;
    private String companyId;
    private String userId;


}
