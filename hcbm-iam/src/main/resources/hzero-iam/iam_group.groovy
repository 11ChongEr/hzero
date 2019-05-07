package script.db

databaseChangeLog(logicalFilePath: 'script/db/iam_group.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2019-03-01-iam_group") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'iam_group_s', startValue:"1")
        }
        createTable(tableName: "iam_group", remarks: "") {
            column(name: "id", type: "bigint(20)", autoIncrement: true ,   remarks: "")  {constraints(primaryKey: true)} 
            column(name: "name", type: "varchar(" + 32 * weight + ")",  remarks: "组名称")  {constraints(nullable:"false")}  
            column(name: "code", type: "varchar(" + 32 * weight + ")",  remarks: "组code")  {constraints(nullable:"false")}  
            column(name: "description", type: "varchar(" + 128 * weight + ")",  remarks: "组描述")  {constraints(nullable:"false")}  
            column(name: "organization_id", type: "bigint(20)",  remarks: "组织id")  {constraints(nullable:"false")}  
            column(name: "object_version_number", type: "bigint(20)",   defaultValue:"1",   remarks: "")   
            column(name: "created_by", type: "bigint(20)",   defaultValue:"0",   remarks: "")   
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")   
            column(name: "last_updated_by", type: "bigint(20)",   defaultValue:"0",   remarks: "")   
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")   

        }

        addUniqueConstraint(columnNames:"code",tableName:"iam_group",constraintName: "iam_group_u1")
        addUniqueConstraint(columnNames:"name",tableName:"iam_group",constraintName: "iam_group_u2")
    }
}