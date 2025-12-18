package com.iugo.demo.exception;

import lombok.*;

import java.time.Instant;
import java.util.Map;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private String status;
    private String code;
    private String message;
    private Map<String, String> fieldErrors;
    private Instant timestamp;
}
