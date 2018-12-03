package cat.tecnocampus.omega.webControllers;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLDataException;

@Controller
@ControllerAdvice
public class HandlingExceptionController {
    @Aspect
    @Component
    private class publicLoggerAdvice{
        private final org.slf4j.Logger logger = LoggerFactory.getLogger(publicLoggerAdvice.class);

    }

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(publicLoggerAdvice.class);

    @ExceptionHandler(EmptyResultDataAccessException.class)
    @GetMapping("usernameDoesNotExist")
    public String handleUsernameDoesNotExist(Model model, HttpServletRequest request, Exception ex) {
        String url = request.getRequestURL().toString();

        logger.error("Request: " + url + " raised " + ex);

        model.addAttribute("username", url.substring(url.lastIndexOf("/") + 1));
        return "error/usernameDoesNotExist";
    }

    @ExceptionHandler({SQLDataException.class,DataAccessException.class})
    @GetMapping("databaseException")
    public String databaseError(Model model, HttpServletRequest request, Exception ex){
        String url = request.getRequestURL().toString();

        logger.error("Request: " + url + " raised " + ex);

        model.addAttribute("where",url.substring(url.lastIndexOf("/") + 1));
        return "error/databaseException";
    }

    @ExceptionHandler(NullPointerException.class)
    @GetMapping("exceptionNullPointer")
    public String ExceptionNullPointer(Model model, HttpServletRequest request, Exception ex){
        String url = request.getRequestURL().toString();

        logger.error("Request: " + url + " raised " + ex);

        model.addAttribute("where",url.substring(url.lastIndexOf("/") + 1));
        return "error/exceptionNullPointer";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @GetMapping("ilegalArgumentError")
    public String IlegalArgument(Model model, HttpServletRequest request, Exception ex){
        String url = request.getRequestURL().toString();

        logger.error("Request: " + url + " raised " + ex);

        model.addAttribute("where",url.substring(url.lastIndexOf("/") + 1));
        return "error/ilegalArgumentError";
    }

    @ExceptionHandler(ConversionFailedException.class)
    @GetMapping("conversionException")
    public String conversionFailed(Model model, HttpServletRequest request, Exception ex){
        String url = request.getRequestURL().toString();

        logger.error("Request: " + url + " raised " + ex);

        model.addAttribute("where",url.substring(url.lastIndexOf("/") + 1));

        return"error/conversionException";
    }

    @ExceptionHandler(Exception.class)
    @GetMapping("errorAll")
    public String ExceptionAll(Model model, HttpServletRequest request, Exception ex){
        String url = request.getRequestURL().toString();

        logger.error("Request: " + url + " raised " + ex);

        model.addAttribute("where2",ex.getMessage());
        return "error/exceptionAll";

    }
  /*  @ExceptionHandler(MissingPathVariableException.class)
    public String MisingVariablePathError(Model model, HttpServletRequest request, Exception ex){


        return "error/M";
    }*/
}


