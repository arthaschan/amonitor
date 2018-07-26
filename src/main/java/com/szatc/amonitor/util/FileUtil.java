package com.szatc.amonitor.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class FileUtil {

    public static   String readToString(String fileName){
        String encoding="UTF-8";
        try{
            File file=new File(fileName);
            Long fileLen=file.length();
            byte[] fileContent=new byte[fileLen.intValue()];

            FileInputStream in=new FileInputStream(file);
            in.read(fileContent);
            in.close();
            return new String(fileContent,encoding);

        }catch (Exception ex){

            return null;
        }

    }

    /**
     * 读取路径下的文件夹和文件名
     * @param path
     * @return
     */
    public static ArrayList<String> getFiles(String path) {
        ArrayList<String> files = new ArrayList<String>();
        File file = new File(path);
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
//              System.out.println("文     件：" + tempList[i]);
                files.add(tempList[i].toString());
            }
            if (tempList[i].isDirectory()) {
//              System.out.println("文件夹：" + tempList[i]);
            }
        }
        return files;
    }

    public static void main(String [] args){
        ArrayList<String> files = new ArrayList<String>();
        files=getFiles(".");

    }

    public static  ArrayList<String> getXmlFile(String path){
        ArrayList<String> files = new ArrayList<String>();
        files=getFiles(path);
        if(files==null||files.size()<=0)
            return files;
        Iterator<String > it=files.iterator();
        while(it.hasNext()){
           String s=it.next();
           if(!s.contains("xml")){
               it.remove();
           }
        }
        return files;
    }
}
