package caveatemptor.utils.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CaveatEmptorExceptionHandler {

    @ExceptionHandler
    public String exceptionHappened(Exception ex, Model model){
        model.addAttribute("message", ex.getMessage());
        return "error";
    }
}
