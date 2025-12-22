package com.iugo.demo.exception;

public final class ErrorMessages {
    private ErrorMessages() {}

    public static final String VALIDATION_ERROR = "La request contiene errores de validación.";
    public static final String JSON_INVALID = "El JSON es inválido o hay un campo con tipo/formato incorrecto.";
    public static final String UNEXPECTED_ERROR = "Ocurrió un error inesperado.";

    public static final String GENERATION_FAILED_EMAIL = "No se pudo generar el email para proveedor.";
    public static final String GENERATION_FAILED_MEMO = "No se pudo generar el memo.";
}