package script.db

databaseChangeLog(logicalFilePath: 'script/db/oauth_login_history.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2019-03-01-oauth_login_history") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'oauth_login_history_s', startValue:"1")
        }
        createTable(tableName: "oauth_login_history", remarks: "") {
            column(name: "id", type: "bigint(20)", autoIncrement: true ,   remarks: "")  {constraints(primaryKey: true)} 
            column(name: "user_id", type: "bigint(20)",  remarks: "用户id")  {constraints(nullable:"false")}  
            column(name: "last_login_at", type: "datetime",  remarks: "用户最后一次登录的时间")  {constraints(nullable:"false")}  
            column(name: "object_version_number", type: "bigint(20)",   defaultValue:"1",   remarks: "")   
            column(name: "created_by", type: "bigint(20)",   defaultValue:"0",   remarks: "")   
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")   
            column(name: "last_updated_by", type: "bigint(20)",   defaultValue:"0",   remarks: "")   
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")   

        }

        addUniqueConstraint(columnNames:"user_id",tableName:"oauth_login_history",constraintName: "oauth_login_history_u1")
    }
}