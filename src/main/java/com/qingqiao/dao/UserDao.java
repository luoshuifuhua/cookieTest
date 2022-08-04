package com.qingqiao.dao;

import com.qingqiao.entity.User;
import com.qingqiao.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author H_H
 * @date 2022/08/01 18:56
 **/
public class UserDao {
    //查询当前页 user
    public List<User> queryAll(Integer page) {
        ArrayList<User> list = new ArrayList<>();
        try {
            Connection conn = DbUtil.getConnection();
            //按页码查询user
            PreparedStatement ps = conn.prepareStatement("select * from user limit ?,2");
            //设置页码
            ps.setInt(1, (page - 1) * 2);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
            DbUtil.close(conn, ps, rs);
        } catch (Exception e) {
            e.printStackTrace();
            //outlook  hotmail
        }
        return list;
    }

    public User login(String username, String password) {
        System.out.println(username);
        System.out.println(password);
        User user = null;
        try {
            Connection conn = DbUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement("select * from user where username = ? and password = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new User(rs.getInt(1), rs.getString(2), rs.getNString(3));
            }
            DbUtil.close(conn, ps, rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public int tolPage() {
        ArrayList<User> list = new ArrayList<>();

        int i = 0;
        try {
            Connection conn = DbUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement("select * from user ");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
            //总个数
            i = list.size();
            DbUtil.close(conn, ps);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //总页数 判断奇偶 (Match round 向上取整ceil)
        return i % 2 == 0 ? i / 2 : i / 2 + 1;
    }
}
