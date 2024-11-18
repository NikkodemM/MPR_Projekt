package pl.edu.pjatk.MPR_Projekt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class CatExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler (value = {CatNotFoundException.class})
    public ResponseEntity<Object> handleCatNotFound(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler (value = {CatAlreadyExistException.class})
    public ResponseEntity<Object> handleCatAlreadyExist(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
