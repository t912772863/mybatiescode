package com.tian.mybatiescode.model;

/**
 * 演示@Provider注解用法
 * Created by Administrator on 2018/4/16 0016.
 */
public class PrivilegeProvider {
    public String queryById4(long id){
         String sql = "select * from button where ID ="+id;
        return sql;
    }
}
