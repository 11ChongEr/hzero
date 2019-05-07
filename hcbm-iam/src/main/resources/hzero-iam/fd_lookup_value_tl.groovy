package script.db

databaseChangeLog(logicalFilePath: 'script/db/fd_lookup_value_tl.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2019-03-01-fd_lookup_value_tl") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'fd_lookup_value_tl_s', startValue:"1")
        }
        createTable(tableName: "fd_lookup_value_tl", remarks: "") {
            column(name: "id", type: "bigint(20)",  remarks: "关联lookup_value id")  {constraints(nullable:"false")}  
            column(name: "lang", type: "varchar(" + 16 * weight + ")",  remarks: "语言名称")  {constraints(nullable:"false")}  
            column(name: "description", type: "varchar(" + 255 * weight + ")",  remarks: "描述")   

        }

        addUniqueConstraint(columnNames:"id,lang",tableName:"fd_lookup_value_tl",constraintName: "fd_lookup_value_tl_pk")
    }
}