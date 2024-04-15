package com.ibi.gencode.util.jdbc;

import com.ibi.gencode.consts.GenConstants;
import com.ibi.gencode.model.GenCodeConfig;
import com.ibi.gencode.model.GenTable;
import com.ibi.gencode.model.GenTableColumn;
import com.ibi.gencode.util.tools.StringUtils;
import org.apache.commons.lang3.RegExUtils;

import java.util.Arrays;

/**
 * 代码生成器 工具类
 *
 * @author Lee
 */

public class GenUtils {


    /**
     * 初始化表
     * @param genCodeConfig
     * @param genTable
     */
    public static void initTable(GenCodeConfig genCodeConfig, GenTable genTable) {
        genTable.setClassName(convertClassName(genCodeConfig.getTableName()));
        genTable.setPackageName(genCodeConfig.getPackageName());
        genTable.setModuleName(getModuleName(genCodeConfig.getPackageName()));
        genTable.setBusinessName(getBusinessName(genCodeConfig.getTableName()));
        genTable.setFunctionName(replaceText(genTable.getTableComment()));
        genTable.setFunctionAuthor(genCodeConfig.getAuthor());
    }


    /**
     * 初始化表的字段
     * @param column
     * @param table
     */
    public static void initColumnField(GenCodeConfig genCodeConfig,GenTableColumn column, GenTable table) {
        String dataType = getDbType(column.getColumnType());
        String columnName = column.getColumnName();
        column.setTableId(table.getTableId());
        column.setJavaField(StringUtils.toCamelCase(columnName));
        column.setJavaType("String");
        column.setQueryType("EQ");
        if (!arraysContains(GenConstants.COLUMNTYPE_STR, dataType) && !arraysContains(GenConstants.COLUMNTYPE_TEXT, dataType)) {
            if (arraysContains(GenConstants.COLUMNTYPE_TIME, dataType)) {
                column.setJavaType("Date");
                column.setHtmlType("datetime");
            } else if (arraysContains(GenConstants.COLUMNTYPE_NUMBER, dataType)) {
                column.setHtmlType("input");
                /**
                 * mysql8.0不支持数据宽度的设置，mysql5.7支持
                 */
                if (arraysContains(GenConstants.COLUMNTYPE_NUMBER_BIGDECIMAL, dataType)) {
                    column.setJavaType("BigDecimal");
                } else if (arraysContains(GenConstants.COLUMNTYPE_NUMBER_INTEGER, dataType)) {
                    column.setJavaType("Integer");
                } else if (arraysContains(GenConstants.COLUMNTYPE_NUMBER_LONG, dataType)) {
                    column.setJavaType("Long");
                }
                // 以下代码不支持8.0
//                String[] str = StringUtils.split(StringUtils.substringBetween(column.getColumnType(), "(", ")"), ",");
//                if (str != null && str.length == 2 && Integer.parseInt(str[1]) > 0) {
//                    column.setJavaType("BigDecimal");
//                } else if (str != null && str.length == 1 && Integer.parseInt(str[0]) <= 10) {
//                    column.setJavaType("Integer");
//                } else {
//                    column.setJavaType("Long");
//                }
            }
        } else {
            Integer columnLength = getColumnLength(column.getColumnType());
            String htmlType = columnLength < 500 && !arraysContains(GenConstants.COLUMNTYPE_TEXT, dataType) ? "input" : "textarea";
            column.setHtmlType(htmlType);
        }

        if (!arraysContains(GenConstants.COLUMNNAME_NOT_ADD, columnName)) {
            column.setIsInsert("1");
        }

        if (!arraysContains(GenConstants.COLUMNNAME_NOT_EDIT, columnName)) {
            column.setIsEdit("1");
        }

        if (genCodeConfig.getUseBoValidate() && !arraysContains(GenConstants.COLUMNNAME_NOT_EDIT, columnName)) {
            column.setIsRequired("1");
        }

        if (!arraysContains(GenConstants.COLUMNNAME_NOT_LIST, columnName)) {
            column.setIsList("1");
        }

        if (!arraysContains(GenConstants.COLUMNNAME_NOT_QUERY, columnName)) {
            column.setIsQuery("1");
        }

        if (StringUtils.endsWithIgnoreCase(columnName, "name")) {
            column.setQueryType("LIKE");
        }

        if (StringUtils.endsWithIgnoreCase(columnName, "status")) {
            column.setHtmlType("radio");
        } else if (!StringUtils.endsWithIgnoreCase(columnName, "type") && !StringUtils.endsWithIgnoreCase(columnName, "sex")) {
            if (StringUtils.endsWithIgnoreCase(columnName, "image")) {
                column.setHtmlType("imageUpload");
            } else if (StringUtils.endsWithIgnoreCase(columnName, "file")) {
                column.setHtmlType("fileUpload");
            } else if (StringUtils.endsWithIgnoreCase(columnName, "content")) {
                column.setHtmlType("editor");
            }
        } else {
            column.setHtmlType("select");
        }

    }

    public static boolean arraysContains(String[] arr, String targetValue) {
        return Arrays.asList(arr).contains(targetValue);
    }

    public static String getModuleName(String packageName) {
        int lastIndex = packageName.lastIndexOf(".");
        int nameLength = packageName.length();
        return StringUtils.substring(packageName, lastIndex + 1, nameLength);
    }

    public static String getBusinessName(String tableName) {
//        int firstIndex = tableName.indexOf("_");
//        int nameLength = tableName.length();
//        String businessName = StringUtils.substring(tableName, firstIndex + 1, nameLength);
        return  StringUtils.toCamelCase(tableName);
    }

    public static String convertClassName(String tableName) {
        boolean autoRemovePre = true;
        String tablePrefix = "ibi";
        if (autoRemovePre && StringUtils.isNotEmpty(tablePrefix)) {
            String[] searchList = StringUtils.split(tablePrefix, ",");
            tableName = replaceFirst(tableName, searchList);
        }

        return StringUtils.convertToCamelCase(tableName);
    }

    public static String replaceFirst(String replacementm, String[] searchList) {
        String text = replacementm;
        String[] var3 = searchList;
        int var4 = searchList.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            String searchString = var3[var5];
            if (replacementm.startsWith(searchString)) {
                text = replacementm.replaceFirst(searchString, "");
                break;
            }
        }

        return text;
    }

    public static String replaceText(String text) {
        return RegExUtils.replaceAll(text, "(?:表|若依)", "");
    }

    public static String getDbType(String columnType) {
        return StringUtils.indexOf(columnType, 40) > 0 ? StringUtils.substringBefore(columnType, "(") : columnType;
    }

    public static Integer getColumnLength(String columnType) {
        if (StringUtils.indexOf(columnType, 40) > 0) {
            String length = StringUtils.substringBetween(columnType, "(", ")");
            return Integer.valueOf(length);
        } else {
            return 0;
        }
    }

    private GenUtils() {
    }
}
