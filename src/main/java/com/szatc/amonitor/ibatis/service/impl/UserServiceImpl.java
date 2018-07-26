package com.szatc.amonitor.ibatis.service.impl;

import com.szatc.amonitor.ibatis.entity.User;
import com.szatc.amonitor.ibatis.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author liuweijian
 * @date 2018/1/4
 */
@Service
public class UserServiceImpl   implements UserService {

    private static Logger logger = Logger.getLogger(UserServiceImpl.class);



    @Override
    public User getUser() {
        List<User> list=null;
        try {

        }
        catch (Exception ex){
            logger.error(ex.toString());
        }

        if(list==null||list.size()<=0)
            return new User();
        return list.get(0);
    }


    @Override
    public boolean addUser(User user) {
        boolean flag=false;
        try{
           // userMapper.addUser(user);
            flag=true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updateUser(User user) {
        boolean flag=false;
        try{
         //   userMapper.updateUser(user);
            flag=true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean deleteUser(int id) {
        boolean flag=false;
        try{
          //  userMapper.deleteUser(id);
            flag=true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public User findUserByName(String userName) {
        return null;
     //   return userMapper.findByName(userName);
    }

    @Override
    public User findUserById(int userId) {
        return null;
      //  return userMapper.findById(userId);
    }

    @Override
    public User findUserByAge(int userAge) {
        return null;
      //  return userMapper.findByAge(userAge);
    }
}
