package com.ibi.gencode.util.jdbc;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库连接测试工具
 * @author DELL
 */

@Slf4j
public class DatabaseConnectionUtil {

    public static boolean ConnectionTest(String dbUrl, String userName, String password) throws SQLException{

        // 尝试连接数据库
        try (Connection connection = DriverManager.getConnection(dbUrl, userName, password)) {
            // 如果连接成功，打印一条消息
            log.info("成功连接到数据库！");
        } catch (SQLException e) {
            // 如果连接失败，打印错误信息
            log.error("无法连接到数据库： " + e.getMessage());
            throw e;
        }

        return true;
    }

}
