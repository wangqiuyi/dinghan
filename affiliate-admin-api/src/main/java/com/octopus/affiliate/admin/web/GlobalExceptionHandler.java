package com.octopus.affiliate.admin.web;

import com.octopus.affiliate.admin.web.json.JsonResult;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Level;

@ControllerAdvice
@Log
public class GlobalExceptionHandler {

    /**
     * 系统异常处理，比如：404,500
     *
     * @param req
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonResult defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        log.log(Level.WARNING, e.getMessage());
        JsonResult result = new JsonResult();
        result.setErrmsg(e.getMessage());
        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            result.setErrno(HttpStatus.NOT_FOUND.value());
        } else {
            result.setErrno(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        result.setData(null);
        result.setStatus(false);
        return result;
    }
}
