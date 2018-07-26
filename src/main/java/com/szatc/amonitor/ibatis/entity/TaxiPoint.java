package com.szatc.amonitor.ibatis.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TaxiPoint")
public class TaxiPoint {

    private int id;

    //序号
    private int sequence;
    private  String plane;
    private double t;

    private double v;

    @XmlElement(name = "V")
    public double getV() {
        return v;
    }

    public void setV(double v) {
        this.v = v;
    }

    private Point point;

    @XmlElement(name = "Point")
    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    @XmlElement(name = "T")
    public double getT() {
        return t;
    }

    public void setT(double t) {
        this.t = t;
    }



    @XmlElement(name = "Plane")
    public String getPlane() {
        return plane;
    }

    public void setPlane(String plane) {
        this.plane = plane;
    }


    @XmlElement(name = "Sequence")
    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    @XmlElement(name = "Id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public  TaxiPoint(){

    }
    public TaxiPoint(Point point, double v){
      this.point = point;
      this.v=v;
    }

    public TaxiPoint(int id, Point point, double v){
        this.id=id;
        this.point = point;
        this.v=v;
    }

    public TaxiPoint(int id, Point point, double v, double t){
        this.id=id;
        this.point = point;
        this.v=v;
        this.t=t;
    }

    public TaxiPoint(int id, int sequence, Point point, double v){
        this.id=id;
        this.sequence=sequence;
        this.point = point;
        this.v=v;
        this.t=t;
    }
}
