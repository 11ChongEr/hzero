package script.db

databaseChangeLog(logicalFilePath: 'script/db/hpfm_flex_model_field.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2019-04-24-hpfm_flex_model_field") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hpfm_flex_model_field_s', startValue:"1")
        }
        createTable(tableName: "hpfm_flex_model_field", remarks: "弹性域模型字段") {
            column(name: "model_field_id", type: "bigint(20)", autoIncrement: true ,   remarks: "表ID，主键，供其他表做外键")  {constraints(primaryKey: true)} 
            column(name: "model_id", type: "bigint(20)",  remarks: "弹性域模型表hpfm_flex_model.model_id")  {constraints(nullable:"false")}  
            column(name: "field_name", type: "varchar(" + 30 * weight + ")",  remarks: "弹性域模型字段名称")  {constraints(nullable:"false")}  
            column(name: "description", type: "varchar(" + 240 * weight + ")",  remarks: "弹性域模型字段描述")   
            column(name: "object_version_number", type: "bigint(20)",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}  
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "created_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_updated_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  

        }

        addUniqueConstraint(columnNames:"model_id,field_name",tableName:"hpfm_flex_model_field",constraintName: "hpfm_flex_model_field_u1")
    }
}