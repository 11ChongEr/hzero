package script.db

databaseChangeLog(logicalFilePath: 'script/db/hpfm_flex_detail_field.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2019-04-24-hpfm_flex_detail_field") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hpfm_flex_detail_field_s', startValue:"1")
        }
        createTable(tableName: "hpfm_flex_detail_field", remarks: "弹性域规则明细字段") {
            column(name: "detail_field_id", type: "bigint(20)", autoIncrement: true ,   remarks: "表ID，主键，供其他表做外键")  {constraints(primaryKey: true)} 
            column(name: "rule_detail_id", type: "bigint(20)",  remarks: "弹性域规则明细hpfm_flex_detail_field.rule_detail_id")  {constraints(nullable:"false")}  
            column(name: "field_name", type: "varchar(" + 30 * weight + ")",  remarks: "规则字段名称")  {constraints(nullable:"false")}  
            column(name: "field_type", type: "varchar(" + 30 * weight + ")",  remarks: "规则字段类型，值集：HPFM.FLEX.FIELD_TYPE")  {constraints(nullable:"false")}  
            column(name: "field_value", type: "varchar(" + 30 * weight + ")",  remarks: "规则字段值")  {constraints(nullable:"false")}  
            column(name: "object_version_number", type: "bigint(20)",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}  
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "created_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_updated_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  

        }

        addUniqueConstraint(columnNames:"rule_detail_id,field_name",tableName:"hpfm_flex_detail_field",constraintName: "hpfm_flex_detail_field_u1")
    }
}