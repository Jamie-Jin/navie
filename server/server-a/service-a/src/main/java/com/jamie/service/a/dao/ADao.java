package com.jamie.service.a.dao;

import com.jamie.api.a.entity.AEntity;
import com.jamie.api.a.vo.AVo;
import com.jamie.common_dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class ADao extends BaseDao<AEntity> {

    public int insertA(AEntity aEntity){
        return singleInsert(aEntity);
    }

    public AEntity getLatestData(){
        return getSqlSessionTemplate().selectOne(getStatement("getLatestData"));
    }

    // 根据条件查询
    public AEntity getDataBy(AVo aVo){
        HashMap<String, Object> param = new HashMap<>();
        param.put("msg", aVo.getMsg());
        return getSqlSessionTemplate().selectOne(getStatement("getDataABy"), param);
    }
}
