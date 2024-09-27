package de.supercode.superbnb.configuration;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalEx {
    @ExceptionHandler(Exception.class)
    public void handling(Exception ex){
        ex.printStackTrace();
    }
}
