package script.db

databaseChangeLog(logicalFilePath: 'script/db/hpfm_exchange_rate_type.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2019-03-01-hpfm_exchange_rate_type") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hpfm_exchange_rate_type_s', startValue:"1")
        }
        createTable(tableName: "hpfm_exchange_rate_type", remarks: "汇率类型") {
            column(name: "rate_type_id", type: "bigint(20)", autoIncrement: true ,   remarks: "表ID，主键，供其他表做外键")  {constraints(primaryKey: true)} 
            column(name: "type_code", type: "varchar(" + 30 * weight + ")",  remarks: "类型代码")  {constraints(nullable:"false")}  
            column(name: "type_name", type: "varchar(" + 30 * weight + ")",  remarks: "类型名称")  {constraints(nullable:"false")}  
            column(name: "rate_method_code", type: "varchar(" + 30 * weight + ")",  remarks: "方式，值集：HMDM.EXCHANGE_RATE_METHOD")  {constraints(nullable:"false")}  
            column(name: "enabled_flag", type: "tinyint(1)",  remarks: "启用标志")  {constraints(nullable:"false")}  
            column(name: "object_version_number", type: "bigint(20)",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}  
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "created_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_updated_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  

        }

        addUniqueConstraint(columnNames:"type_code",tableName:"hpfm_exchange_rate_type",constraintName: "hpfm_exchange_rate_type_u1")
    }

    changeSet(author: "zhiying.dong@hand-china.com", id: "2019-04-03-hpfm_exchange_rate_type"){
        addColumn(tableName: 'hpfm_exchange_rate_type') {
            column(name: "tenant_id", type: "bigint(20)", defaultValue: "0", remarks: "租户ID,hpfm_tenant.tenant_id") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "zhiying.dong@hand-china.com", id: "2019-04-03-hpfm_exchange_rate_type_1"){
        dropUniqueConstraint(tableName: "hpfm_exchange_rate_type",constraintName: "hpfm_exchange_rate_type_u1")
        addUniqueConstraint(columnNames:"type_code,tenant_id",tableName:"hpfm_exchange_rate_type",constraintName: "hpfm_exchange_rate_type_u1")
    }
}