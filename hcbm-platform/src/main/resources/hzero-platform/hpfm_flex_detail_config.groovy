package script.db

databaseChangeLog(logicalFilePath: 'script/db/hpfm_flex_detail_config.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2019-04-24-hpfm_flex_detail_config") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hpfm_flex_detail_config_s', startValue:"1")
        }
        createTable(tableName: "hpfm_flex_detail_config", remarks: "弹性域规则明细配置") {
            column(name: "detail_config_id", type: "bigint(20)", autoIncrement: true ,   remarks: "表ID，主键，供其他表做外键")  {constraints(primaryKey: true)} 
            column(name: "rule_detail_id", type: "bigint(20)",  remarks: "弹性域规则明细hpfm_flex_detail_field.rule_detail_id")  {constraints(nullable:"false")}  
            column(name: "model_field_id", type: "bigint(20)",  remarks: "弹性域模型字段：hpfm_flex_model_field.model_field_id")  {constraints(nullable:"false")}  
            column(name: "order_seq", type: "int(11)",  remarks: "排序号")  {constraints(nullable:"false")}  
            column(name: "field_column_number", type: "int(11)",  remarks: "字段行号")  {constraints(nullable:"false")}  
            column(name: "field_column_width", type: "int(11)",  remarks: "字段宽度")  {constraints(nullable:"false")}  
            column(name: "field_type", type: "varchar(" + 30 * weight + ")",  remarks: "规则字段类型，值集：HPFM.FLEX.FIELD_TYPE")  {constraints(nullable:"false")}  
            column(name: "readable_flag", type: "tinyint(1)",   defaultValue:"1",   remarks: "是否只读")  {constraints(nullable:"false")}  
            column(name: "required_flag", type: "tinyint(1)",  remarks: "是否必输")  {constraints(nullable:"false")}  
            column(name: "object_version_number", type: "bigint(20)",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}  
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "created_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_updated_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  

        }

        addUniqueConstraint(columnNames:"rule_detail_id,model_field_id",tableName:"hpfm_flex_detail_config",constraintName: "hpfm_flex_detail_config_u1")
    }
}