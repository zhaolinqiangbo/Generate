package com.ibi.gencode;

import com.google.common.collect.Lists;
import com.ibi.gencode.model.GenCodeConfig;

import java.util.List;

/**
 * @description: 通过main方法来生成代码
 * @author: zhangzhaoliang
 * @date: ${DATE} ${TIME}
 */
public class Main {
    public static void main(String[] args) {
        List<GenCodeConfig> tableConfigList = Lists.newArrayList();

        GenCodeConfig genCodeConfig1 = new GenCodeConfig();
        // 表名
        genCodeConfig1.setTableName("expand_dispatch_order_driver");
        // 包名
        genCodeConfig1.setPackageName("com.ibi.queueup");
        // 作者
        genCodeConfig1.setAuthor("zlq");
        // 是否使用controller方法日志
        //genCodeConfig1.setUseMethodLog(Boolean.FALSE);

        tableConfigList.add(genCodeConfig1);
        GenerateService.generateCode("F:\\Desktop\\download","F:/",tableConfigList);

        System.out.println("生成成功!!!!!!!!");
    }
}
