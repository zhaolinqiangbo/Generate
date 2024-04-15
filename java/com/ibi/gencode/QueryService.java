package com.ibi.gencode;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.Lists;
import com.ibi.gencode.model.GenTable;
import com.ibi.gencode.model.GenTableColumn;
import com.ibi.gencode.util.jdbc.BeanJDBCUtils;

import java.util.List;

/**
 * @description:
 * @author: zhangzhaoliang
 * @date: 2024/1/24 10:57
 */
public class QueryService {

    /**
     * 通过表名获得表列表信息
     * @param tableNameList
     * @return
     */
    public static List<GenTable> selectDbTableListByNames(List<String> tableNameList){
        List<String> newTableNameList = Lists.newArrayList();
        tableNameList.stream().forEach(tableName->{
            newTableNameList.add("'"+tableName+"'");
        });
        BeanJDBCUtils<GenTable> jdbcUtils = new BeanJDBCUtils();
        /**
         * 表信息sql
         */
        String tableSql = "select table_name as tableName, table_comment as tableComment, create_time as createTime, update_time as updateTime from information_schema.tables\n" +
                " where table_name NOT LIKE 'xxl_job_%' and table_name NOT LIKE 'gen_%' and table_schema = (select database())\n" +
                " and table_name in\n" +
                " ("+ String.join(",",newTableNameList)+")" +
                " order by create_time desc";
        return jdbcUtils.queryList(tableSql, GenTable.class,  null);
    }
    /**
     * 通过表名获得表信息
     * @param tableName
     * @return
     */
    public static GenTable selectDbTableByName(String tableName){
        List<GenTable> genTableList = selectDbTableListByNames(Lists.newArrayList(tableName));
        if(CollectionUtil.isEmpty(genTableList)) {
            return null;
        }
        return genTableList.get(0);
    }


    /**
     * 获得表的列信息
     * @param tableName
     * @return
     */
    public static List<GenTableColumn> selectDbTableColumnsByName(String tableName){
        BeanJDBCUtils<GenTableColumn> jdbcUtils = new BeanJDBCUtils();
        String columnSql = "select column_name as columnName,\n" +
                "               (case when (is_nullable = 'no' && column_key != 'PRI') then '1' else null end) as isRequired,\n" +
                "               (case when column_key = 'PRI' then '1' else '0' end) as isPk,\n" +
                "               ordinal_position as sort,\n" +
                "               column_comment as columnComment,\n" +
                "               (case when extra = 'auto_increment' then '1' else '0' end) as isIncrement,\n" +
                "               column_type as columnType\n" +
                "        from information_schema.columns where table_schema = (select database()) and table_name = ('"+tableName+"')\n" +
                "        order by ordinal_position";

        List<GenTableColumn> genTableColumnList = jdbcUtils.queryList(columnSql, GenTableColumn.class, null);
        return genTableColumnList;
    }
}
