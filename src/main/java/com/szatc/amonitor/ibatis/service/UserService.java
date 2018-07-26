package com.szatc.amonitor.ibatis.service;

import com.szatc.amonitor.ibatis.entity.User;

/**
 * ACDM报文数据访问对象
 *
 * @author liuweijian
 * @date 2018/1/4
 */
public interface UserService    {

    User getUser() ;


    /**
     * 新增用户
     * @param user
     * @return
     */
    boolean addUser(User user);

    /**
     * 修改用户
     * @param user
     * @return
     */
    boolean updateUser(User user);


    /**
     * 删除用户
     * @param id
     * @return
     */
    boolean deleteUser(int id);

    /**
     * 根据用户名字查询用户信息
     * @param userName
     */
    User findUserByName(String userName);

    /**
     * 根据用户ID查询用户信息
     * @param userId
     */
    User findUserById(int userId);

    /**
     * 根据用户ID查询用户信息
     * @param userAge
     */
    User findUserByAge(int userAge);

}
