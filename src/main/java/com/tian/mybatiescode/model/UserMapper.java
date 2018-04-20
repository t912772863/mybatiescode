package com.tian.mybatiescode.model;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User queryByMailAndMobile(@Param("mail")String email, @Param("mobile")String mobile);

    /**
     * 演示resultMap的继承使用方法
     *
     * @param id
     * @return
     */
    User queryByIdWithTest(Long id);

    /**
     * association注解使用演示
     * @param id
     * @return
     */
    User queryByIdWithTest2(Long id);

    /**
     * association注解中引有一个外部文件的resultMap
     * @param id
     * @return
     */
    User queryByIdWithTest3(Long id);

    /**
     * 测试通过嵌套子查询实现
     * @param id
     * @return
     */
    User queryByIdWithTest4(@Param("id") Long id);
}