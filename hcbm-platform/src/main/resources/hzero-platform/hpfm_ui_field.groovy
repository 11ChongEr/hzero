package script.db

databaseChangeLog(logicalFilePath: 'script/db/hpfm_ui_field.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2019-03-01-hpfm_ui_field") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hpfm_ui_field_s', startValue:"1")
        }
        createTable(tableName: "hpfm_ui_field", remarks: "UI模板字段") {
            column(name: "field_id", type: "bigint(20)", autoIncrement: true ,   remarks: "表ID，主键，供其他表做外键")  {constraints(primaryKey: true)} 
            column(name: "template_id", type: "bigint(20)",  remarks: "模板ID")  {constraints(nullable:"false")}  
            column(name: "component_type", type: "varchar(" + 30 * weight + ")",  remarks: "字段编辑组件 值集 HPFM.UI_COMPONENT_TYPE")   
            column(name: "field_name", type: "varchar(" + 255 * weight + ")",  remarks: "字段名")  {constraints(nullable:"false")}  
            column(name: "field_label", type: "varchar(" + 255 * weight + ")",  remarks: "字段Labe")   
            column(name: "required_flag", type: "tinyint(1)",   defaultValue:"0",   remarks: "是否必输。1必输，0不必输")  {constraints(nullable:"false")}  
            column(name: "enabled_flag", type: "tinyint(1)",   defaultValue:"1",   remarks: "是否启用。1启用，0未启用")  {constraints(nullable:"false")}  
            column(name: "left_offset", type: "int(11)",  remarks: "左空位")   
            column(name: "right_offset", type: "int(11)",  remarks: "右空位")   
            column(name: "colspan", type: "int(11)",  remarks: "跨越列数")   
            column(name: "align", type: "varchar(" + 30 * weight + ")",  remarks: "对齐方式")   
            column(name: "width", type: "varchar(" + 30 * weight + ")",  remarks: "宽度")   
            column(name: "order_seq", type: "int(11)",   defaultValue:"1",   remarks: "排序号")  {constraints(nullable:"false")}  
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "created_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_updated_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "object_version_number", type: "bigint(20)",   defaultValue:"1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "visiable_flag", type: "tinyint(1)",   defaultValue:"1",   remarks: "是否可见")   

        }

        addUniqueConstraint(columnNames:"template_id,field_name",tableName:"hpfm_ui_field",constraintName: "hpfm_ui_field_u1")
    }
}