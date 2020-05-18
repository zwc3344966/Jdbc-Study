package com.zwc.dao.Impl;

import com.mysql.cj.util.StringUtils;
import com.zwc.dao.UserDao;
import com.zwc.pojo.User;
import com.zwc.utils.DbBaseUtil;
import com.zwc.utils.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: zhangwch
 * @create: 2020-05-18 16:24
 **/
public class UserDaoImpl implements UserDao {

    public List<User> getUserList(User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Object> list = new ArrayList<Object>();

        try {
            StringBuilder sql = new StringBuilder("select * from userinfo where 1=1");
            if (0 != user.getId()) {
                sql.append(" and id = ?");
                list.add(user.getId());
            }

            if (null != user.getName() && !"".equals(user.getName())) {
                sql.append(" and name = ?");
                list.add(user.getName());
            }

            if (null != user.getPassword() && !"".equals(user.getPassword())) {
                sql.append(" and password = ?");
                list.add(user.getPassword());
            }

            System.out.println("sql = [" + sql.toString() + "]");
            System.out.println("paras = [" + Arrays.toString(list.toArray()) + "]");
            conn = DbBaseUtil.getConnection();
            rs = DbBaseUtil.executeQuery(conn, ps, rs, sql.toString(), list);

            // TODO:结果集指针问题是个坑，获取结果数时，需要先将指针移动到最后一条记录上（rs.last()）获取完后再将指针移动到第一条记录之前（first()是移动到第一条记录上，如果移动到第一条记录上，获取结果时会少一条记录；beforeFirst()是移动到第一条记录之前）
            rs.last();
            int count = rs.getRow();
            rs.beforeFirst();
            System.out.println("==> Parameters: \r\n<==    " + Util.getFieldType(user.getClass()));
            while (rs.next()) {
                System.out.print("<==        Row: " + rs.getInt("id"));
                System.out.print("  " + rs.getString("name"));
                System.out.println("  " + rs.getString("password"));
            }
            System.out.println("<==      Total: "+ count);

            DbBaseUtil.close(conn, ps, rs);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error.Message = [" + e.getMessage() + "]");
        }

        return null;
    }
}
