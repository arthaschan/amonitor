package com.szatc.amonitor.calculate;

import com.szatc.amonitor.ibatis.entity.TaxiCircuit;
import com.szatc.amonitor.ibatis.entity.TaxiConflict;
import com.szatc.amonitor.ibatis.entity.TaxiPoint;
import com.szatc.amonitor.ibatis.entity.Point;
import com.szatc.amonitor.util.Formula;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 计算飞机的状态
 */
@Component
public class Cal implements ICal {

    private static Logger logger = Logger.getLogger(Cal.class);
    @Value("${sys.minDistance}")
    public  float minDistance=5;
    /**
     * 计算飞机任意一个时刻的坐标。
     * @param line
     * @param time
     * @return
     */
    @Override
    public Point getStatus(TaxiCircuit line, float time) {
        // 前提：飞机的滑行时间已经保存在t 里。
        if(line==null||line.getRoute()==null||line.getRoute().size()<=0)
            return null;
        Point point=new Point();
        if(time>line.getMaxSlipTime())
        {//滑行到最后一个点后，飞机不动了。
            TaxiPoint p=line.getLastPlanePoint();
            point=p.getPoint().clone();
            return point;
        }
        try {
            for (int i = 0; i < line.getRoute().size() - 1; i++) {
                TaxiPoint taxiPoint1 = line.getRoute().get(i);//p  当前点，当前路段的起点
                TaxiPoint taxiPoint2 = line.getRoute().get(i + 1);//p2 下一个点，当前路段的尾点
                if (time < taxiPoint1.getT() || time > taxiPoint2.getT())
                    continue;
                double slipTime = time - taxiPoint1.getT();//在当前路线上滑行的时间是slipTime;

                //计算k，b
                //竖着走

                Point p1 = taxiPoint1.getPoint();
                Point p2 = taxiPoint2.getPoint();
                if (p2.getX() == p1.getX()) {
                    point.setX(taxiPoint1.getPoint().getX());
                    // 注意是朝上走
                    if (p2.getY() > p1.getY()) {
                        point.setY(p1.getY() + slipTime * taxiPoint1.getV());
                    } else {
                        //朝下走
                        point.setY(p1.getY() - slipTime * taxiPoint1.getV());
                    }
                    break;
                } else if (p2.getY() == p1.getY()) {
                    // 注意朝右走
                    point.setY(p1.getY());
                    if (p2.getX() > p1.getX()) {
                        point.setX(p1.getX() + slipTime * taxiPoint1.getV());
                    } else {
                        //朝左走
                        point.setX(p1.getX() - slipTime * taxiPoint1.getV());
                    }
                    break;
                } else {// 斜着走
                    double k = (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());
                    double L = slipTime * taxiPoint1.getV();
                    double b = p1.getY() - k * p1.getX();//b=y-kx
                    double delta = L / Math.sqrt(1 + k * k);// L/√（1+k²）

                    //        若已知直线过两点(x1,y1)和(x2,y2)，求该直线解析式。
//        若x1<>x2  ，设该直线解析式为y=kx+b，将点(x1,y1)和(x2,y2)代入该解析式中，得：
//        y1=kx1+b;y2=kx2+b;k=(y2-y1)/(x2-x1) ,b=y1-  (y2-y1)/(x2-x1)*x1.
                    //已知直线解析式，和2点的距离，求第2个点。
//                 设直线上距点P距离为L的点坐标为（m,n）
//                 该点在直线上：n=km+b       ——1式
//                 该点距离点P为L：(m-x1)²+(n-y1)²=L²   ——2式
//                 其中：y1=ax1+b——3式
//                 将1,3式代入2式解得：m=x1+L/√（1+k²）,n=kx1+kL/√（1+k²）+b,
//                         或：m=x1-L/√（1+k²）,n=kx1-kL/√（1+k²）+b
                    if (p2.getX() > p1.getX()) {//朝右走
                        point.setX(p1.getX() + delta);
                        point.setY(point.getX() * k + b);//y=kx+b
                    } else {//朝左走
                        point.setX(p1.getX() - delta);
                        point.setY(point.getX() * k + b);//y=kx+b
                    }
                    break;
                }
            }
        }catch (Exception ex){
            logger.error(ex.toString());
        }
        return point;
    }

    @Override
    public TaxiConflict getConflict(TaxiCircuit f1, TaxiCircuit f2) {

        // 理论上计算任意一个时刻2个点的距离
        //现实生活中，如果2个飞机的路线没有交叉，则一定不会出现冲突。因为路线设计就避免了这种冲突。

        if(f1==null||f2==null)
            return null;
        double maxTime=f1.getMaxSlipTime()>f2.getMaxSlipTime()?f1.getMaxSlipTime():f2.getMaxSlipTime();
        try{
            for(int i=1;i<maxTime;i++){
                Point p1=getStatus(f1,i);
                Point p2=getStatus(f2,i);

                double distance= Formula.distance(p1,p2);
                if(distance<=minDistance) {
                    TaxiConflict planeConflict=new TaxiConflict(f1,f2,i,p1,p2);
                    logger.error(planeConflict.toString());
                    return planeConflict;
                }
            }
        }
        catch (Exception ex){
            logger.error(ex.toString());
        }
        return null;
    }





}
