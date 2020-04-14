package com.mzx.login.dao;

import com.mzx.common.model.response.QueryResult;
import com.mzx.login.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * @author ZhenXinMa
 * @date 2020/4/10 16:06
 */
@Mapper
public interface UserDao {

    /**
     * 增加一个临时用户.
     * @param user
     * @return
     */
    void insert(@Param(value = "user") User user);

    /**
     * 通过ID获取到用户.
     * @param id
     * @return
     */
    User getByID(@Param("id") Integer id);

    User getByName(@Param(value = "username") String username);


}
