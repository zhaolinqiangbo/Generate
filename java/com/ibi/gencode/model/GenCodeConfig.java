package com.ibi.gencode.model;

/**
 * @description: 代码生成前的配置信息
 * @author: zhangzhaoliang
 * @date: 2024/1/24 14:37
 */
public class GenCodeConfig {

    /**
     * 是否生成entity
     */
    private Boolean isGenerateEntity = Boolean.TRUE;

    /**
     * 是否生成Bo
     */
    private Boolean isGenerateBo = Boolean.TRUE;

    /**
     * 是否生成Vo
     */
    private Boolean isGenerateVo = Boolean.TRUE;

    /**
     * 是否生成controller
     */
    private Boolean isGenerateController  = Boolean.TRUE;


    /**
     * 是否生成service
     */
    private Boolean isGenerateService = Boolean.TRUE;


    /**
     * 是否生成mapper
     */
    private Boolean isGenerateMapper = Boolean.TRUE;

    /**
     * 是否生成mapper的xml
     */
    private Boolean isGenerateMapperXml = Boolean.TRUE;


    /**
     * 是否生成前端API JS
     */
    private Boolean isGenerateFrontApiJs = Boolean.TRUE;


    /**
     * 是否生成前端index页面
     */
    private Boolean isGenerateFrontIndex = Boolean.TRUE;


    /**
     * 是否生成前端新增和编辑页面
     */
    private Boolean isGenerateFrontAddOrEdit = Boolean.TRUE;

    /**
     * 是否生成sql
     */
    private Boolean isGenerateSql = Boolean.FALSE;


    /**
     * 是否使用controller的log日志
     */
    private Boolean isUseMethodLog = Boolean.FALSE;

    /**
     * 是否使用Bo的校验规则
     */
    private Boolean isUseBoValidate = Boolean.TRUE;

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 包名
     */
    private String packageName;


    /**
     * 生成作者
     */
    private String author;


    public Boolean getGenerateController() {
        return isGenerateController;
    }

    public void setGenerateController(Boolean generateController) {
        isGenerateController = generateController;
    }

    public Boolean getGenerateMapperXml() {
        return isGenerateMapperXml;
    }

    public void setGenerateMapperXml(Boolean generateMapperXml) {
        isGenerateMapperXml = generateMapperXml;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Boolean getUseMethodLog() {
        return isUseMethodLog;
    }

    public void setUseMethodLog(Boolean useMethodLog) {
        isUseMethodLog = useMethodLog;
    }

    public Boolean getUseBoValidate() {
        return isUseBoValidate;
    }

    public void setUseBoValidate(Boolean useBoValidate) {
        isUseBoValidate = useBoValidate;
    }


    public Boolean getGenerateEntity() {
        return isGenerateEntity;
    }

    public void setGenerateEntity(Boolean generateEntity) {
        isGenerateEntity = generateEntity;
    }

    public Boolean getGenerateBo() {
        return isGenerateBo;
    }

    public void setGenerateBo(Boolean generateBo) {
        isGenerateBo = generateBo;
    }

    public Boolean getGenerateVo() {
        return isGenerateVo;
    }

    public void setGenerateVo(Boolean generateVo) {
        isGenerateVo = generateVo;
    }

    public Boolean getGenerateService() {
        return isGenerateService;
    }

    public void setGenerateService(Boolean generateService) {
        isGenerateService = generateService;
    }

    public Boolean getGenerateMapper() {
        return isGenerateMapper;
    }

    public void setGenerateMapper(Boolean generateMapper) {
        isGenerateMapper = generateMapper;
    }

    public Boolean getGenerateFrontApiJs() {
        return isGenerateFrontApiJs;
    }

    public void setGenerateFrontApiJs(Boolean generateFrontApiJs) {
        isGenerateFrontApiJs = generateFrontApiJs;
    }

    public Boolean getGenerateFrontIndex() {
        return isGenerateFrontIndex;
    }

    public void setGenerateFrontIndex(Boolean generateFrontIndex) {
        isGenerateFrontIndex = generateFrontIndex;
    }

    public Boolean getGenerateFrontAddOrEdit() {
        return isGenerateFrontAddOrEdit;
    }

    public void setGenerateFrontAddOrEdit(Boolean generateFrontAddOrEdit) {
        isGenerateFrontAddOrEdit = generateFrontAddOrEdit;
    }

    public Boolean getGenerateSql() {
        return isGenerateSql;
    }

    public void setGenerateSql(Boolean generateSql) {
        isGenerateSql = generateSql;
    }
}
