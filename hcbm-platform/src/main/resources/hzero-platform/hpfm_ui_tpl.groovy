package script.db

databaseChangeLog(logicalFilePath: 'script/db/hpfm_ui_tpl.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2019-03-01-hpfm_ui_tpl") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hpfm_ui_tpl_s', startValue:"1")
        }
        createTable(tableName: "hpfm_ui_tpl", remarks: "UI模板") {
            column(name: "template_id", type: "bigint(20)", autoIncrement: true ,   remarks: "表ID，主键，供其他表做外键")  {constraints(primaryKey: true)} 
            column(name: "template_code", type: "varchar(" + 255 * weight + ")",  remarks: "UI模板代码")  {constraints(nullable:"false")}  
            column(name: "page_id", type: "bigint(20)",  remarks: "页面ID  HPFM_UI_PAGE.PAGE_ID")  {constraints(nullable:"false")}  
            column(name: "order_seq", type: "int(11)",   defaultValue:"1",   remarks: "排序号")  {constraints(nullable:"false")}  
            column(name: "template_type", type: "varchar(" + 30 * weight + ")",  remarks: "UI模板类型 FORM|GRID|TOOLBAR")  {constraints(nullable:"false")}  
            column(name: "description", type: "varchar(" + 255 * weight + ")",  remarks: "UI模板描述")   
            column(name: "object_version_number", type: "bigint(20)",   defaultValue:"1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "enabled_flag", type: "tinyint(1)",   defaultValue:"1",   remarks: "是否启用。1启用，0未启用")  {constraints(nullable:"false")}  
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "created_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_updated_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "parent_template_code", type: "varchar(" + 60 * weight + ")",  remarks: "")   

        }

    }
}