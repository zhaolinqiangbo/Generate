package com.ibi.gencode.model;


import com.ibi.gencode.util.tools.StringUtils;

import java.util.Date;

/**
 * 代码生成业务字段表 gen_table_column
 *
 * @author Lee
 */

public class GenTableColumn {


    private Long createUser;


    private Long updateUser;


    private Date createTime;


    private Date updateTime;


    private Long columnId;

    /**
     * 归属表编号
     */
    private Long tableId;

    /**
     * 列名称
     */
    private String columnName;


    private String columnComment;

    /**
     * 列类型
     */
    private String columnType;

    /**
     * JAVA类型
     */
    private String javaType;


    private String javaField;


    private String isPk;


    private String isIncrement;


    private String isRequired;


    private String isInsert;


    private String isEdit;


    private String isList;


    private String isQuery;

    /**
     * 查询方式（EQ等于、NE不等于、GT大于、LT小于、LIKE模糊、BETWEEN范围）
     */
    private String queryType;

    /**
     * 显示类型（input文本框、textarea文本域、select下拉框、checkbox复选框、radio单选框、datetime日期控件、image图片上传控件、upload文件上传控件、editor富文本控件）
     */
    private String htmlType;

    /**
     * 字典类型
     */
    private String dictType;

    /**
     * 排序
     */
    private Integer sort;


    public String getCapJavaField() {
        return StringUtils.capitalize(this.javaField);
    }

    public boolean isIncrement() {
        return this.isIncrement(this.isIncrement);
    }

    public boolean isIncrement(String isIncrement) {
        return isIncrement != null && StringUtils.equals("1", isIncrement);
    }

    public boolean isRequired() {
        return this.isRequired(this.isRequired);
    }

    public boolean isRequired(String isRequired) {
        return isRequired != null && StringUtils.equals("1", isRequired);
    }

    public boolean isInsert() {
        return this.isInsert(this.isInsert);
    }

    public boolean isInsert(String isInsert) {
        return isInsert != null && StringUtils.equals("1", isInsert);
    }

    public boolean isEdit() {
        return this.isInsert(this.isEdit);
    }

    public boolean isEdit(String isEdit) {
        return isEdit != null && StringUtils.equals("1", isEdit);
    }

    public boolean isList() {
        return this.isList(this.isList);
    }

    public boolean isList(String isList) {
        return isList != null && StringUtils.equals("1", isList);
    }

    public boolean isQuery() {
        return this.isQuery(this.isQuery);
    }

    public boolean isQuery(String isQuery) {
        return isQuery != null && StringUtils.equals("1", isQuery);
    }

    public boolean isSuperColumn() {
        return isSuperColumn(this.javaField);
    }

    public static boolean isSuperColumn(String javaField) {
        return StringUtils.equalsAnyIgnoreCase(javaField, new CharSequence[]{"createBy", "createTime", "updateBy", "updateTime", "createUser", "updateUser", "id", "parentName", "parentId","status"});
    }

    public boolean isUsableColumn() {
        return isUsableColumn(this.javaField);
    }

    public static boolean isUsableColumn(String javaField) {
        return StringUtils.equalsAnyIgnoreCase(javaField, new CharSequence[]{"parentId", "orderNum", "remark"});
    }

    public String readConverterExp() {
        String remarks = StringUtils.substringBetween(this.columnComment, "（", "）");
        StringBuffer sb = new StringBuffer();
        if (StringUtils.isNotEmpty(remarks)) {
            String[] var3 = remarks.split(" ");
            int var4 = var3.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String value = var3[var5];
                if (StringUtils.isNotEmpty(value)) {
                    Object startStr = value.subSequence(0, 1);
                    String endStr = value.substring(1);
                    sb.append("").append(startStr).append("=").append(endStr).append(",");
                }
            }

            return sb.deleteCharAt(sb.length() - 1).toString();
        } else {
            return this.columnComment;
        }
    }

    public GenTableColumn() {
    }

    public Long getCreateUser() {
        return this.createUser;
    }

    public Long getUpdateUser() {
        return this.updateUser;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public Long getColumnId() {
        return this.columnId;
    }

    public Long getTableId() {
        return this.tableId;
    }

    public String getColumnName() {
        return this.columnName;
    }

    public String getColumnComment() {
        return this.columnComment;
    }

    public String getColumnType() {
        return this.columnType;
    }

    public String getJavaType() {
        return this.javaType;
    }

    public String getJavaField() {
        return this.javaField;
    }

    public boolean isPk() {
        return this.isPk(this.isPk);
    }

    public boolean isPk(String isPk) {
        return isPk != null && StringUtils.equals("1", isPk);
    }
    public String getIsIncrement() {
        return this.isIncrement;
    }

    public String getIsRequired() {
        return this.isRequired;
    }

    public String getIsInsert() {
        return this.isInsert;
    }

    public String getIsEdit() {
        return this.isEdit;
    }

    public String getIsList() {
        return this.isList;
    }

    public String getIsQuery() {
        return this.isQuery;
    }

    public String getQueryType() {
        return this.queryType;
    }

    public String getHtmlType() {
        return this.htmlType;
    }

    public String getDictType() {
        return this.dictType;
    }

    public Integer getSort() {
        return this.sort;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setColumnId(Long columnId) {
        this.columnId = columnId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public void setJavaField(String javaField) {
        this.javaField = javaField;
    }


    public void setIsIncrement(String isIncrement) {
        this.isIncrement = isIncrement;
    }

    public void setIsRequired(String isRequired) {
        this.isRequired = isRequired;
    }

    public void setIsInsert(String isInsert) {
        this.isInsert = isInsert;
    }

    public void setIsEdit(String isEdit) {
        this.isEdit = isEdit;
    }

    public void setIsList(String isList) {
        this.isList = isList;
    }

    public void setIsQuery(String isQuery) {
        this.isQuery = isQuery;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public void setHtmlType(String htmlType) {
        this.htmlType = htmlType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }


    protected boolean canEqual(Object other) {
        return other instanceof GenTableColumn;
    }
}
