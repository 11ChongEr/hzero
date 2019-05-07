package script.db

databaseChangeLog(logicalFilePath: 'script/db/fd_project_type.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2019-03-01-fd_project_type") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'fd_project_type_s', startValue:"1")
        }
        createTable(tableName: "fd_project_type", remarks: "") {
            column(name: "ID", type: "bigint(20)", autoIncrement: true ,   remarks: "")  {constraints(primaryKey: true)} 
            column(name: "NAME", type: "varchar(" + 64 * weight + ")",  remarks: "类型名称")  {constraints(nullable:"false")}  
            column(name: "CODE", type: "varchar(" + 128 * weight + ")",  remarks: "类型编码")  {constraints(nullable:"false")}  
            column(name: "DESCRIPTION", type: "varchar(" + 255 * weight + ")",  remarks: "类型描述")   
            column(name: "OBJECT_VERSION_NUMBER", type: "bigint(20)",   defaultValue:"1",   remarks: "")   
            column(name: "CREATED_BY", type: "bigint(20)",   defaultValue:"0",   remarks: "")   
            column(name: "CREATION_DATE", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")   
            column(name: "LAST_UPDATED_BY", type: "bigint(20)",   defaultValue:"0",   remarks: "")   
            column(name: "LAST_UPDATE_DATE", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")   

        }

        addUniqueConstraint(columnNames:"CODE",tableName:"fd_project_type",constraintName: "UK_FD_PROJECT_TYPE_CODE")
    }
}