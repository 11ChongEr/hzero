package script.db

databaseChangeLog(logicalFilePath: 'script/db/hpfm_flex_rule_detail.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2019-04-24-hpfm_flex_rule_detail") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hpfm_flex_rule_detail_s', startValue:"1")
        }
        createTable(tableName: "hpfm_flex_rule_detail", remarks: "弹性域规则明细") {
            column(name: "rule_detail_id", type: "bigint(20)", autoIncrement: true ,   remarks: "表ID，主键，供其他表做外键")  {constraints(primaryKey: true)} 
            column(name: "rule_id", type: "bigint(20)",  remarks: "弹性域规则hpfm_flex_rule.rule_id")  {constraints(nullable:"false")}  
            column(name: "detail_code", type: "varchar(" + 30 * weight + ")",  remarks: "弹性域规则明细编码")  {constraints(nullable:"false")}  
            column(name: "description", type: "varchar(" + 240 * weight + ")",  remarks: "弹性域规则明细描述")   
            column(name: "enabled_flag", type: "tinyint(1)",   defaultValue:"1",   remarks: "是否启用。1启用，0未启用")  {constraints(nullable:"false")}  
            column(name: "object_version_number", type: "bigint(20)",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}  
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "created_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_updated_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  

        }

        addUniqueConstraint(columnNames:"rule_id,detail_code",tableName:"hpfm_flex_rule_detail",constraintName: "hpfm_flex_rule_detail_u1")
    }
}