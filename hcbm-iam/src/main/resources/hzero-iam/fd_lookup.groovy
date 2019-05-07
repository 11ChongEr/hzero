package script.db

databaseChangeLog(logicalFilePath: 'script/db/fd_lookup.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2019-03-01-fd_lookup") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'fd_lookup_s', startValue:"1")
        }
        createTable(tableName: "fd_lookup", remarks: "") {
            column(name: "id", type: "bigint(20)", autoIncrement: true ,   remarks: "")  {constraints(primaryKey: true)} 
            column(name: "code", type: "varchar(" + 32 * weight + ")",  remarks: "代码")  {constraints(nullable:"false")}  
            column(name: "description", type: "varchar(" + 128 * weight + ")",  remarks: "描述")   
            column(name: "object_version_number", type: "bigint(20)",   defaultValue:"1",   remarks: "")   
            column(name: "created_by", type: "bigint(20)",   defaultValue:"0",   remarks: "")   
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")   
            column(name: "last_updated_by", type: "bigint(20)",   defaultValue:"0",   remarks: "")   
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")   

        }

        addUniqueConstraint(columnNames:"code",tableName:"fd_lookup",constraintName: "fd_lookup_u1")
    }
}