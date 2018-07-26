package com.szatc.amonitor.util;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;


public class XmlUtil {

    private static final SAXReader reader = new SAXReader();
    private static Logger logger = Logger.getLogger(XmlUtil.class);

    /**
     * 移除XML 文档中的某个标签 ，保留此标签的子孙节点。
     * @param xml
     * @return
     */
    public static String deleteNode(String xml, String nodeName){
        if(xml==null)
            return xml;
        String leftLabel="<"+nodeName+">";
        String rightLabe="</"+nodeName+">";
        xml=xml.replace(leftLabel,"").replace(rightLabe,"");
        return xml;
    }


    public static void main(String [] args){
      try {
          String xml = FileUtil.readToString("G:\\ADD.xml");

//          DlXml entity = new DlXml();
//          entity = (DlXml) XmlUtil.parseXml2Obj(xml, DlXml.class);
//          DlHeader header = entity.getHeader();
//          xml = XmlUtil.deleteNode(xml, "DFLT");
//          Add add = (Add) parseXml2Obj(xml, Add.class);
//          logger.info(add.toString());
      }catch (Exception ex){
          logger.error(ex.toString());
      }
    }

    /**
     * Objext 转成 XML
     *
     * @param object
     */
    public static void object2Xml(Object object,String fileName) {
        FileWriter writer = null;
        try {
            JAXBContext context = JAXBContext.newInstance(object.getClass());
            Marshaller marshal = context.createMarshaller();
            marshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // 格式化输出
            marshal.setProperty(Marshaller.JAXB_ENCODING, "utf-8");// 编码格式,默认为utf-8
            marshal.setProperty(Marshaller.JAXB_FRAGMENT, false);// 是否省略xml头信息
            marshal.marshal(object, System.out);
            writer = new FileWriter(fileName);
            marshal.marshal(object, writer);

        } catch (Exception ex) {
            logger.error(ex.toString());
            ex.printStackTrace();
        }
    }
    /**
     * xml 转成特定的Bean
     *
     * @param xml
     * @return
     */
    public static Object parseXml2Obj(String xml,Class clazz) {
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            InputSource is = new InputSource();
            StringReader xmlStr = new StringReader(xml);
            is.setCharacterStream(xmlStr);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Object entity=clazz.newInstance();
            entity =  unmarshaller.unmarshal(is);
            return entity;

        } catch (Exception ex) {
            logger.error(ex.toString());
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 读取xml获取根节点
     *
     * @param msgXml
     * @return
     */
     public static  synchronized Element getRoot(String msgXml) throws DocumentException, UnsupportedEncodingException {
        try {
            //读取xml文档 处理 XML 里带汉字的情况
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(msgXml.getBytes("UTF-8"));
            Document document = reader.read(byteArrayInputStream);

            return document.getRootElement();
        } catch (DocumentException e) {
            throw e;
        }
    }
}
