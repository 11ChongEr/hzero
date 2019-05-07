package script.db

databaseChangeLog(logicalFilePath: 'script/db/fd_project.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2019-03-01-fd_project") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'fd_project_s', startValue:"1")
        }
        createTable(tableName: "fd_project", remarks: "") {
            column(name: "id", type: "bigint(20)", autoIncrement: true ,   remarks: "")  {constraints(primaryKey: true)} 
            column(name: "name", type: "varchar(" + 32 * weight + ")",  remarks: "项目名")  {constraints(nullable:"false")}  
            column(name: "code", type: "varchar(" + 14 * weight + ")",  remarks: "项目code")  {constraints(nullable:"false")}  
            column(name: "organization_id", type: "bigint(20)",  remarks: "组织ID")   
            column(name: "is_enabled", type: "tinyint(3)",   defaultValue:"1",   remarks: "是否启用。1启用，0未启用")  {constraints(nullable:"false")}  
            column(name: "object_version_number", type: "bigint(20)",   defaultValue:"1",   remarks: "")   
            column(name: "created_by", type: "bigint(20)",   defaultValue:"0",   remarks: "")   
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")   
            column(name: "last_updated_by", type: "bigint(20)",   defaultValue:"0",   remarks: "")   
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")   
            column(name: "TYPE", type: "varchar(" + 64 * weight + ")",  remarks: "项目类型")   

        }

        addUniqueConstraint(columnNames:"code,organization_id",tableName:"fd_project",constraintName: "fd_project_u1")
    }

    changeSet(author: 'hzero@hand-china.com', id: '2018-12-18-fd-project-add') {
        addColumn(tableName: 'FD_PROJECT') {
            column(name: 'IMAGE_URL', type: 'VARCHAR(255)', remarks: '项目图标url', afterColumn: 'IS_ENABLED')
        }
    }

    changeSet(author: 'hzero@hand-china.com', id: '2019-04-25-fd-project-add-category') {
        addColumn(tableName: 'FD_PROJECT') {
            column(name: 'CATEGORY', type: 'VARCHAR(64)', remarks: '项目类别：AGILE(敏捷项目),PROGRAM(普通项目组),ANALYTICAL(分析型项目群)', afterColumn: 'TYPE', defaultValue: 'AGILE')
        }
    }
}