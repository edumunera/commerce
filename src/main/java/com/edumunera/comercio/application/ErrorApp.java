package com.edumunera.comercio.application;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorApp {

    INVALID_ARGUMENT("Invalid_Argument_001", "Handle Method Argument Not Valid Exception."),
    PRICE_NOT_FOUND("Price_Not_Found_001", "Handle Price Not Found."),
    JSON_PROCESSING_ERROR("JSON_Processing_Error_001", "Handle Error Processing JSON.");

    private final String code;
    private final String msg;

}
