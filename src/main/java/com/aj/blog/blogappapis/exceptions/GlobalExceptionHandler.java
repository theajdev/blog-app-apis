package com.aj.blog.blogappapis.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.aj.blog.blogappapis.payloads.ApiResponse;
import com.aj.blog.blogappapis.payloads.InvalidUser;
import com.aj.blog.blogappapis.payloads.ResourceAlreadyExist;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {

        String message = ex.getMessage();
        Integer userId = (int) ex.getFieldValue();
        ApiResponse apiResponse = new ApiResponse(message, false, userId);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> resp = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String filedName = ((FieldError) error).getField();
            String msg = error.getDefaultMessage();
            resp.put(filedName, msg);
        });

        return new ResponseEntity<Map<String, String>>(resp, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(InvalidUserException.class)
    public ResponseEntity<InvalidUser> InvalidUserExceptionHandler(InvalidUserException ex) {

        String message = ex.getMessage();
        InvalidUser apiResponse = new InvalidUser(message,400,HttpStatus.BAD_REQUEST);
        return new ResponseEntity<InvalidUser>(apiResponse, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ResourceAlreadyExist> ResourceAlreadyExistsException(ResourceAlreadyExistsException ex){
    	String message=ex.getMessage();
    	ResourceAlreadyExist alreadyExists=new ResourceAlreadyExist(message);
    	return new ResponseEntity<ResourceAlreadyExist>(alreadyExists,HttpStatus.BAD_REQUEST);
    }
}
