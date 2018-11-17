package cat.tecnocampus.omega.webControllers;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLDataException;

@ControllerAdvice
public class HandlingExceptionController {
    @Aspect
    @Component
    private class publicLoggerAdvice{
        private final org.slf4j.Logger logger = LoggerFactory.getLogger(publicLoggerAdvice.class);

    }

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(publicLoggerAdvice.class);


    @ExceptionHandler(EmptyResultDataAccessException.class)
    public String handleUsernameDoesNotExist(Model model, HttpServletRequest request, Exception ex) {
        String url = request.getRequestURL().toString();

        logger.error("Request: " + url + " raised " + ex);

        model.addAttribute("username", url.substring(url.lastIndexOf("/") + 1));
        return "error/usernameDoesNotExist";
    }

    @ExceptionHandler({SQLDataException.class,DataAccessException.class})
    public String databaseError(Model model, HttpServletRequest request, Exception ex){
        String url = request.getRequestURL().toString();

        logger.error("Request: " + url + " raised " + ex);

        model.addAttribute("where",url.substring(url.lastIndexOf("/") + 1));
        return "error/databaseException";
    }


}


