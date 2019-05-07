package script.db

databaseChangeLog(logicalFilePath: 'script/db/hpfm_ui_field_attr.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2019-03-01-hpfm_ui_field_attr") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hpfm_ui_field_attr_s', startValue:"1")
        }
        createTable(tableName: "hpfm_ui_field_attr", remarks: "UI模板字段属性") {
            column(name: "field_attribute_id", type: "bigint(20)", autoIncrement: true ,   remarks: "表ID，主键，供其他表做外键")  {constraints(primaryKey: true)} 
            column(name: "field_id", type: "bigint(20)",  remarks: "UI字段ID")  {constraints(nullable:"false")}  
            column(name: "attribute_type", type: "varchar(" + 30 * weight + ")",  remarks: "属性类型 string|number|boolean")  {constraints(nullable:"false")}  
            column(name: "attribute_name", type: "varchar(" + 120 * weight + ")",  remarks: "属性名")  {constraints(nullable:"false")}  
            column(name: "attribute_value", type: "varchar(" + 240 * weight + ")",  remarks: "属性值")   
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "created_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_updated_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "object_version_number", type: "bigint(20)",   defaultValue:"1",   remarks: "")  {constraints(nullable:"false")}  

        }

        addUniqueConstraint(columnNames:"field_id,attribute_name",tableName:"hpfm_ui_field_attr",constraintName: "hpfm_ui_field_attr_u1")
    }
}