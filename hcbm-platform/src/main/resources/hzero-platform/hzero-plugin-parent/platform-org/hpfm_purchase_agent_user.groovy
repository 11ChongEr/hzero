package script.db

databaseChangeLog(logicalFilePath: 'script/db/hpfm_purchase_agent_user.groovy') {
    changeSet(author: "zhiying.dong@hand-china.com", id: "2019-03-11-hpfm_purchase_agent_user") {
    if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hpfm_purchase_agent_user_s', startValue:"1")
        }
        createTable(tableName: "hpfm_purchase_agent_user", remarks: "采购员用户") {
        column(name: "agent_user_id", type: "bigint(20)", autoIncrement: true ,   remarks: "表ID，主键，供其他表做外键")  {constraints(primaryKey: true)} 
        column(name: "purchase_agent_id", type: "bigint(20)",  remarks: "公司hpfm_purchase_agent.purchase_agent_id")  {constraints(nullable:"false")}  
        column(name: "user_id", type: "bigint(20)",  remarks: "指定用户,iam_user.user_id")  {constraints(nullable:"false")}  
        column(name: "object_version_number", type: "bigint(20)",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}  
        column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  
        column(name: "created_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
        column(name: "last_updated_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
        column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  

    }

   addUniqueConstraint(columnNames:"purchase_agent_id,user_id",tableName:"hpfm_purchase_agent_user",constraintName: "hpfm_purchase_agent_user_u1")   
  }    
}