package script.db

databaseChangeLog(logicalFilePath: 'script/db/iam_book_mark.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2019-03-01-iam_book_mark") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'iam_book_mark_s', startValue:"1")
        }
        createTable(tableName: "iam_book_mark", remarks: "") {
            column(name: "ID", type: "bigint(20)", autoIncrement: true ,   remarks: "")  {constraints(primaryKey: true)} 
            column(name: "NAME", type: "varchar(" + 64 * weight + ")",  remarks: "书签名称")  {constraints(nullable:"false")}  
            column(name: "URL", type: "varchar(" + 255 * weight + ")",  remarks: "书签url")  {constraints(nullable:"false")}  
            column(name: "ICON", type: "varchar(" + 128 * weight + ")",  remarks: "图标的code值")  {constraints(nullable:"false")}  
            column(name: "COLOR", type: "varchar(" + 32 * weight + ")",  remarks: "图标的颜色")   
            column(name: "SORT", type: "bigint(20)",  remarks: "书签顺序")  {constraints(nullable:"false")}  
            column(name: "USER_ID", type: "bigint(20)",  remarks: "用户ID")  {constraints(nullable:"false")}  
            column(name: "OBJECT_VERSION_NUMBER", type: "bigint(20)",   defaultValue:"1",   remarks: "")   
            column(name: "CREATED_BY", type: "bigint(20)",   defaultValue:"0",   remarks: "")   
            column(name: "CREATION_DATE", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")   
            column(name: "LAST_UPDATED_BY", type: "bigint(20)",   defaultValue:"0",   remarks: "")   
            column(name: "LAST_UPDATE_DATE", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")   

        }

    }
}