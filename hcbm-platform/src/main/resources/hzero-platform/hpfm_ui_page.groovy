package script.db

databaseChangeLog(logicalFilePath: 'script/db/hpfm_ui_page.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2019-03-01-hpfm_ui_page") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hpfm_ui_page_s', startValue:"1")
        }
        createTable(tableName: "hpfm_ui_page", remarks: "UI页面配置") {
            column(name: "page_id", type: "bigint(20)", autoIncrement: true ,   remarks: "表ID，主键，供其他表做外键")  {constraints(primaryKey: true)} 
            column(name: "page_code", type: "varchar(" + 200 * weight + ")",  remarks: "页面编码")  {constraints(nullable:"false")}  
            column(name: "description", type: "varchar(" + 240 * weight + ")",  remarks: "页面描述")   
            column(name: "dimension_type", type: "varchar(" + 50 * weight + ")",  remarks: "维度类型 SITE|TENANT|COMPANY")  {constraints(nullable:"false")}  
            column(name: "dimension_value", type: "bigint(20)",  remarks: "维度值")  {constraints(nullable:"false")}  
            column(name: "tenant_id", type: "bigint(20)",   defaultValue:"0",   remarks: "租户ID,hpfm_tenant.tenant_id")  {constraints(nullable:"false")}  
            column(name: "enabled_flag", type: "tinyint(1)",   defaultValue:"1",   remarks: "是否启用。1启用，0未启用")  {constraints(nullable:"false")}  
            column(name: "object_version_number", type: "bigint(20)",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}  
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "created_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_updated_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  

        }

        addUniqueConstraint(columnNames:"page_code,dimension_type,dimension_value",tableName:"hpfm_ui_page",constraintName: "hpfm_ui_page_u1")
    }
}