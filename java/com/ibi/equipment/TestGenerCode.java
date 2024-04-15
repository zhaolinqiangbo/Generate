package com.ibi.equipment;

import com.ibi.gencode.util.tools.StringUtils;

/**
 * @description:
 * @author: zhangzhaoliang
 * @date: 2024/4/3 15:29
 */
public class TestGenerCode {

    public static String getBusinessName(String tableName) {
//        int firstIndex = tableName.indexOf("_");
//        int nameLength = tableName.length();
//        String businessName = StringUtils.substring(tableName, firstIndex + 1, nameLength);
        String businessName = StringUtils.toCamelCase(tableName);
        return businessName;
    }

    public static void main(String[] args) {
        String upkeepInfo = TestGenerCode.getBusinessName("expand_dispatch_order_driver");
        System.out.println(upkeepInfo);
    }
}
