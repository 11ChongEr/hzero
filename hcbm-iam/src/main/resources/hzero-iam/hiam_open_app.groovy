package script.db

databaseChangeLog(logicalFilePath: 'script/db/hiam_open_app.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2019-03-01-hiam_open_app") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hiam_open_app_s', startValue:"1")
        }
        createTable(tableName: "hiam_open_app", remarks: "三方网站") {
            column(name: "open_app_id", type: "bigint(20)", autoIncrement: true ,   remarks: "表ID")  {constraints(primaryKey: true)} 
            column(name: "app_code", type: "varchar(" + 60 * weight + ")",  remarks: "应用编码")  {constraints(nullable:"false")}  
            column(name: "app_name", type: "varchar(" + 60 * weight + ")",  remarks: "应用名称")  {constraints(nullable:"false")}  
            column(name: "app_image", type: "varchar(" + 255 * weight + ")",  remarks: "应用图片地址")  {constraints(nullable:"false")}  
            column(name: "app_id", type: "varchar(" + 120 * weight + ")",  remarks: "第三方平台方appid")  {constraints(nullable:"false")}  
            column(name: "app_key", type: "varchar(" + 120 * weight + ")",  remarks: "appid对应的授权码")  {constraints(nullable:"false")}  
            column(name: "redirect_uri", type: "varchar(" + 255 * weight + ")",  remarks: "成功授权后的回调地址")  {constraints(nullable:"false")}  
            column(name: "scope", type: "varchar(" + 255 * weight + ")",  remarks: "授权列表")   
            column(name: "authorize_path", type: "varchar(" + 255 * weight + ")",  remarks: "获取认证码的地址")   
            column(name: "token_path", type: "varchar(" + 255 * weight + ")",  remarks: "获取AccessToken的地址")   
            column(name: "refresh_token_path", type: "varchar(" + 255 * weight + ")",  remarks: "RereshToken的地址")   
            column(name: "self_path", type: "varchar(" + 255 * weight + ")",  remarks: "获取个人信息的地址")   
            column(name: "order_seq", type: "int(11)",  remarks: "排序号")  {constraints(nullable:"false")}  
            column(name: "enabled_flag", type: "tinyint(1)",   defaultValue:"1",   remarks: "是否启用。1启用，0未启用")  {constraints(nullable:"false")}  
            column(name: "object_version_number", type: "bigint(20)",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}  
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "created_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_updated_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  

        }

        addUniqueConstraint(columnNames:"app_code",tableName:"hiam_open_app",constraintName: "hiam_open_app_u1")
    }
}