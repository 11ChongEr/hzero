package script.db

databaseChangeLog(logicalFilePath: 'script/db/hpfm_flex_rule.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2019-04-24-hpfm_flex_rule") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hpfm_flex_rule_s', startValue:"1")
        }
        createTable(tableName: "hpfm_flex_rule", remarks: "弹性域规则") {
            column(name: "rule_id", type: "bigint(20)", autoIncrement: true ,   remarks: "表ID，主键，供其他表做外键")  {constraints(primaryKey: true)} 
            column(name: "model_id", type: "bigint(20)",  remarks: "弹性域模型表hpfm_flex_model.model_id")  {constraints(nullable:"false")}  
            column(name: "rule_code", type: "varchar(" + 30 * weight + ")",  remarks: "弹性域规则编码")  {constraints(nullable:"false")}  
            column(name: "description", type: "varchar(" + 240 * weight + ")",  remarks: "弹性域规则描述")   
            column(name: "tenant_id", type: "bigint(20)",   defaultValue:"0",   remarks: "租户ID,hpfm_tenant.tenant_id")  {constraints(nullable:"false")}  
            column(name: "enabled_flag", type: "tinyint(1)",   defaultValue:"1",   remarks: "是否启用。1启用，0未启用")  {constraints(nullable:"false")}  
            column(name: "object_version_number", type: "bigint(20)",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}  
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "created_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_updated_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  

        }

        addUniqueConstraint(columnNames:"rule_code,tenant_id",tableName:"hpfm_flex_rule",constraintName: "hpfm_flex_rule_u1")
    }
}