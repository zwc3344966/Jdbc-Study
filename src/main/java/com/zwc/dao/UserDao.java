package com.zwc.dao;

import com.zwc.pojo.User;

import java.util.List;

/**
 * @author: zhangwch
 * @create: 2020-05-18 16:22
 **/
public interface UserDao {

    List<User> getUserList(User user);
}
