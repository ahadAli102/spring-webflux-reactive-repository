package com.ahad.location.server.response;

public record GenericResponse <T> (
        String responseCode,
        String responseMessage,
        T responseBody
) { }
