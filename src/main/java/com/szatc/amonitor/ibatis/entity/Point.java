package com.szatc.amonitor.ibatis.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Point")
public class Point {

    private double x;
    private double y;

    @XmlElement(name = "X")
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    @XmlElement(name = "Y")
    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }


    @Override
    public Point clone()  {
        Point p=new Point();
        p.setX(this.x);
        p.setY(this.y);
        return p;
    }

    public  Point (){

    }
    public Point(double x,double y){
        this.x=x;
        this.y=y;
    }
    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y  +
                '}';
    }
}
