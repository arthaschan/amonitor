package com.szatc.amonitor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.szatc.amonitor.calculate.Cal;
import com.szatc.amonitor.calculate.ICal;
import com.szatc.amonitor.ibatis.entity.Point;
import com.szatc.amonitor.ibatis.entity.TaxiCircuit;
import com.szatc.amonitor.ibatis.entity.TaxiConflict;
import com.szatc.amonitor.ibatis.entity.TaxiPoint;
import com.szatc.amonitor.util.FileUtil;
import com.szatc.amonitor.util.XmlUtil;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AmonitorApplicationTests {

	private static Logger logger = Logger.getLogger(AmonitorApplicationTests.class);
	@Test
	public void contextLoads() {
	}



	private void testConflict(){
		ICal c=new Cal();
		TaxiCircuit line1=buildPlane1();
		TaxiCircuit line2=buildPlane2();
		line1=line1.init();
		line2=line2.init();

		TaxiConflict conflict=c.getConflict(line1,line2);
		String s=conflict.toString();
	}

	private  void testReverse(){
		ICal c=new Cal();
		TaxiCircuit line1=buildPlane1();
		TaxiCircuit line2=buildPlane3();
		line1=line1.init();
		line2=line2.init();
		TaxiConflict conflict=c.getConflict(line1,line2);
		String s=conflict.toString();
		logger.error(s);
	}

	private void testAcross(){
		ICal c=new Cal();
		TaxiCircuit line1=buildPlane4();
		TaxiCircuit line2=buildPlane5();
		line1=line1.init();
		line2=line2.init();
		TaxiConflict conflict=c.getConflict(line1,line2);
		String s=conflict.toString();
	}

	public static void main(String []params){
		AmonitorApplicationTests controller=new AmonitorApplicationTests();
		String xmlLine1=FileUtil.readToString("line1.xml");
		String xmlLine2=FileUtil.readToString("line2.xml");
		String xmlLine3=FileUtil.readToString("line3.xml");
		String xmlLine4=FileUtil.readToString("line4.xml");
		String xmlLine5=FileUtil.readToString("line5.xml");



		TaxiCircuit line1=(TaxiCircuit) XmlUtil.parseXml2Obj(xmlLine1,TaxiCircuit.class);// controller.buildPlane1();
		TaxiCircuit line2= (TaxiCircuit) XmlUtil.parseXml2Obj(xmlLine2,TaxiCircuit.class);//controller.buildPlane2();
		TaxiCircuit line3= (TaxiCircuit) XmlUtil.parseXml2Obj(xmlLine3,TaxiCircuit.class);//controller.buildPlane4();
		TaxiCircuit line4= (TaxiCircuit) XmlUtil.parseXml2Obj(xmlLine4,TaxiCircuit.class);//controller.buildPlane4();
		TaxiCircuit line5=(TaxiCircuit) XmlUtil.parseXml2Obj(xmlLine5,TaxiCircuit.class);// controller.buildPlane5();


//		XmlUtil.object2Xml(line1,"line1");
//		XmlUtil.object2Xml(line2,"line2");
//		XmlUtil.object2Xml(line3,"line3");
//		XmlUtil.object2Xml(line4,"line4");
//		XmlUtil.object2Xml(line5,"line5");



		//飞机f1和飞机f2在时刻15产生冲突，当时他们分别在Point{x=15.0, y=0.0, v=1.0}和Point{x=15.303300858899107, y=4.696699141100893, v=1.5}
		controller.testConflict();
		////飞机f4和飞机f5在时刻68产生冲突，当时他们分别在Point{x=48.08326112068523, y=48.08326112068523, v=1.0}和Point{x=48.08326112068523, y=51.91673887931477, v=1.0}
		controller.testAcross();
		//飞机f1和飞机f3在时刻11产生冲突，当时他们分别在Point{x=11.0, y=0.0, v=1.0}和Point{x=10.0, y=0.0, v=1.0}
		controller.testReverse();
	}

	/**
	 * f1  f3 完全对头反向的 ，必定碰撞。
	 * @return
	 */
	public TaxiCircuit buildPlane1(){
		TaxiCircuit line=new TaxiCircuit();
		line.setPlane("f1");
		List<TaxiPoint> list=new ArrayList<TaxiPoint>();
		list.add(new TaxiPoint(1,1,new Point(0,0),1));
		list.add(new TaxiPoint(2,2,new Point(10,0),1));
		list.add(new TaxiPoint(3,3,new Point(20,0),1));
		list.add(new TaxiPoint(4,4,new Point(20,10),1));
		list.add(new TaxiPoint(5,5,new Point(30,10),1));
		list.add(new TaxiPoint(6,6,new Point(30,20),1));
		list.add(new TaxiPoint(7,7,new Point(40,20),1));
		line.setRoute(list);
		return line;
	}

	public TaxiCircuit buildPlane2(){
		TaxiCircuit line=new TaxiCircuit();
		line.setPlane("f2");
		List<TaxiPoint> list=new ArrayList<TaxiPoint>();
		list.add(new TaxiPoint(11,1,new Point(0,10),1));
		list.add(new TaxiPoint(12,2,new Point(10,10),1.5));
		list.add(new TaxiPoint(13,3,new Point(20,0),1));
		list.add(new TaxiPoint(14,4,new Point(20,10),0.5));
		line.setRoute(list);
		return line;
	}


	public TaxiCircuit buildPlane3(){
		TaxiCircuit line=new TaxiCircuit();
		line.setPlane("f3");
		List<TaxiPoint> list=new ArrayList<TaxiPoint>();
		list.add(new TaxiPoint(27,1,new Point(40,20),1));
		list.add(new TaxiPoint(26,2,new Point(30,20),1));
		list.add(new TaxiPoint(25,3,new Point(30,10),1));
		list.add(new TaxiPoint(24,4,new Point(20,10),1));
		list.add(new TaxiPoint(23,5,new Point(20,0),1));
		list.add(new TaxiPoint(22,6,new Point(10,0),1));
		list.add(new TaxiPoint(21,7,new Point(0,0),1));
		line.setRoute(list);
		return line;
	}


	/**
	 * f4  f5  X 型在（50,50）交叉。属于斜线用例
	 * @return
	 */
	public TaxiCircuit buildPlane4(){
		TaxiCircuit line=new TaxiCircuit();
		line.setPlane("f4");
		List<TaxiPoint> list=new ArrayList<TaxiPoint>();
		list.add(new TaxiPoint(31,1,new Point(0,0),1));
		list.add(new TaxiPoint(32,2,new Point(50,50),1));
		list.add(new TaxiPoint(33,3,new Point(100,100),1));
		line.setRoute(list);
		return line;
	}

	public TaxiCircuit buildPlane5(){
		TaxiCircuit line=new TaxiCircuit();
		line.setPlane("f5");
		List<TaxiPoint> list=new ArrayList<TaxiPoint>();
		list.add(new TaxiPoint(31,1,new Point(0,100),1));
		list.add(new TaxiPoint(32,2,new Point(50,50),1));
		list.add(new TaxiPoint(33,3,new Point(100,0),1));
		line.setRoute(list);
		return line;
	}
}
