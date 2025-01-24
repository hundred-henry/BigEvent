package com.bigevent.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    //  快速返回操作成功响应结果（带响应数据）
    public static <E> Result<E> success(E data) {
        return new Result<>(0, "Operation successful", data);
    }

    // 快速返回操作成功响应结果
    public static Result success() {
        return new Result(0, "Operation successful", null);
    }

    public static Result error(String message) {
        return new Result(1, message, null);
    }
}
