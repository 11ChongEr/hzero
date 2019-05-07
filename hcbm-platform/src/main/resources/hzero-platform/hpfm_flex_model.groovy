package script.db

databaseChangeLog(logicalFilePath: 'script/db/hpfm_flex_model.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2019-04-24-hpfm_flex_model") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hpfm_flex_model_s', startValue:"1")
        }
        createTable(tableName: "hpfm_flex_model", remarks: "弹性域模型") {
            column(name: "model_id", type: "bigint(20)", autoIncrement: true ,   remarks: "表ID，主键，供其他表做外键")  {constraints(primaryKey: true)} 
            column(name: "service_name", type: "varchar(" + 30 * weight + ")",  remarks: "服务名称")  {constraints(nullable:"false")}  
            column(name: "model_code", type: "varchar(" + 30 * weight + ")",  remarks: "模型编码")  {constraints(nullable:"false")}  
            column(name: "model_name", type: "varchar(" + 30 * weight + ")",  remarks: "模型名称")  {constraints(nullable:"false")}  
            column(name: "model_table", type: "varchar(" + 30 * weight + ")",  remarks: "模型表")  {constraints(nullable:"false")}  
            column(name: "tenant_id", type: "bigint(20)",   defaultValue:"0",   remarks: "租户ID,hpfm_tenant.tenant_id")  {constraints(nullable:"false")}  
            column(name: "enabled_flag", type: "tinyint(1)",   defaultValue:"1",   remarks: "是否启用。1启用，0未启用")  {constraints(nullable:"false")}  
            column(name: "object_version_number", type: "bigint(20)",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}  
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "created_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_updated_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  

        }

        addUniqueConstraint(columnNames:"model_code,tenant_id",tableName:"hpfm_flex_model",constraintName: "hpfm_flex_model_u1")
    }
}