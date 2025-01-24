package com.bigevent.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Article {
    private Integer id;
    private String title;
    private String content;
    private String coverImg;
    private String state;
    private Integer categoryId;  // ID of category
    private Integer createUser;  // ID of creator
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
