package mini.project.miniproject01.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(value = CustomException.class)
    public final ResponseEntity<String> handleCustomException(CustomException exception){

        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(value = AuthFail.class)
    public final ResponseEntity<String> handleAuthFailException(AuthFail exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
