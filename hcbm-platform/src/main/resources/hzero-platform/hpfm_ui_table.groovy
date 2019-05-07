package script.db

databaseChangeLog(logicalFilePath: 'script/db/hpfm_ui_table.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2019-03-01-hpfm_ui_table") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hpfm_ui_table_s', startValue:"1")
        }
        createTable(tableName: "hpfm_ui_table", remarks: "UI用户个性化表格") {
            column(name: "custom_id", type: "bigint(20)", autoIncrement: true ,   remarks: "表ID，主键，供其他表做外键")  {constraints(primaryKey: true)} 
            column(name: "table_code", type: "varchar(" + 60 * weight + ")",  remarks: "表格编码")  {constraints(nullable:"false")}  
            column(name: "hidden", type: "tinyint(1)",   defaultValue:"0",   remarks: "是否隐藏")  {constraints(nullable:"false")}  
            column(name: "field_key", type: "varchar(" + 30 * weight + ")",  remarks: "key")  {constraints(nullable:"false")}  
            column(name: "fixed_left", type: "tinyint(1)",   defaultValue:"1",   remarks: "是否左固定")  {constraints(nullable:"false")}  
            column(name: "order_seq", type: "bigint(20)",  remarks: "排序")  {constraints(nullable:"false")}  
            column(name: "dimension_type", type: "varchar(" + 30 * weight + ")",  remarks: "维度类型 TENANT|USER")  {constraints(nullable:"false")}  
            column(name: "dimension_value", type: "bigint(20)",  remarks: "维度值")  {constraints(nullable:"false")}  
            column(name: "object_version_number", type: "bigint(20)",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}  
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "created_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_updated_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  

        }

        addUniqueConstraint(columnNames:"table_code,field_key,dimension_type,dimension_value",tableName:"hpfm_ui_table",constraintName: "hpfm_ui_table_u1")
    }
}