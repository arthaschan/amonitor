package com.szatc.amonitor.controller;

import com.alibaba.fastjson.JSON;
import com.szatc.amonitor.calculate.ICal;
import com.szatc.amonitor.ibatis.entity.TaxiCircuit;
import com.szatc.amonitor.ibatis.entity.TaxiConflict;
import com.szatc.amonitor.util.FileUtil;
import com.szatc.amonitor.util.XmlUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 机场控制器
 * 实现如下功能：
 * 1：接受界面的关于飞机轨迹路线数据的输入
 * 2：输出2个飞机在未来几秒内会有冲突
 */
@Controller
@RequestMapping(value = "/airport")
public class AirportController {

    @Value("${sys.minDistance}")
    public  float minDistance;

    @Autowired
    private ICal cal;

    private static Logger logger = Logger.getLogger(AirportController.class);
    /**
     *
     * @param route json  数据
     * @return
     */
    @ResponseBody
   // @RequestMapping(value="/inputRoute", method = RequestMethod.POST)
    @RequestMapping(value="/inputRoute", method = RequestMethod.GET)
    public String inputRoute(String route){
        logger.error(route);
        ArrayList<TaxiCircuit> taxiCircuits=new ArrayList<>();
        ArrayList<String> files= FileUtil.getXmlFile("./xml");
        if(files==null||files.size()<=0)
            return "";
        for(String file :files){
          String s=   FileUtil.readToString(file);
          Object o=XmlUtil.parseXml2Obj(s,TaxiCircuit.class);
          if(o==null){
              logger.error("XML 映射为对象不成功。"+s);
          }
          taxiCircuits.add((TaxiCircuit )o);
        }
      StringBuffer sb=new StringBuffer();
        //TODO:检查 taxiCircuits 里的2个飞机的冲突。
        // 要先对每个飞机做初始化。

        try {
            for (int i = 0; i < taxiCircuits.size(); i++) {
                TaxiCircuit tI = taxiCircuits.get(i);
                if (!tI.isInit())
                    tI.init();
                for (int j = i + 1; j < taxiCircuits.size(); j++) {
                    TaxiCircuit tJ = taxiCircuits.get(j);
                    if (!tJ.isInit())
                        tJ.init();
                    TaxiConflict conflict = cal.getConflict(tI, tJ);
                    if (conflict == null)
                        continue;
                    sb.append(conflict.toString() + "<p>");
                }
            }
        }catch (Exception ex){
            logger.error(ex.toString());
        }
        String s=sb.toString();
        return s;
    }

    @ResponseBody
    @RequestMapping(value="/getConflict", method = RequestMethod.GET)
    public String getConfict(){

        return "";
    }


}
