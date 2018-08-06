package com.octopus.affiliate.admin.web;

import com.google.common.collect.ImmutableSet;
import com.octopus.affiliate.admin.utils.ResponseUtils;
import com.octopus.affiliate.admin.web.json.JsonResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Set;

@ControllerAdvice
public class GlobalResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    /**
     * 需要处理的类型
     */
    private final Set<MediaType> jsonMediaType = new ImmutableSet.Builder<MediaType>()
            .add(MediaType.APPLICATION_JSON)
            .add(MediaType.APPLICATION_JSON_UTF8)
            .build();

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object obj, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> converterType, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        //当类型 不属于 需要处理的包头的时候 直接返回obj
        if (!jsonMediaType.contains(mediaType)) {
            return obj;
        }
        //当类型 是属于需要处理的时候 并且 obj不是ReturnJsonBody的时候 进行格式化处理
        if (obj == null || !(obj instanceof JsonResult)) {
            return ResponseUtils.ok(obj);
        }
        return obj;
    }
}
