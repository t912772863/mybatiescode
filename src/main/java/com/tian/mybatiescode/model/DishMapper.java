package com.tian.mybatiescode.model;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;
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

    /**
     * 一个mapper接口中即可以有xml映射, 也可以有注解方法, 但是方法名不能重复
     * @return
     */
    @Select("select * from dish")
    @MapKey("id")
    Map<String, Dish> queryMap();

    /**
     * 这个方法和上面的方法功能是一样的, 区别在于, 上面的方法sql语句是通过注解方法指定的.
     * 而这个方法中sql语句是使用我们最常用的xml中指定的. 需要
     * 特别注意: 本方法返回的是一个map, 但是xml中的方法中result的类型是map中value的类型,
     *           map中key, 为MapKey注解中指定的字段的值
     * @return
     */
    @MapKey("id")
    Map<String, Dish> queryMap2();

}