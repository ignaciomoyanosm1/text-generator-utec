package com.iugo.demo.exception;

public class ContentGenerationException extends BusinessException {
    public ContentGenerationException(String message) { super(message); }
    public ContentGenerationException(String message, Throwable cause) { super(message, cause); }
}