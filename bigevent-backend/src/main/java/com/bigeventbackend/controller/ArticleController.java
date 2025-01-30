package com.bigeventbackend.controller;

import com.bigeventbackend.pojo.Article;
import com.bigeventbackend.pojo.PageBean;
import com.bigeventbackend.pojo.Result;
import com.bigeventbackend.service.ArticleService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public Result add(@RequestBody @Validated Article article) {
        articleService.add(article);
        return Result.success();
    }

    @GetMapping
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String state
    ) {
        PageBean<Article> pb = articleService.list(pageNum, pageSize, categoryId, state);
        return Result.success(pb);
    }

    @GetMapping("/detail")
    public Result<Article> detail(@NotNull final Integer id) {
        final Article article = articleService.findById(id);
        return Result.success(article);
    }

    @PutMapping
    public Result<String> update(@RequestBody  Article article) {
        articleService.update(article);
        return Result.success();
    }

    @DeleteMapping
    public Result<String> delete(@NotNull final Integer id) {
        articleService.delete(id);
        return Result.success();
    }
}
