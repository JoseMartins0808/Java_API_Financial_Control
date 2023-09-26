package com.br.martins.learningspringboot.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid (final MethodArgumentNotValidException error,
        final HttpHeaders headers, final HttpStatusCode statusCode, final WebRequest webRequest) {

        final HashMap<String, Object> returningObjectError = new HashMap<String, Object>();

        final HashMap<String, String> allErrorsObject = new HashMap<String, String>();

        final List<ObjectError> allErrorsList = error.getAllErrors();

        allErrorsList.forEach((err) -> {
            if (err instanceof FieldError) {
                allErrorsObject.put(((FieldError) err).getField(), err.getDefaultMessage());
            } else {
                allErrorsObject.put(err.getObjectName(), err.getDefaultMessage());
            }
        });

        returningObjectError.put("message", allErrorsObject);

        return new ResponseEntity<Object> (returningObjectError, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({AppException.class})
    public ResponseEntity<Object> handleAppError (final AppException error) {

        final HashMap<String, String> returningErrorObject = new HashMap<String, String>();
        returningErrorObject.put("message", error.getMessage());

        return new ResponseEntity<Object>(returningErrorObject, error.getStatusCode());
    }


    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleInternalError (final RuntimeException error) {

        final HashMap<String, String> returningErrorObject = new HashMap<String, String>();
        returningErrorObject.put("message", "Internal server error");

        System.out.println(error.getMessage());

        return new ResponseEntity<Object>(returningErrorObject, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
