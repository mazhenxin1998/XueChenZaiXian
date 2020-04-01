package com.mzx.servermanager.service.impl;

import com.mzx.common.exception.ThrowException;
import com.mzx.common.model.response.CommonCode;
import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.common.model.response.QueryResult;
import com.mzx.framework.model.course.response.CourseCode;
import com.mzx.framework.model.system.SysDictionary;
import com.mzx.servermanager.dao.ISysDictionDao;
import com.mzx.servermanager.service.ISysDictionaryService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author ZhenXinMa
 * @date 2020/4/1 20:10
 */
@Service
public class SysDictionaryServiceImpl implements ISysDictionaryService {

    @Resource
    private ISysDictionDao sysDictionDao;

    @Override
    public QueryResponseResult getByDName(String DName){

        if(StringUtils.isEmpty(DName)){
            ThrowException.exception(CommonCode.BAD_PARAMETERS);
            /*抛异常*/
        }

        Optional<SysDictionary> o = sysDictionDao.findBydName(DName);
        if( !o.isPresent() ){
            /*如果SysDictionary存在那么就执行*/
            return new QueryResponseResult(CommonCode.SERVER_ERROR,null);
        }
        SysDictionary dictionary = o.get();
        QueryResult queryResult = new QueryResult();
        queryResult.setData(dictionary);

        return new QueryResponseResult(CommonCode.SUCCESS,queryResult);
    }

    @Override
    public QueryResponseResult getByType(String type) {

        if( StringUtils.isEmpty(type) ){
            ThrowException.exception(CommonCode.BAD_PARAMETERS);
        }

        Optional<SysDictionary> o = sysDictionDao.findBydType(type);
        if( !o.isPresent() ){
            return  new QueryResponseResult(CourseCode.COURSE_BAD_PARAMETER_IS_NULL,null);
        }
        SysDictionary dictionary = o.get();
        QueryResult result = new QueryResult();
        result.setData(dictionary);

        return new QueryResponseResult(CommonCode.SUCCESS,result);
    }


}
