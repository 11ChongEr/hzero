package script.db

databaseChangeLog(logicalFilePath: 'script/db/fd_lookup_value.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2019-03-01-fd_lookup_value") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'fd_lookup_value_s', startValue:"1")
        }
        createTable(tableName: "fd_lookup_value", remarks: "") {
            column(name: "id", type: "bigint(20)", autoIncrement: true ,   remarks: "")  {constraints(primaryKey: true)} 
            column(name: "lookup_id", type: "bigint(20)",  remarks: "值名称")  {constraints(nullable:"false")}  
            column(name: "code", type: "varchar(" + 32 * weight + ")",  remarks: "代码")  {constraints(nullable:"false")}  
            column(name: "description", type: "varchar(" + 256 * weight + ")",  remarks: "描述")   
            column(name: "object_version_number", type: "bigint(20)",   defaultValue:"1",   remarks: "")   
            column(name: "created_by", type: "bigint(20)",   defaultValue:"0",   remarks: "")   
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")   
            column(name: "last_updated_by", type: "bigint(20)",   defaultValue:"0",   remarks: "")   
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")   

        }

        addUniqueConstraint(columnNames:"lookup_id,code",tableName:"fd_lookup_value",constraintName: "fd_lookup_value_u1")
    }
}