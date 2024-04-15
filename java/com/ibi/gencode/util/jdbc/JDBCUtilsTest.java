package com.ibi.gencode.util.jdbc;

import com.google.common.collect.Lists;
import com.ibi.gencode.model.GenTable;
import com.ibi.gencode.model.GenTableColumn;

import java.util.List;


/**
 * @description:
 * @author: zhangzhaoliang
 * @date: 2024/1/23 9:12
 */
public class JDBCUtilsTest {

    @org.junit.Test
    public void test(){
        List<GenTable> genTableList = this.selectDbTableListByNames(Lists.newArrayList("lp_order","lp_order_address"));
        genTableList.stream().forEach(genTable -> {
            List<GenTableColumn> genTableColumnList = this.selectDbTableColumnsByName(genTable.getTableName());
            genTableColumnList.stream().forEach(genTableColumn -> {
                System.out.println(genTableColumn);
            });
        });
    }


    /**
     * 通过表明获得表信息
     * @param tableNameList
     * @return
     */
    public List<GenTable> selectDbTableListByNames( List<String> tableNameList){
        List<String> newTableNameList = Lists.newArrayList();
        tableNameList.stream().forEach(tableName->{
            newTableNameList.add("'"+tableName+"'");
        });
        BeanJDBCUtils<GenTable> jdbcUtils = new BeanJDBCUtils();
        String sql = "select table_name as tableName, table_comment as tableComment, create_time as createTime, update_time as updateTime from information_schema.tables\n" +
                "        where table_name NOT LIKE 'xxl_job_%' and table_name NOT LIKE 'gen_%' and table_schema = (select database())\n" +
                "        and table_name in\n" +
                "        ("+ String.join(",",newTableNameList) +")" +
                "        order by create_time desc";
        return jdbcUtils.queryList(sql, GenTable.class, null);
    }

    /**
     * 获得表的列信息
     * @param tableName
     * @return
     */
    private List<GenTableColumn> selectDbTableColumnsByName(String tableName){
        String sql= "select column_name as columnName,\n" +
                "               (case when (is_nullable = 'no' && column_key != 'PRI') then '1' else null end) as isRequired,\n" +
                "               (case when column_key = 'PRI' then '1' else '0' end) as isPk,\n" +
                "               ordinal_position as sort,\n" +
                "               column_comment as columnComment,\n" +
                "               (case when extra = 'auto_increment' then '1' else '0' end) as isIncrement,\n" +
                "               column_type as columnType\n" +
                "        from information_schema.columns where table_schema = (select database()) and table_name = ('"+tableName+"')\n" +
                "        order by ordinal_position";
        BeanJDBCUtils<GenTableColumn> jdbcUtils = new BeanJDBCUtils();
        List<GenTableColumn> genTableColumnList = jdbcUtils.queryList(sql, GenTableColumn.class, null);
        return genTableColumnList;
    }
}
