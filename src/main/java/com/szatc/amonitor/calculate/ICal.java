package com.szatc.amonitor.calculate;

import com.szatc.amonitor.ibatis.entity.TaxiCircuit;
import com.szatc.amonitor.ibatis.entity.TaxiConflict;
import com.szatc.amonitor.ibatis.entity.Point;

public interface ICal {
    /**
     * 根据折线计算 任意一个时刻time 飞机的位置。
      * @param line
     * @return
     */
    public Point getStatus(TaxiCircuit line, float time);

    /**
     * 计算2个飞机会产生冲突的时刻
     * @param f1
     * @param f2
     * @return
     */
   public TaxiConflict getConflict(TaxiCircuit f1, TaxiCircuit f2);


}
