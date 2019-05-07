package script.db

databaseChangeLog(logicalFilePath: 'script/db/fd_organization.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2019-03-01-fd_organization") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'fd_organization_s', startValue:"1")
        }
        createTable(tableName: "fd_organization", remarks: "") {
            column(name: "id", type: "bigint(20)", autoIncrement: true ,   remarks: "")  {constraints(primaryKey: true)} 
            column(name: "name", type: "varchar(" + 32 * weight + ")",  remarks: "组织名")  {constraints(nullable:"false")}  
            column(name: "code", type: "varchar(" + 15 * weight + ")",  remarks: "组织code")  {constraints(nullable:"false")}  
            column(name: "is_enabled", type: "tinyint(3)",   defaultValue:"1",   remarks: "是否启用。1启用，0未启用")  {constraints(nullable:"false")}  
            column(name: "USER_ID", type: "bigint(20)",   defaultValue:"1",   remarks: "创建用户的id")   
            column(name: "ADDRESS", type: "varchar(" + 128 * weight + ")",  remarks: "组织的地址")   
            column(name: "object_version_number", type: "bigint(20)",   defaultValue:"1",   remarks: "")   
            column(name: "created_by", type: "bigint(20)",   defaultValue:"0",   remarks: "")   
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")   
            column(name: "last_updated_by", type: "bigint(20)",   defaultValue:"0",   remarks: "")   
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")   

        }

        addUniqueConstraint(columnNames:"code",tableName:"fd_organization",constraintName: "fd_organization_u1")
    }

    changeSet(author: 'hzero@hand-china.com', id: '2018-12-18-fd-organization-add') {
        addColumn(tableName: 'FD_ORGANIZATION') {
            column(name: 'IMAGE_URL', type: 'VARCHAR(255)', remarks: '组织图标url', afterColumn: 'ADDRESS')
        }
    }


    changeSet(author: 'hzero@hand-china.com', id: '2019-04-25-fd-organization-add') {
        addColumn(tableName: 'FD_ORGANIZATION') {
            column(name: 'IS_REGISTER', type: 'TINYINT UNSIGNED', defaultValue: "0", remarks: '是否为注册组织。1.是，0不是', afterColumn: 'IS_ENABLED') {
                constraints(nullable: false)
            }
            column(name: 'SCALE', type: 'TINYINT UNSIGNED', remarks: '组织规模。0：0-30,1：30-100,2：100', afterColumn: 'IMAGE_URL')
        }
    }
}