package com.szatc.amonitor.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.szatc.amonitor.ibatis.entity.User;

//下面是FastJson的简介：常用的方法！
//  Fastjson API入口类是com.alibaba.fastjson.JSON，常用的序列化操作都可以在JSON类上的静态方法直接完成。
//  public static final Object parse(String text); // 把JSON文本parse为JSONObject或者JSONArray
//  public static final JSONObject parseObject(String text)； // 把JSON文本parse成JSONObject
//  public static final <T> T parseObject(String text, Class<T> clazz); // 把JSON文本parse为JavaBean
//  public static final JSONArray parseArray(String text); // 把JSON文本parse成JSONArray
//  public static final <T> List<T> parseArray(String text, Class<T> clazz); //把JSON文本parse成JavaBean集合
//  public static final String toJSONString(Object object); // 将JavaBean序列化为JSON文本
//  public static final String toJSONString(Object object, boolean prettyFormat); // 将JavaBean序列化为带格式的JSON文本
//  public static final Object toJSON(Object javaObject); 将JavaBean转换为JSONObject或者JSONArray（和上面方法的区别是返回值是不一样的）

/**
 * 这是关于FastJson的一个使用Demo，在Java环境下验证的；
 * 使用的时候可以参照这个例子进行回忆！
 * 备注：下面写的Json格式的数据"前面的\，意思是告诉编译器忽略掉它后面的"。
 * 后面定义有三个类：User、Group、Weibo
 * @author liuzhao
 */
public class JsonDemo {

    public static void main(String[] args) {

        json2JsonObjectM1();//将Json文本数据信息转换为JsonObject对象，然后利用键值对的方式获取信息
        json2BeanM2();//将Json文本数据转换为JavaBean数据！
        json2JSONArrayM3();//将json数据转化为JSONArray:
        json2JavaBeanM4();//将JSON文本转换为JavaBean的集合；转换为单纯的1个Object：parseObject(String text, Class<T> clazz);
        bean2JsonM5();//将JavaBean转换为Json格式的数据/Json文本 用户数据的传递：例如上传服务器
        bean2JSONObjectM6();//将JavaBean转换为JSON对象
        complexExampleM7();//一个相对复杂的例子：
        complex2JsonM8();//一个复杂的Object到Json的Demo
        complexMap2JsonM9();//将Map数据转换为JsonString的Demo；这个对RedBoy服务端不就直接破了嘛！
    }

    /**
     * 将Json文本数据信息转换为JsonObject对象，然后利用键值对的方式获取信息
     */
    private static void json2JsonObjectM1() {
        //一个JsonObject文本数据
        String s = "{\"name\":\"liuzhao\"}";
        //将JsonObject数据转换为Json
        JSONObject object = JSON.parseObject(s);
        //利用键值对的方式获取到值
        System.out.println(object.get("name"));
        /**
         * 打印：
         * liuzhao
         */
    }

    /**
     * 将Json文本数据转换为JavaBean数据！
     * 需要注意的是：这时候的Json文本信息中的键的名称必须和JavaBean中的字段名称一样！键中没有的在这个JavaBean中就显示为null！
     */
    private static void json2BeanM2() {
        String s = "{\"id\":\"0375\",\"city\":\"平顶山\"}";
        //一个简单方便 的方法将Json文本信息转换为JsonObject对象的同时转换为JavaBean对象！
//        Weibo weibo = JSON.parseObject(s, Weibo.class);//Weibo类在下边定义
//        System.out.println(weibo.getId());
//        System.out.println(weibo.getCity());
        //打印的结果     0375
        //              平顶山
    }


    /**
     * 将Map类型的数据转换为JsonString
     */
    private static void complexMap2JsonM9() {
//        Group group = new Group();
//        group.setId(1);
//        group.setName("group");
//
//        User user1 = new User();
//        user1.setId(2);
//        user1.setName("user1");
//
//        User user2 = new User();
//        user2.setId(3);
//        user2.setName("user2");
//        group.getList().add(user1);
//        group.getList().add(user2);

        Map<Integer, Object> map = new HashMap<Integer,Object>();
        map.put(1, "No.1");
        map.put(2, "No.2");
//        map.put(3, group.getList());

        String jsonString = JSON.toJSONString(map);
        System.out.println(jsonString);
        /**
         *  输出：{1:"No.1",2:"No.2",3:[{"id":2,"name":"user1"},{"id":3,"name":"user2"}]}
         */
    }

    /**
     * 由一个复杂的Object到Json的Demo
     */
    private static void complex2JsonM8() {
//        Group group = new Group();
//        group.setId(1);
//        group.setName("group");
//
//        User user1 = new User();
//        user1.setId(2);
//        user1.setName("user1");
//
//        User user2 = new User();
//        user2.setId(3);
//        user2.setName("user2");
//        group.getList().add(user1);
//        group.getList().add(user2);
//        String jsonString = JSON.toJSONString(group);
//        System.out.println(jsonString);
        /**
         *  输出：{"id":1,"list":[{"id":2,"name":"user1"},{"id":3,"name":"user2"}],"name":"group"}
         */
    }

    /**
     * 一个相对复杂的例子：
     * JSON文本数据：
     *      首先是JSONObject，转换为JSONArray；
     *      然后将JSONArray转换为JavaBean
     */
    private static void complexExampleM7() {
        String s = "{js:[{id:\"110000\",\"city\":\"北#001京市\"},{id:\"110000\",\"city\":\"北#002京市\"}"
                + ",{id:\"110000\",\"city\":\"北#002京市\"},{id:\"110000\",\"city\":\"北#002京市\"},"
                + "{id:\"110000\",\"city\":\"#006北#005京市\"},"
                + "{id:\"110000\",\"city\":\"北#002京市\"},"
                + "{id:\"110000\",\"city\":\"北#002京市\"},{id:\"120000\",\"city\":\"天#009津市\"}]}";

//        JSONObject object = JSON.parseObject(s);
//        Object jsonArray = object.get("js");
//        System.out.println(jsonArray);
//        List<Weibo> list = JSON.parseArray(jsonArray+"", Weibo.class);
//        for (Weibo weibo : list) {
//            System.out.println(weibo.getCity());
//        }
    }

    /**
     * 将JavaBean转换为JSON对象
     */
    private static void bean2JSONObjectM6() {
//        Weibo weibo = new Weibo("0373", "洛阳");
//        JSONObject json = (JSONObject) JSON.toJSON(weibo);
//        System.out.println(json.get("id"));
        /**
         * 打印：
         * 0373
         */
    }

    /**
     * 将JSON文本转换为JavaBean的集合；
     * 内部实现肯定是：首先转换为JSONArray，然后再转换为List集合
     */
    private static void json2JavaBeanM4() {
//        String s = "[{\"id\":\"0375\",\"city\":\"平顶山\"},{\"id\":\"0377\",\"city\":\"南阳\"}]";
//        List<Weibo> list = JSON.parseArray(s, Weibo.class);
//        for (Weibo weibo : list) {
//            System.out.println(weibo.getCity());
//        }
        /**
         * 打印：
         * 平顶山
         * 南阳
         */
    }

    /**
     * 将json数据转化为JSONArray:
     * 注意：获取到JSONArray之后（我们这里只是获取到JSONArray而不是JavaBean的List集合）
     * 获取JSONArray中的数据转换为String类型需要在外边加"";
     */
    private static void json2JSONArrayM3() {
        String s = "[{\"id\":\"0375\",\"city\":\"平顶山\"},{\"id\":\"0377\",\"city\":\"南阳\"}]";
        //将JSON文本转换为JSONArray
        JSONArray array = JSON.parseArray(s);
        //这行必须写：必须加上+"";不然会报出类型强转异常！
        String str = array.get(1)+"";
        System.out.println(array.get(0));
        JSONObject object = JSON.parseObject(str);
        System.out.println(object.get("id"));
        /**
         * {"city":"平顶山","id":"0375"}
         0377
         */
    }


    /**
     * 将JavaBean转换为Json格式的数据/Json文本
     * 用户数据的传递：例如上传服务器
     */
    private static void bean2JsonM5() {
//        Weibo weibo = new Weibo("123456", "上海");
//        String string = JSON.toJSONString(weibo);
//        System.out.println(string);
        /**
         * 打印：
         * {"city":"上海","id":"123456"}
         */
    }

}