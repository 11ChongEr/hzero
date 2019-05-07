package script.db

databaseChangeLog(logicalFilePath: 'script/db/hpfm_ui_page_script.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2019-03-01-hpfm_ui_page_script") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hpfm_ui_page_script_s', startValue:"1")
        }
        createTable(tableName: "hpfm_ui_page_script", remarks: "UI页面配置脚本") {
            column(name: "script_id", type: "bigint(20)", autoIncrement: true ,   remarks: "表ID，主键，供其他表做外键")  {constraints(primaryKey: true)} 
            column(name: "page_id", type: "bigint(20)",  remarks: "HPFM_UI_PAGE.PAGE_ID")  {constraints(nullable:"false")}  
            column(name: "name", type: "varchar(" + 100 * weight + ")",  remarks: "方法名")  {constraints(nullable:"false")}  
            column(name: "description", type: "varchar(" + 240 * weight + ")",  remarks: "方法描述")  {constraints(nullable:"false")}  
            column(name: "content", type: "longtext",  remarks: "脚本内容")  {constraints(nullable:"false")}  
            column(name: "babel_content", type: "longtext",  remarks: "脚本编译内容")  {constraints(nullable:"false")}  
            column(name: "object_version_number", type: "bigint(20)",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}  
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "created_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_updated_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  

        }

        addUniqueConstraint(columnNames:"page_id,name",tableName:"hpfm_ui_page_script",constraintName: "hpfm_ui_page_script_u1")
    }
}