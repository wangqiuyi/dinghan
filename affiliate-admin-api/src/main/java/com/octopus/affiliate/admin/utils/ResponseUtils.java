package com.octopus.affiliate.admin.utils;

import com.octopus.affiliate.admin.web.json.JsonResult;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtils {
    public static JsonResult ok() {
        JsonResult result = new JsonResult();
        result.setErrno(0);
        result.setErrmsg("成功");
        return result;
    }

    public static JsonResult ok(Object data) {
        JsonResult result = new JsonResult();
        result.setErrno(0);
        result.setErrmsg("成功");
        result.setData(data);
        return result;
    }

    public static JsonResult ok(Object items, Integer total) {
        JsonResult result = new JsonResult();
        result.setErrno(0);
        result.setErrmsg("成功");
        Map<String, Object> data = new HashMap<>();
        data.put("items", items);
        data.put("total", total);
        result.setData(data);
        return result;
    }

    public static Object ok(String errmsg, Object data) {
        JsonResult result = new JsonResult();
        result.setErrno(0);
        result.setErrmsg(errmsg);
        result.setData(data);
        return result;
    }

    public static Object fail() {
        JsonResult result = new JsonResult();
        result.setStatus(false);
        result.setErrno(-1);
        result.setErrmsg("错误");
        return result;
    }

    public static Object fail(int errno, String errmsg) {
        JsonResult result = new JsonResult();
        result.setStatus(false);
        result.setErrno(errno);
        result.setErrmsg(errmsg);
        return result;
    }

    public static Object fail401() {
        return fail(401, "请登录");
    }

    public static Object unlogin() {
        return fail401();
    }

    public static Object fail402() {
        return fail(402, "参数不对");
    }

    public static Object badArgument() {
        return fail402();
    }

    public static Object fail403() {
        return fail(403, "参数值不对");
    }

    public static Object badArgumentValue() {
        return fail403();
    }

    public static Object fail501() {
        return fail(501, "业务不支持");
    }

    public static Object unsupport() {
        return fail501();
    }

    public static Object fail502() {
        return fail(502, "系统内部错误");
    }

    public static Object serious() {
        return fail502();
    }
}

