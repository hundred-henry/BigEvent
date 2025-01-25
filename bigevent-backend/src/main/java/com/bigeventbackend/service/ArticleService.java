package com.bigeventbackend.service;

import com.bigeventbackend.pojo.Article;
import com.bigeventbackend.pojo.PageBean;

public interface ArticleService {
    void add(Article article);

    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);
}
