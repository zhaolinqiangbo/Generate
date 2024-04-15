package com.ibi.gencode;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import com.ibi.gencode.model.GenCodeConfig;
import com.ibi.gencode.model.GenTable;
import com.ibi.gencode.model.GenTableColumn;
import com.ibi.gencode.util.VelocityInitializer;
import com.ibi.gencode.util.VelocityUtils;
import com.ibi.gencode.util.jdbc.GenUtils;
import com.ibi.gencode.util.tools.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.File;
import java.io.StringWriter;
import java.util.List;

/**
 * @author zlq
 * @DateTime 2023/4/21 14:10
 */
@Slf4j
public class GenerateService {


    public static void generateCode(String javaCodePath,String vueCodePath, List<GenCodeConfig> tableConfigList) {
        for (GenCodeConfig genCodeConfig : tableConfigList) {
            generatorCode(javaCodePath,vueCodePath,genCodeConfig);
        }
    }



    public static void generatorCode(String javaCodePath,String vueCodePath, GenCodeConfig genCodeConfig){
        // 查询表信息
        GenTable table = QueryService.selectDbTableByName(genCodeConfig.getTableName());
        if(null == table) {
            return;
        }
        // 初始化表信息
        GenUtils.initTable(genCodeConfig,table);

        // 查询列信息
        List<GenTableColumn> genTableColumnList = QueryService.selectDbTableColumnsByName(genCodeConfig.getTableName());
        if(CollectionUtil.isEmpty(genTableColumnList)) {
            return;
        }
        genTableColumnList.stream().forEach(genTableColumn -> {
            GenUtils.initColumnField(genCodeConfig, genTableColumn,table);
        });
        table.setColumns(genTableColumnList);

        // 设置主键列信息
        VelocityUtils.setPkColumn(table);

        VelocityInitializer.initVelocity();

        VelocityContext context = VelocityUtils.prepareContext(table, genCodeConfig);

        // 获取模板列表
        List<String> templates = VelocityUtils.getTemplateList(genCodeConfig);
        for (String template : templates) {
            // 渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, "UTF-8");
            tpl.merge(context, sw);
            try {
                if(StringUtils.contains(template,"vue") || StringUtils.contains(template,"js")) {
                    FileUtil.writeUtf8String(sw.toString(), getGenPath(vueCodePath,table, template));
                } else {
                    FileUtil.writeUtf8String(sw.toString(), getGenPath(javaCodePath,table, template));
                }
            } catch (Exception e) {
                log.error("渲染模板失败，表名：" + table.getTableName(), e);
            }
        }
    }

    /**
     * 获取代码生成地址
     *
     * @param table    业务表信息
     * @param templateName 模板文件路径
     * @return 生成地址
     */
    public static String getGenPath(String genPath,GenTable table, String templateName) {
        if (StringUtils.equals(genPath, "/")) {
            return System.getProperty("user.dir") + File.separator + "src" + File.separator + VelocityUtils.getFileName(templateName, table);
        }
        return genPath + File.separator + VelocityUtils.getFileName(templateName, table);
    }


}
