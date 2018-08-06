package com.octopus.affiliate.admin.web.controller;

import com.octopus.affiliate.admin.utils.ResponseUtils;
import org.springframework.boot.autoconfigure.web.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class ErrorController extends AbstractErrorController {
    public ErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @RequestMapping(value = "error", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object handlerError(HttpServletRequest request) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(request, true);
        return ResponseUtils.fail((int) errorAttributes.get("status"), (String) errorAttributes.get("message"));
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
