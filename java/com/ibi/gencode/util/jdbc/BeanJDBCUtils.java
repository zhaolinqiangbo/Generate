package com.ibi.gencode.util.jdbc;


import java.lang.reflect.Field;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: zhangzhaoliang
 * @date: 2024/1/22 18:28
 */
@SuppressWarnings("all")
public class BeanJDBCUtils<T> extends JDBCUtils {
    private Connection connection;
    private PreparedStatement preparedStatement;

    /**
     * @param sql
     * @param args
     * @return
     * @Description: 查找数据库中多行数据
     */
    public List<T> queryList(String sql,Class clazz, Object... args) {
        List<T> list = new ArrayList<>();
        connection = getConnection();
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            if(null!=args && args.length > 0){
                for (int i = 0; i < args.length; ++i){
                    preparedStatement.setObject(i + 1, args[i]);
                }
            }
            preparedStatement.execute();
            /*获取返回结果*/
            resultSet = preparedStatement.getResultSet();
            /*获取返回结果的数据*/
            ResultSetMetaData metaData = resultSet.getMetaData();
            /*获取数据的行数*/
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()) {
                /*初始化泛型*/
                T entity = (T) clazz.newInstance();
                Object object = new Object();
                for (int i = 0; i < columnCount; ++i) {
                    /*获取列名的别名，如果没有别名则直接获取列名*/
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    object = resultSet.getObject(i + 1);
                    /*使用反射给泛型变量赋值*/
                    Field field = entity.getClass().getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    if(object instanceof String){
                        field.set(entity, String.valueOf(object));
                    }
                    if(object instanceof LocalDateTime){
                        LocalDateTime localDateTimeValue= (LocalDateTime)object;
                        java.util.Date date = Date.from(localDateTimeValue.atZone( ZoneId.systemDefault()).toInstant());
                        field.set(entity,date);
                    }
                }
                list.add(entity);
            }

            return list;
        } catch (Exception e) {
           e.printStackTrace();
        } finally {
            close(connection, resultSet);
        }
        return null;
    }


    /**
     *
     * @param sql
     * @param clazz
     * @param args
     * @return
     * @Description: 查找数据库中单行数据
     */
    public T queryOne(String sql, Class clazz, Object... args) {
        connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; ++i)
                preparedStatement.setObject(i + 1, args[i]);
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            T t = (T) clazz.newInstance();
            if (resultSet.next()) {
                Object object = new Object();
                for (int i = 0; i < columnCount; i++) {
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    Field field = t.getClass().getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    object = resultSet.getObject(columnLabel);
                    if(object instanceof String){
                        field.set(t, String.valueOf(object));
                    }
                    if(object instanceof LocalDateTime){
                        LocalDateTime localDateTimeValue= (LocalDateTime)object;
                        java.util.Date date = Date.from(localDateTimeValue.atZone( ZoneId.systemDefault()).toInstant());
                        field.set(t,date);
                    }

                }
            }
            return t;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            close(connection, resultSet);
        }
    }

    /**
     * @param sql
     * @param args
     * @return 影响的行数
     * @Description: 增删改
     */
    public Integer updata(String sql, Object... args) {

        connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; ++i)
                preparedStatement.setObject(i + 1, args[i]);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(connection);
        }
    }

    /**
     * @param sql
     * @param args
     * @param <E>
     * @return
     * @Description: 查找特定的值，并返回单一基本类型
     */
    public <E> E getElement(String sql, Object... args) {
        connection = getConnection();
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            Object object = null;
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                object = resultSet.getObject(1);
            }
            return (E) object;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(connection, resultSet);
        }
    }
}
