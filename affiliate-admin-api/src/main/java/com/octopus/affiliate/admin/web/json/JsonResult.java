package com.octopus.affiliate.admin.web.json;

import lombok.Data;

@Data
public class JsonResult {

    private boolean status = true;

    private int errno = 0;

    private String errmsg = "";

    private Object data;
}
