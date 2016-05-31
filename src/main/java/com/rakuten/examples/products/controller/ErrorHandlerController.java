package com.rakuten.examples.products.controller;

import com.rakuten.examples.products.service.ProductNotFoundException;
import com.rakuten.examples.products.service.ProductServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class ErrorHandlerController {
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String productNotFound(ProductNotFoundException pnfe) {
        return pnfe.getMessage();
    }

    @ExceptionHandler(ProductServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String productServiceError(ProductServiceException pse) {
        return pse.getMessage();
    }
}
