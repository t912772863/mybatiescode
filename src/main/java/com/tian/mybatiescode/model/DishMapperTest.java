package com.tian.mybatiescode.model;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;

/**
 * mybatis代码生成插件, 英文文档:http://www.mybatis.org/generator/
 * Created by Administrator on 2018/4/3 0003.
 */
public class DishMapperTest {
    private static SqlSessionFactory sqlSessionFactory;

    public static void main(String[] args) throws IOException {
        test7();


    }

    /**
     * mybatis的基本使用, 直接读xml文件, 不用接口的方式
     */
    public static void test1() throws IOException {
        SqlSession sqlSession = getSqlSession();
        List<Dish> dishList = sqlSession.selectList("selectAll");
        for (Dish d : dishList
                ) {
            System.out.println(d);
        }

    }

    /**
     * 接口 + xml文件的方式
     */
    public static void test2() {
        SqlSession sqlSession = getSqlSession();
        System.out.println(sqlSession.getConfiguration().getDatabaseId());
        // 获取mapper接口
        DishMapper dishMapper = sqlSession.getMapper(DishMapper.class);
        Dish dish = dishMapper.queryById("3424");
        System.out.println(dish);
    }

    public static void test3() {
        SqlSession sqlSession = getSqlSession();
        DishMapper dishMapper = sqlSession.getMapper(DishMapper.class);
        // 防止查询出所有
//        List<Dish> dishList = dishMapper.queryByNameLike("");
        // like查询的时候,注意有可能中文乱码, 导致查询不出数据
        List<Dish> dishList = dishMapper.queryByNameLike("tes");
        for (Dish d : dishList) {
            System.out.println(d);
        }
    }

    /**
     * 演示mybatis中通过OGNL表达式调用对象的静态方法
     */
    public static void test4() {
        SqlSession sqlSession = getSqlSession();
        DishMapper dishMapper = sqlSession.getMapper(DishMapper.class);
        List<Dish> dishList = dishMapper.testCheckNameLength("t");
        for (Dish d : dishList) {
            System.out.println(d);
        }
    }

    public static void test5() {
        SqlSession sqlSession = getSqlSession();
        DishMapper dishMapper = sqlSession.getMapper(DishMapper.class);
        Dish dish = dishMapper.queryByIdWithMaterial(22451L);
        System.out.println(dish);
    }

    public static void test6() {
        SqlSession sqlSession = getSqlSession();
        DishMapper dishMapper = sqlSession.getMapper(DishMapper.class);
        Dish dish = dishMapper.queryByIdWithMaterial2(22451L);
        System.out.println(dish);
    }

    public static void test7() {
        SqlSession sqlSession = getSqlSession();
        DishMapper dishMapper = sqlSession.getMapper(DishMapper.class);
        Map<String, Dish> dishMap = dishMapper.queryMap();
        System.out.println(dishMap);
    }


    public static SqlSession getSqlSession() {
        Reader reader = null;
        SqlSession sqlSession = null;
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            reader.close();
            sqlSession = sqlSessionFactory.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sqlSession;
    }

}
