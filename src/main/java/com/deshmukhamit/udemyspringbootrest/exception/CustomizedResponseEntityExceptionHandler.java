package com.deshmukhamit.udemyspringbootrest.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<CustomErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(this.getCustomErrorResponse(httpStatus, ex.getLocalizedMessage()), httpStatus);
    }

    private CustomErrorResponse getCustomErrorResponse(HttpStatus httpStatus, String message) {
        return new CustomErrorResponse(httpStatus.getReasonPhrase(), httpStatus.value(), message);
    }
    private CustomErrorResponse getCustomErrorResponse(HttpStatus httpStatus, String message, List<String> details) {
        return new CustomErrorResponse(httpStatus.getReasonPhrase(), httpStatus.value(), message, details);
    }

}
