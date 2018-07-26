package com.szatc.amonitor.util;

import com.szatc.amonitor.ibatis.entity.Point;

public class PointFactory {

    /**
     * 根据名字获取点
     * @param name
     * @return
     */
    public Point getPoint(String name){
        Point p=new Point(0,0);
        switch(name){
            case "p010":
                p=new Point(0,10);
                break;
            case "p100":
                p=new Point(10,0);
                break;
            case "p1010":
                p=new Point(10,10);
                break;

            default:
                    return p;

        }
        return null;
    }
}
