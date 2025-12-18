package com.iugo.demo.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    private String status;
    private String code;
    private String message;
    private T data;
}
