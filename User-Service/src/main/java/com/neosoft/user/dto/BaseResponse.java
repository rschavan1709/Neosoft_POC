package com.neosoft.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BaseResponse<T> {
    private Integer code;
    private String message;
    private T data;
    private T error;
}
