package com.jamie.service.a.dao;

import com.jamie.api.a.entity.AEntity;
import com.jamie.api.a.vo.AVo;
import com.jamie.common_dao.BaseDao;
import com.jamie.common_util.redis.RedisConstant;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class ADao extends BaseDao<AEntity> {

    // 删除指定的Redis缓存，防止Redis出现脏数据
    @CacheEvict(value = RedisConstant.dataA, key = "#aEntity.getId()")
    public int insertA(AEntity aEntity){
        return singleInsert(aEntity);
    }

    // 删除指定的Redis缓存，防止Redis出现脏数据
    @CacheEvict(value = RedisConstant.dataA, key = "#aEntity.getId()")
    public int updateA(AEntity aEntity){
        return update(aEntity);
    }

    public AEntity getLatestData(){
        return getSqlSessionTemplate().selectOne(getStatement("getLatestData"));
    }

    // 根据条件查询
    // 使用@Cacheable有个坑：如果key是字符串，必须加上''，如果key是使用变量，则要加上#
    @Cacheable(value = RedisConstant.dataA, key = "#aVo.getId()")
    public AEntity getDataBy(AVo aVo){
        HashMap<String, Object> param = new HashMap<>();

        if (null != aVo.getMsg()){
            param.put("msg", aVo.getMsg());
        }
        else if (null != aVo.getId()){
            param.put("id", aVo.getId());
        }
        return getSqlSessionTemplate().selectOne(getStatement("getDataABy"), param);
    }
}
