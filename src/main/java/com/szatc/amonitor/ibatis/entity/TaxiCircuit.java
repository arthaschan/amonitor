package com.szatc.amonitor.ibatis.entity;

import com.szatc.amonitor.util.Formula;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "TaxiCircuit")
public class TaxiCircuit {

    private String plane;

    private double maxSlipTime;

    private boolean isInit;

    private List<TaxiPoint>  route;

    public TaxiCircuit() {

    }

    public TaxiCircuit(List<TaxiPoint> list){
        if(list==null||list.size()<=0)
            return;
        this.setPlane(list.get(0).getPlane());
        this.setRoute(list);
    }

    public TaxiPoint getLastPlanePoint(){
        if(route==null||route.size()<=0)
            return null;
        return route.get(route.size()-1);
    }

    public TaxiPoint getFirstPlanePoint(){
        if(route==null||route.size()<=0)
            return null;
        return route.get(0);
    }

    @XmlElementWrapper(name="Route")
    @XmlElement(name="TaxiPoint")
    public List<TaxiPoint> getRoute() {
        return route;
    }

    public void setRoute(List<TaxiPoint> route) {
        this.route = route;
    }


    public boolean isInit() {
        return isInit;
    }

    @XmlElement(name="MaxSlipTime")
    public double getMaxSlipTime() {
        return maxSlipTime;
    }

    public void setMaxSlipTime(double maxSlipTime) {
        this.maxSlipTime = maxSlipTime;
    }

    @XmlElement(name="Plane")
    public String getPlane() {
        return plane;
    }

    public void setPlane(String plane) {
        this.plane = plane;
    }


    public TaxiCircuit init(){
        if(this.route==null||this.route.size()<=0)
            return this;
        double max=0;
        for(int i=1;i<=route.size()-1;i++){
            TaxiPoint a=route.get(i-1);
            TaxiPoint b=route.get(i);
            double t1=a.getT();
            double t2=(double) Formula.distance(a,b)/a.getV();
            b.setT(t2+t1);
        }
        this.maxSlipTime=getLastPlanePoint().getT();
        this.isInit=true;
        return this;
    }
}
