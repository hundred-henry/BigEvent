package com.bigeventbackend.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

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

    // ✅ 修正泛型问题，指定 `Result<Void>`
    public static Result success() {
        return new Result(0, "Operation successful", null);
    }

    // ✅ 修正泛型问题，指定 `Result<Void>`
    public static Result error(String message) {
        return new Result(1, message, null);
    }
}
