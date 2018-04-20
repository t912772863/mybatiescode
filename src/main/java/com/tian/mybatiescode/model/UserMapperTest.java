package com.tian.mybatiescode.model;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.Date;

/**
 * Created by Administrator on 2018/4/9 0009.
 */
public class UserMapperTest {
    private static SqlSessionFactory sqlSessionFactory;

    public static void main(String[] args) {
        test9();
    }

    /**
     * 测试mybatis查询的时候, 映射到一个属性对象中
     */
    public static void test1(){
        SqlSession sqlSession = getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectByPrimaryKey(1L);
        System.out.println(user);
    }

    /**
     * 测试插入方法, 返回自增主键等
     */
    public static void test2(){
        SqlSession sqlSession = getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 插入一条记录
        User user = new User();
        user.setRemark("测试插入");
        user.setMobile("11111111");
        user.setCreateTime(new Date());
        userMapper.insert(user);
        sqlSession.commit();
    }

    public static void test3(){
        SqlSession sqlSession = getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.queryByMailAndMobile("912772863@qq.com","13510272496");
        System.out.println(user);
    }

    public static void test4(){
        SqlSession sqlSession = getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.queryByIdWithTest(1L);
        System.out.println(user);
    }

    public static void test5(){
        SqlSession sqlSession = getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.queryByIdWithTest2(1L);
        System.out.println(user);
    }

    public static void test6(){
        SqlSession sqlSession = getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.queryByIdWithTest3(1L);
        System.out.println(user);
    }

    public static void test7(){
        SqlSession sqlSession = getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.queryByIdWithTest4(1L);
        System.out.println(user);
    }

    /**
     * 演示mybatis的一级缓存
     * mybatis的一级缓存在于SqlSession的生命周期中, 在同一个SqlSession中查询时,mybatis会把执行的方法和参数等
     * 转换成一个Cache对象,进而转换一个个key, 把查询出来的结果当成一个value, 然后存入本地缓存中. 如果同一个SqlSession
     * 执行的方法和参数完全一致, 那么会从缓存中得到查询结果,而不用去查询数据库
     * 如果不想让某个查询方法使用一级缓存,可以在xml中指定属性flushCache=true
     * 在执行insert, delete, update等对数据库数据产生影响的操作时, 默认也会清除一级缓存
     *
     *
     */
    public static void test8(){
        SqlSession sqlSession = getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 查询出一个对象
        User user1 = userMapper.selectByPrimaryKey(1L);
        // 修改这个对象中的一个属性
        user1.setNickName("newNickName");
        // 再次查询同一个user
        User user2 = userMapper.selectByPrimaryKey(1L);
        // 虽然没有改数据库, 但是这个时候,user2中的nickName已经是上面set的新的了,这是因为上面修改的对象
        // 其实是就是一级缓存中的对象,第二次查询的时候,直接从一级缓存中查出来所以就是修改后的对象了
        System.out.println(user2.getNickName());
        // 验证user1和user2就是同一个实例
        System.out.println(user1.equals(user2));
        System.out.println(user1 == user2);

        System.out.println("======================");
        // 再重新获取一个SqlSession
        SqlSession sqlSession2 = getSqlSession();
        UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
        // 查询出一个对象
        User user3 = userMapper2.selectByPrimaryKey(1L);
        System.out.println(user3.getNickName());
        System.out.println(user1 == user3);
        System.out.println(user2 == user3);

    }

    /**
     * 关于mybatis的二级缓存
     * 可以简单的理解为二级缓存是存在于SqlSessionFactory生命周期中的. 如果存在多个SqlSessionFactory,
     * 那么它们之间的二级缓存也是不互通的. 如果想让他们互通.则要再添加一些外部的缓存,比如redis
     */
    public static void test9(){
        SqlSession sqlSession = getSqlSession();
        User user1 = null;
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            user1 = userMapper.selectByPrimaryKey(1L);
            // 对当前对象重新赋值
            user1.setNickName("newNickName");
            // 再次查询
            User user2 = userMapper.selectByPrimaryKey(1L);
            // 虽然没有更新数据库, 但是user2中的值已经是新的了, 对比发现user1,user2就是同一个对象
            System.out.println(user2.getNickName());
            System.out.println(user1 == user2);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 关闭当前sqlSession, 关闭的时候才会生成二级缓存, 二级缓存要求对象实现序列化接口
            sqlSession.close();
        }

        // 开启一个新的SqlSession
        sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 查询
            User user2 = userMapper.selectByPrimaryKey(1L);
            // 查看,发现名字也是新的了.
            System.out.println(user2.getNickName());
            // 但是这里的对象和前面sqlSession查出来的对象是不同的实例
            System.out.println(user2 == user1);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    private static SqlSession getSqlSession(){
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
