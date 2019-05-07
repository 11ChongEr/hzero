package script.db

databaseChangeLog(logicalFilePath: 'script/db/iam_user.groovy') {
    def weight = 1
    if(helper.isSqlServer()){
        weight = 2
    } else if(helper.isOracle()){
        weight = 3
    }
    changeSet(author: "hzero@hand-china.com", id: "2019-03-01-iam_user") {
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'iam_user_s', startValue:"1")
        }
        createTable(tableName: "iam_user", remarks: "") {
            column(name: "id", type: "bigint(20)", autoIncrement: true ,   remarks: "")  {constraints(primaryKey: true)} 
            column(name: "login_name", type: "varchar(" + 128 * weight + ")",  remarks: "用户名")  {constraints(nullable:"false")}  
            column(name: "email", type: "varchar(" + 128 * weight + ")",  remarks: "电子邮箱地址")  {constraints(nullable:"false")}  
            column(name: "organization_id", type: "bigint(20)",  remarks: "组织ID")  {constraints(nullable:"false")}  
            column(name: "password", type: "varchar(" + 128 * weight + ")",  remarks: "Hash后的用户密码")  {constraints(nullable:"false")}  
            column(name: "real_name", type: "varchar(" + 128 * weight + ")",  remarks: "用户真实姓名")   
            column(name: "phone", type: "varchar(" + 32 * weight + ")",  remarks: "手机号")   
            column(name: "INTERNATIONAL_TEL_CODE", type: "varchar(" + 16 * weight + ")",   defaultValue:"+86",   remarks: "国际电话区号。")   
            column(name: "image_url", type: "varchar(" + 480 * weight + ")",  remarks: "用户头像地址")   
            column(name: "profile_photo", type: "mediumtext",  remarks: "用户二进制头像")   
            column(name: "language", type: "varchar(" + 16 * weight + ")",   defaultValue:"zh_CN",   remarks: "语言")  {constraints(nullable:"false")}  
            column(name: "time_zone", type: "varchar(" + 16 * weight + ")",   defaultValue:"GMT+8",   remarks: "时区")  {constraints(nullable:"false")}  
            column(name: "last_password_updated_at", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "上一次密码更新时间")  {constraints(nullable:"false")}  
            column(name: "last_login_at", type: "datetime",  remarks: "上一次登陆时间")   
            column(name: "is_enabled", type: "tinyint(3)",   defaultValue:"1",   remarks: "用户是否启用。1启用，0未启用")  {constraints(nullable:"false")}  
            column(name: "is_locked", type: "tinyint(3)",   defaultValue:"0",   remarks: "是否锁定账户")  {constraints(nullable:"false")}  
            column(name: "is_ldap", type: "tinyint(3)",   defaultValue:"0",   remarks: "是否是LDAP来源。1是，0不是")   
            column(name: "is_admin", type: "tinyint(3)",   defaultValue:"0",   remarks: "是否为管理员用户。1表示是，0表示不是")   
            column(name: "locked_until_at", type: "datetime",  remarks: "锁定账户截止时间")   
            column(name: "password_attempt", type: "tinyint(3)",   defaultValue:"0",   remarks: "密码输错累积次数")   
            column(name: "object_version_number", type: "bigint(20)",   defaultValue:"1",   remarks: "")   
            column(name: "created_by", type: "bigint(20)",   defaultValue:"0",   remarks: "")   
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")   
            column(name: "last_updated_by", type: "bigint(20)",   defaultValue:"0",   remarks: "")   
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")   

        }

        addUniqueConstraint(columnNames:"login_name",tableName:"iam_user",constraintName: "iam_user_u1")
        addUniqueConstraint(columnNames:"email",tableName:"iam_user",constraintName: "iam_user_u2")
        addUniqueConstraint(columnNames:"phone",tableName:"iam_user",constraintName: "iam_user_u3")
    }

    changeSet(author: 'hzero@hand-china.com', id: '2019-04-25-rename-password-column') {
        renameColumn(columnDataType: 'VARCHAR(128)', newColumnName: "HASH_PASSWORD", oldColumnName: "PASSWORD", remarks: 'Hash后的用户密码', tableName: 'IAM_USER')
    }

    changeSet(author: "xiaoyu.zhao@hand-china.com", id: "2019-05-05-iam_user") {
        renameColumn(columnDataType: "varchar(" + 128 * weight + ")", newColumnName: "HASH_PASSWORD", oldColumnName: "HASH_PASSWORD", remarks: 'Hash后的用户密码', tableName: 'IAM_USER')
        dropNotNullConstraint(tableName: "iam_user", columnName: "email", columnDataType: "varchar(" + 128 * weight + ")")
    }
}
