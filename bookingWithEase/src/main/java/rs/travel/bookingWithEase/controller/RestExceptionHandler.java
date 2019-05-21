package rs.travel.bookingWithEase.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.*;

import rs.travel.bookingWithEase.model.ApiError;
import rs.travel.booking_with_ease.exceptions.*;

// https://www.toptal.com/java/spring-boot-rest-api-error-handling
// https://grokonez.com/spring-framework/spring-mvc/use-restcontrolleradvice-new-features-spring-framework-4-3
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles EntityNotFoundException. Created to encapsulate errors with more detail than javax.persistence.EntityNotFoundException.
     *
     * @param ex the EntityNotFoundException
     * @return the ApiError object
     */
  /*  @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            EntityNotFoundException ex) {
        ApiError apiError = new ApiError(NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }
    */
	
    @ExceptionHandler(EntityAlreadyExistsException.class)
    protected ResponseEntity<Object> handleEntityAlreadyExists(
    		EntityAlreadyExistsException ex) {
       
        ApiError apiError = new ApiError(CONFLICT);
        apiError.setMessage(ex.getMessage());
    
        return buildResponseEntity(apiError);
    }
    
    @ExceptionHandler(EntityNotEditableException.class)
    protected ResponseEntity<Object> handleEntityAlreadyExists(
    		EntityNotEditableException ex) {
       
        ApiError apiError = new ApiError(METHOD_NOT_ALLOWED);
        apiError.setMessage(ex.getMessage());
    
        return buildResponseEntity(apiError);
    }
  
    /**
     * Handle javax.persistence.EntityNotFoundException
     */
    /*
    @ExceptionHandler(javax.persistence.EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(javax.persistence.EntityNotFoundException ex) {
        return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, ex));
    }
*/
  
    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}