package com.szatc.amonitor.ibatis.service.impl;

import com.szatc.amonitor.ibatis.entity.TaxiCircuit;
import com.szatc.amonitor.ibatis.entity.TaxiPoint;
import com.szatc.amonitor.ibatis.service.TaxiCircuitService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * @author liuweijian
 * @date 2018/1/4
 */
@Service
public class TaxiCircuitServiceImpl implements TaxiCircuitService {

    private static Logger logger = Logger.getLogger(TaxiCircuitServiceImpl.class);


    @Override
    public List<TaxiCircuit> getList(List<TaxiPoint> taxiPointList) {
        //前提：要求planePointList 里按照飞机和顺序号依次有序。
        List<TaxiCircuit> list=new ArrayList<>();
        String planeA="0";
        String planeB="1";
        boolean isNew=true;
        TaxiCircuit temp=new TaxiCircuit();
        List<TaxiPoint> taxiPoints =new ArrayList<>();
        for (int i = 0; i< taxiPointList.size()-1; i++){
            if(isNew){
                taxiPoints =new ArrayList<>();
                planeA= taxiPointList.get(i).getPlane();
                planeB= taxiPointList.get(i+1).getPlane();
                temp.setPlane(planeA);
                taxiPoints.add(taxiPointList.get(i));
                if(planeA.equals(planeB))
                {
                    isNew=false;
                    if(i+1== taxiPointList.size()-1){
                        //下一个是最后一个，则直接添加进去
                        taxiPoints.add(taxiPointList.get(i+1));
                        temp.setRoute(taxiPoints);
                        list.add(temp);
                        break;
                    }
                }else {
                    //下一个和当前的不同，则是新的了
                    temp.setRoute(taxiPoints);
                    list.add(temp);
                    temp=new TaxiCircuit();
                }
            }else{
                //如果当前的这个不是新的，则表示上一次对比发现当前的这个和上一个是同一个飞机的。
                //则不用更新飞机名。
                taxiPoints.add(taxiPointList.get(i));
                planeA= taxiPointList.get(i).getPlane();
                planeB= taxiPointList.get(i+1).getPlane();
                if(planeA.equals(planeB))
                {
                    isNew=false;
                    if(i+1== taxiPointList.size()-1){
                        //下一个是最后一个，则直接添加进去
                        taxiPoints.add(taxiPointList.get(i+1));
                        temp.setRoute(taxiPoints);
                        list.add(temp);
                        break;
                    }

                }else {
                    //下一个和当前的不同，则是新的了
                    temp.setRoute(taxiPoints);
                    list.add(temp);
                    temp=new TaxiCircuit();
                }
            }

        }
        return list;
    }
}
