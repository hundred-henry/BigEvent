package com.bigeventbackend.mapper;

import com.bigeventbackend.pojo.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {
    @Insert("INSERT INTO article(title, content, cover_img, state, category_id, create_user, create_time, update_time)" +
            "VALUES (#{title}, #{content}, #{coverImg}, #{state}, #{categoryId}, #{createUser}, #{createTime}, #{updateTime})")
    void add(Article article);

    List<Article> list(Integer userId, Integer categoryId, String state);

    @Select("SELECT * FROM article WHERE id=#{id}")
    Article findById(Integer id);

    @Update("UPDATE article SET title=#{title},content=#{content},cover_img=#{coverImg},state=#{state},category_id=#{categoryId},update_time=#{updateTime} where id=#{id}")
    void update(Article article);

    @Delete("DELETE FROM article WHERE id=#{id}")
    void delete(Integer id);
}
