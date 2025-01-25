package com.bigeventbackend.service;

import com.bigeventbackend.pojo.Article;
import com.bigeventbackend.pojo.PageBean;
import jakarta.validation.constraints.NotNull;

public interface ArticleService {
    void add(Article article);

    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    Article findById(@NotNull Integer id);

    void update(Article article);

    void delete(@NotNull Integer id);
}
