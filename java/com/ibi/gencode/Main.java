package com.ibi.gencode;

import com.google.common.collect.Lists;
import com.ibi.gencode.model.GenCodeConfig;
import com.ibi.gencode.util.jdbc.JDBCUtils;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * @description: 通过main方法来生成代码
 * @author: zhangzhaoliang
 * @date: ${DATE} ${TIME}
 */
public class Main {
    public static void main(String[] args) throws IOException {
        List<GenCodeConfig> tableConfigList = Lists.newArrayList();

        GenCodeConfig genCodeConfig1 = new GenCodeConfig();
        // 表名
        genCodeConfig1.setTableName("invitation_record");
        // 包名
        genCodeConfig1.setPackageName("com.ibi.center");
        // 作者
        genCodeConfig1.setAuthor("zlq");
        // 是否使用controller方法日志
        //genCodeConfig1.setUseMethodLog(Boolean.FALSE);

        tableConfigList.add(genCodeConfig1);
        GenerateService.generateCode("G:\\newCode","G:\\newCode",tableConfigList);

        System.out.println("生成成功!!!!!!!!，路径：G:\\newCode");
    }
}
