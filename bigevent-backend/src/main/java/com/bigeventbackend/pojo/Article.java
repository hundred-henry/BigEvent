package com.bigeventbackend.pojo;

import com.bigeventbackend.anno.State;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

@Data
public class Article {
    private Integer id;

    @NotEmpty
    @Pattern(regexp = "^\\S{1,15}$")
    @JsonProperty("title")
    private String title;

    @NotEmpty
    @JsonProperty("content")
    private String content;

    @NotEmpty
    @URL
    @JsonProperty("coverImg")
    private String coverImg;

    @State
    @JsonProperty("state")
    private String state;

    @NotNull
    @JsonProperty("categoryId")
    private Integer categoryId;
    private Integer createUser;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
