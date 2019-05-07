package script.db

databaseChangeLog(logicalFilePath: 'script/db/iam_menu_config.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2019-03-01-iam_menu_config") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'iam_menu_config_s', startValue:"1")
        }
        createTable(tableName: "iam_menu_config", remarks: "") {
            column(name: "id", type: "bigint(20)", autoIncrement: true ,   remarks: "")  {constraints(primaryKey: true)} 
            column(name: "menu_id", type: "bigint(20)",  remarks: "菜单id")   
            column(name: "domain", type: "varchar(" + 128 * weight + ")",  remarks: "域名")   
            column(name: "devops_service_group", type: "varchar(" + 128 * weight + ")",  remarks: "服务组")   
            column(name: "devops_service_type", type: "varchar(" + 128 * weight + ")",  remarks: "服务类型")   
            column(name: "devops_service_code", type: "varchar(" + 128 * weight + ")",  remarks: "服务代码")   
            column(name: "object_version_number", type: "bigint(20)",   defaultValue:"1",   remarks: "")   
            column(name: "created_by", type: "bigint(20)",   defaultValue:"0",   remarks: "")   
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")   
            column(name: "last_updated_by", type: "bigint(20)",   defaultValue:"0",   remarks: "")   
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")   

        }

    }
}