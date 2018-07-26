package com.szatc.amonitor.ibatis.service.impl;

import com.szatc.amonitor.ibatis.entity.TaxiPoint;
import com.szatc.amonitor.ibatis.service.TaxiPointService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author liuweijian
 * @date 2018/1/4
 */
@Service
public class TaxiPointServiceImpl implements TaxiPointService {

    private static Logger logger = Logger.getLogger(TaxiPointServiceImpl.class);



    @Override
    public List<TaxiPoint> getList() {

        try{
            return  null;
        }
        catch (Exception ex){
            logger.error(ex.toString());
        }
        return null;
    }

    @Override
    public boolean deleteList() {
        try{
            return  false;
        }
        catch (Exception ex){
            logger.error(ex.toString());
        }
        return false;
    }
}
