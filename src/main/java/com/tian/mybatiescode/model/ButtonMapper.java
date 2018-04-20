package com.tian.mybatiescode.model;

import org.apache.ibatis.annotations.*;

/**
 * 演示通过注解的方式实现mybatis, 省略xml文件.
 * 这种方式不够灵活, 这里只演示一下, 开发中不建议这样用.
 */
public interface ButtonMapper {
    /**
     * 这里可以映射成功, 是因为开始了下划线与驼峰映射
     * @param id
     * @return
     */
    @Select({"select * from button where ID = #{id}"})
    Button queryById(Long id);

    /**
     * 这个方法用@Results注解,类似于xml中的ResultMap
     * mybatis3.3.1c以后,可以在results注解中通过id属性对其进行标识,
     * 这样其它方法就可以引用了, 不像以前每个方法都要写一遍
     * @param id
     * @return
     */
    @Results(id = "buttonResultMap",value = {
            @Result(property = "id",column = "v1"),
            @Result(property = "name", column = "v2")
    })
    @Select({"select ID v1, NAME v2 from button where ID = #{id}"})
    Button queryById2(Long id);

    /**
     * 通过ResultMap注解引用其它定义好的Results
     * @param id
     * @return
     */
    @ResultMap("buttonResultMap")
    @Select({"select ID v1, NAME v2 from button where ID = #{id}"})
    Button queryById3(Long id);

    /**
     * 通过注解,实现插入,并返回自增
     * @param button
     */
    @Insert({"insert into button(NAME, TYPE, LEVEL, USE_STATUS, STATUS, CREATE_TIME) " +
            "values (#{name}, #{type}, #{level}, #{useStatus}, #{status}, #{createTime})"})
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void insert(Button button);

    /**
     * 演示Provider注解的用法
     * @param id
     * @return
     */
    @SelectProvider(type = PrivilegeProvider.class, method = "queryById4")
    Button queryById4(Long id);



}