package com.ibi.gencode.consts;

/**
 * @description:
 * @author: zhangzhaoliang
 * @date: 2024/1/24 10:14
 */
public interface GenConstants {

    String TPL_CRUD = "crud";
    String TPL_TREE = "tree";
    String TPL_SUB = "sub";
    String TREE_CODE = "treeCode";
    String TREE_PARENT_CODE = "treeParentCode";
    String TREE_NAME = "treeName";
    String PARENT_MENU_ID = "parentMenuId";
    String PARENT_MENU_NAME = "parentMenuName";
    String[] COLUMNTYPE_STR = new String[]{"char", "varchar", "nvarchar", "varchar2"};
    String[] COLUMNTYPE_TEXT = new String[]{"tinytext", "text", "mediumtext", "longtext"};
    String[] COLUMNTYPE_TIME = new String[]{"datetime", "time", "date", "timestamp"};
    String[] COLUMNTYPE_NUMBER = new String[]{"tinyint", "smallint", "mediumint", "int", "number", "integer", "bit", "bigint", "float", "double", "decimal"};
    String[] COLUMNTYPE_NUMBER_INTEGER = new String[]{"tinyint", "smallint", "int", "integer", "bit"};
    String[] COLUMNTYPE_NUMBER_BIGDECIMAL = new String[]{"float", "double", "decimal"};
    String[] COLUMNTYPE_NUMBER_LONG = new String[]{"bigint"};
    String[] COLUMNNAME_NOT_ADD = new String[]{"id", "create_by", "create_time", "del_flag", "update_by", "update_time", "version", "create_user", "update_user", "status"};
    String[] COLUMNNAME_NOT_EDIT = new String[]{"id", "create_by", "create_time", "del_flag", "update_by", "update_time", "version", "create_user", "update_user", "status"};
    String[] COLUMNNAME_NOT_LIST = new String[]{"id", "create_by", "create_time", "del_flag", "update_by", "update_time", "version", "create_user", "update_user", "status"};
    String[] COLUMNNAME_NOT_QUERY = new String[]{"create_by", "create_time", "del_flag", "update_by", "update_time", "remark", "version", "create_user", "update_user", "status"};
    String[] BASE_ENTITY = new String[]{"id", "createBy", "createTime", "updateBy", "updateTime", "createUser", "updateUser", "status"};
    String[] TREE_ENTITY = new String[]{"parentName", "parentId", "children"};
    String HTML_INPUT = "input";
    String HTML_TEXTAREA = "textarea";
    String HTML_SELECT = "select";
    String HTML_RADIO = "radio";
    String HTML_CHECKBOX = "checkbox";
    String HTML_DATETIME = "datetime";
    String HTML_IMAGE_UPLOAD = "imageUpload";
    String HTML_FILE_UPLOAD = "fileUpload";
    String HTML_EDITOR = "editor";
    String TYPE_STRING = "String";
    String TYPE_INTEGER = "Integer";
    String TYPE_LONG = "Long";
    String TYPE_DOUBLE = "Double";
    String TYPE_BIGDECIMAL = "BigDecimal";
    String TYPE_DATE = "Date";
    String QUERY_LIKE = "LIKE";
    String QUERY_EQ = "EQ";
    String REQUIRE = "1";
    String CACHE_GEN_USER_ONLINE = "cache:gen:userOnline:";
    String CACHE_GEN_USER_GROUP = "cache:gen:userGroup";
}
