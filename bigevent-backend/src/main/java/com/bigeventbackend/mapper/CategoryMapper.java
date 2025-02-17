package com.bigeventbackend.mapper;

import com.bigeventbackend.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Insert("INSERT INTO category(category_name, category_alias, create_user, create_time, update_time)" +
            "VALUES (#{categoryName}, #{categoryAlias}, #{createUser}, #{createTime}, #{updateTime})")
    void add(Category category);

    @Select("SELECT * FROM category WHERE create_user = #{userId}")
    List<Category> list(Integer userId);

    @Select("SELECT * FROM category WHERE id = #{id}")
    Category findById(Integer id);

    @Update( "UPDATE category SET category_name=#{categoryName}, category_alias=#{categoryAlias}, update_time=#{updateTime} WHERE id=#{id}")
    void update(Category category);

    @Delete("DELETE FROM category WHERE id=#{id}")
    void deleteById(Integer id);
}
