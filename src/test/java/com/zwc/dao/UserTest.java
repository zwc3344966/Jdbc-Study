package com.zwc.dao;

import com.zwc.dao.Impl.UserDaoImpl;
import com.zwc.pojo.User;
import com.zwc.utils.DbBaseUtil;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author: zhangwch
 * @create: 2020-05-18 16:44
 **/
public class UserTest {

    @Test
    public void test() {

        System.out.println("==============start==================");

        UserDao userDao = new UserDaoImpl();
        User user = new User();
//        user.setId(1);
//        user.setName("zwc");
        userDao.getUserList(user);

        System.out.println("==============start==================");
    }
}
