package com.tian.mybatiescode.model;

import org.apache.ibatis.session.SqlSession;

import java.util.Date;
import java.util.Map;

import static com.tian.mybatiescode.model.DishMapperTest.getSqlSession;

/**
 * 测试通过注解实现mybatis的一些基本用法
 * Created by Administrator on 2018/4/16 0016.
 */
public class ButtonMapperTest {
    public static void main(String[] args) {
        test5();
    }

    public static void test1(){
        SqlSession sqlSession = getSqlSession();
        Button button = sqlSession.getMapper(ButtonMapper.class).queryById(1L);
        System.out.println(button);
    }

    public static void test2(){
        SqlSession sqlSession = getSqlSession();
        Button button = sqlSession.getMapper(ButtonMapper.class).queryById2(1L);
        System.out.println(button);
    }

    public static void test3(){
        SqlSession sqlSession = getSqlSession();
        Button button = sqlSession.getMapper(ButtonMapper.class).queryById3(1L);
        System.out.println(button);
    }

    public static void test4(){
        SqlSession sqlSession = getSqlSession();
        Button button = new Button();
        button.setName("buttonName");
        button.setType("buttonType");
        button.setLevel(1);
        button.setCreateTime(new Date());
        button.setUseStatus(1);
        button.setStatus(1);
        sqlSession.getMapper(ButtonMapper.class).insert(button);
        sqlSession.commit();
        System.out.println(button);
    }

    public static void test5(){
        SqlSession sqlSession = getSqlSession();
        Button button = sqlSession.getMapper(ButtonMapper.class).queryById4(1L);
        System.out.println(button);
    }

    public static void  test6(){
        SqlSession sqlSession = getSqlSession();
        Map<Long, Button> buttonMap = sqlSession.getMapper(ButtonMapper.class).queryMap();
        System.out.println(buttonMap);
    }
}
