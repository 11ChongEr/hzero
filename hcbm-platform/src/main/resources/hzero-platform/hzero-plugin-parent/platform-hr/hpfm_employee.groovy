package script.db

databaseChangeLog(logicalFilePath: 'script/db/hpfm_employee.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2019-03-01-hpfm_employee") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hpfm_employee_s', startValue:"1")
        }
        createTable(tableName: "hpfm_employee", remarks: "员工表") {
            column(name: "employee_id", type: "bigint(20)", autoIncrement: true ,   remarks: "员工ID")  {constraints(primaryKey: true)} 
            column(name: "employee_num", type: "varchar(" + 30 * weight + ")",  remarks: "员工编码")  {constraints(nullable:"false")}  
            column(name: "name", type: "varchar(" + 60 * weight + ")",  remarks: "员工姓名")  {constraints(nullable:"false")}  
            column(name: "name_en", type: "varchar(" + 60 * weight + ")",  remarks: "员工英文名")   
            column(name: "tenant_id", type: "bigint(20)",   defaultValue:"0",   remarks: "租户ID")  {constraints(nullable:"false")}  
            column(name: "email", type: "varchar(" + 60 * weight + ")",  remarks: "电子邮件")   
            column(name: "mobile", type: "varchar(" + 60 * weight + ")",  remarks: "移动电话")   
            column(name: "gender", type: "tinyint(1)",  remarks: "性别, 0: 男 1: 女")  {constraints(nullable:"false")}  
            column(name: "cid", type: "varchar(" + 60 * weight + ")",  remarks: "身份编码")   
            column(name: "quick_index", type: "varchar(" + 30 * weight + ")",  remarks: "快速检索")   
            column(name: "phoneticize", type: "varchar(" + 60 * weight + ")",  remarks: "拼音")   
            column(name: "status", type: "varchar(" + 30 * weight + ")",  remarks: "员工状态，值集HPFM.EMPLOYEE_STATUS")   
            column(name: "enabled_flag", type: "tinyint(1)",   defaultValue:"1",   remarks: "启用状态")  {constraints(nullable:"false")}  
            column(name: "object_version_number", type: "bigint(20)",   defaultValue:"1",   remarks: "行版本号，用来处理锁 ")  {constraints(nullable:"false")}  
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "创建时间 ")  {constraints(nullable:"false")}  
            column(name: "created_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "创建人")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "最后更新时间")  {constraints(nullable:"false")}  
            column(name: "last_updated_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "最后更新人")  {constraints(nullable:"false")}  

        }
   createIndex(tableName: "hpfm_employee", indexName: "hpfm_employee_n1") {
            column(name: "name")
            column(name: "tenant_id")
        }

        addUniqueConstraint(columnNames:"employee_id,employee_num",tableName:"hpfm_employee",constraintName: "hpfm_employee_u1")
    }
}