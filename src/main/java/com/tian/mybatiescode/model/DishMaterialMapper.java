package com.tian.mybatiescode.model;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DishMaterialMapper {
    int insert(DishMaterial record);

    int insertSelective(DishMaterial record);

    List<DishMaterial> queryByDishId(@Param("dishId") Long dishId);
}