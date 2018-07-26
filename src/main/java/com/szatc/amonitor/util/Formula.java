package com.szatc.amonitor.util;

import com.szatc.amonitor.ibatis.entity.TaxiPoint;
import com.szatc.amonitor.ibatis.entity.Point;

public class Formula {

    /**
     * 求2点之间的距离
     * @param a
     * @param b
     * @return
     */
    public static double distance(Point a,Point b){
        float t=0;
        if(a==null||b==null)
            return -1;
        if(b.getX()==a.getX()){
            return b.getY()-a.getY();
        }
        double dis=  Math.sqrt( (b.getY()-a.getY())* (b.getY()-a.getY()) +  (b.getX()-a.getX())*  (b.getX()-a.getX()));

        return dis;
    }


    /**
     * 求2个飞机之间的距离
     * @param a
     * @param b
     * @return
     */
    public static double distance(TaxiPoint a, TaxiPoint b){
//        若已知直线过两点(x1,y1)和(x2,y2)，求该直线解析式。
//        若x1<>x2  ，设该直线解析式为y=kx+b，将点(x1,y1)和(x2,y2)代入该解析式中，得：
//        y1=kx1+b;y2=kx2+b;k=(y2-y1)/(x2-x1) ,b=y-  (y2-y1)/(x2-x1)*x1.
//
        float t=0;
        if(a==null||b==null||a.getPoint()==null||b.getPoint()==null)
            return -1;
        Point p1=a.getPoint();
        Point p2=b.getPoint();


        double dis=  distance(p1,p2);

        return dis;
    }
}
