package com.jamie.service.b.biz;

import com.jamie.api.b.entity.BEntity;
import com.jamie.service.b.dao.BDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Bbiz {

    @Autowired
    private BDao bDao;

    public int insertB(BEntity bEntity){
        return bDao.insertB(bEntity);
    }

}
