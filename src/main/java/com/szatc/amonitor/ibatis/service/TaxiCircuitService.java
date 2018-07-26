package com.szatc.amonitor.ibatis.service;

import com.szatc.amonitor.ibatis.entity.TaxiCircuit;
import com.szatc.amonitor.ibatis.entity.TaxiPoint;

import java.util.List;

/**
 * ACDM报文数据访问对象
 *
 * @date 2018/1/4
 */
public interface TaxiCircuitService {

    List<TaxiCircuit> getList(List<TaxiPoint> taxiPointList);

}
