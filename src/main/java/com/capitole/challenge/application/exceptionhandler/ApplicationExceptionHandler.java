package com.capitole.challenge.application.exceptionhandler;

import com.capitole.challenge.delivery.dto.response.ApiErrorDto;
import com.capitole.challenge.domain.exception.ProductPriceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String NOT_FOUND_CODE = "not.found";

    @ExceptionHandler(value = {ProductPriceNotFoundException.class})
    public ResponseEntity<ApiErrorDto> handleProductPriceNotFoundException(
            ProductPriceNotFoundException nfe,
            WebRequest request) {
        ApiErrorDto error = new ApiErrorDto(NOT_FOUND_CODE, nfe.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(error);
    }
}
