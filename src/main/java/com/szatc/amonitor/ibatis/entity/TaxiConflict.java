package com.szatc.amonitor.ibatis.entity;

import java.text.MessageFormat;

/**
 * 飞机冲突信息
 * 飞机A B 在时间t 产生冲突，此刻A 在pA，B 在pB.
 */
public class TaxiConflict {

    private TaxiCircuit a;

    private TaxiCircuit b;

    private int t;

    public TaxiCircuit getA() {
        return a;
    }

    public void setA(TaxiCircuit a) {
        this.a = a;
    }

    public TaxiCircuit getB() {
        return b;
    }

    public void setB(TaxiCircuit b) {
        this.b = b;
    }

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }

    public Point getpA() {
        return pA;
    }

    public void setpA(Point pA) {
        this.pA = pA;
    }

    public Point getpB() {
        return pB;
    }

    public void setpB(Point pB) {
        this.pB = pB;
    }

    private Point pA;

    private Point pB;


    @Override
    public String toString() {
        if(a==null||b==null)
            return "";
        String s="";
        try {
            s = MessageFormat.format("飞机{0}和飞机{1}在时刻{2}产生冲突，当时他们分别在{3}和{4}", a.getPlane(), b.getPlane(), t, pA, pB);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return s;
    }
    public TaxiConflict(TaxiCircuit a, TaxiCircuit b, int t, Point pA, Point pB){
        this.a=a;
        this.b=b;
        this.t=t;
        this.pA=pA;
        this.pB=pB;
    }

    public TaxiConflict(){

    }
}
