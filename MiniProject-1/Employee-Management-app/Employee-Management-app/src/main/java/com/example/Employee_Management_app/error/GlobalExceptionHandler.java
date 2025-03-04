package com.example.Employee_Management_app.error;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Gobal Exception handler for userdefined exceptions
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Exception handler will catch the error thrown when email already exist
     * @param custom exception name with variable will pass the error message
     * @param model to pass the value to html page
     * @return it will call used enpoint
     */
    @ExceptionHandler(EmailAlreadyExistException.class)
    public String globalHandleForEmailException(EmailAlreadyExistException e, Model model){
        model.addAttribute("message",e.getMessage());
        return"add";
    }

}
