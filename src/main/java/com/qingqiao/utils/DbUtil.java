package com.qingqiao.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mysql.cj.protocol.Resultset;

import javax.sql.DataSource;
import javax.xml.transform.Result;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/*
工具类:
1.获取连接
2.关流
3.关流
 */
public class DbUtil {
    public static Connection conn = null;

    public static Connection getConnection() {
        try {
            // 读取properties文件
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/db.properties"));
            // 根据文件生成数据源
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            // 通过数据源 获取连接
            conn = dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void close(Connection conn, PreparedStatement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
