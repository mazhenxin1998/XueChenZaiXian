package com.mzx.framework.model.ucenter.ext;

import com.mzx.framework.model.ucenter.XcMenu;
import com.mzx.framework.model.ucenter.XcUser;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * Created by admin on 2018/3/20.
 */
@Data
@ToString
public class XcUserExt extends XcUser {

    //权限信息
    private List<XcMenu> permissions;

    //企业信息
    private String companyId;
}
