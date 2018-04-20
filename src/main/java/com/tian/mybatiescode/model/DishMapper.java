package com.tian.mybatiescode.model;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface DishMapper {
    int insertSelective(Dish record);

    Dish queryById(@Param("id") String id);

    List<Dish> queryByName(@Param("name") String name);

    Set<String> queryAllDishName();

    List<Dish> selectAll();

    /**
     * 测试bind标签的用法,choose用法
     * 比如要模糊匹配的时候我们会用到concat方法,来拼接几个字符串,
     *
     * @param name
     * @return
     */
    List<Dish> queryByNameLike(@Param("name") String name);

    /**
     * 演示mybatis中OGNL表壳来调用java类中的静态方法,实现一些比如验证工作
     * @param name
     * @return
     */
    List<Dish> testCheckNameLength(@Param("name")String name);

    /**
     * 查询菜品, 包括了材料(一对多查询)
     * @param id
     * @return
     */
    Dish queryByIdWithMaterial(Long id);

    /**
     * 通过嵌套子查询, 实现一对多查询
     * @param id
     * @return
     */
    Dish queryByIdWithMaterial2(Long id);

}