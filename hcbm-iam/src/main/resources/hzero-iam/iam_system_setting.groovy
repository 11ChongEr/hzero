package script.db

databaseChangeLog(logicalFilePath: 'script/db/iam_system_setting.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2019-03-01-iam_system_setting") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'iam_system_setting_s', startValue:"1")
        }
        createTable(tableName: "iam_system_setting", remarks: "") {
            column(name: "ID", type: "bigint(20)", autoIncrement: true ,   remarks: "")  {constraints(primaryKey: true)} 
            column(name: "FAVICON", type: "varchar(" + 255 * weight + ")",  remarks: "平台徽标链接")  {constraints(nullable:"false")}  
            column(name: "SYSTEM_LOGO", type: "varchar(" + 255 * weight + ")",  remarks: "平台导航栏图形标链接")   
            column(name: "SYSTEM_NAME", type: "varchar(" + 100 * weight + ")",  remarks: "平台简称")  {constraints(nullable:"false")}  
            column(name: "SYSTEM_TITLE", type: "varchar(" + 255 * weight + ")",  remarks: "平台全称")   
            column(name: "DEFAULT_PASSWORD", type: "varchar(" + 50 * weight + ")",  remarks: "平台默认密码")  {constraints(nullable:"false")}  
            column(name: "DEFAULT_LANGUAGE", type: "varchar(" + 50 * weight + ")",  remarks: "平台默认语言")  {constraints(nullable:"false")}  
            column(name: "MIN_PASSWORD_LENGTH", type: "int(10)",   defaultValue:"0",   remarks: "不启用组织层密码策略时的密码最小长度")  {constraints(nullable:"false")}  
            column(name: "MAX_PASSWORD_LENGTH", type: "int(10)",   defaultValue:"65535",   remarks: "不启用组织层密码策略时的密码最大长度")  {constraints(nullable:"false")}  
            column(name: "OBJECT_VERSION_NUMBER", type: "bigint(20)",   defaultValue:"1",   remarks: "")   
            column(name: "CREATED_BY", type: "bigint(20)",   defaultValue:"0",   remarks: "")   
            column(name: "CREATION_DATE", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")   
            column(name: "LAST_UPDATED_BY", type: "bigint(20)",   defaultValue:"0",   remarks: "")   
            column(name: "LAST_UPDATE_DATE", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")   

        }

    }

    changeSet(author: 'hzero@hand-china.com', id: '2019-04-25-iam-add-column') {
        addColumn(tableName: 'IAM_SYSTEM_SETTING') {
            column(name: 'REGISTER_ENABLED', type: 'TINYINT UNSIGNED', remarks: '是否启用组织注册功能，默认为0，禁用', afterColumn: 'MAX_PASSWORD_LENGTH', defaultValue: 0) {
                constraints(nullable: false)
            }
            column(name: 'REGISTER_URL', type: 'VARCHAR(255)', remarks: '注册组织链接', afterColumn: 'REGISTER_ENABLED')
        }
    }
}