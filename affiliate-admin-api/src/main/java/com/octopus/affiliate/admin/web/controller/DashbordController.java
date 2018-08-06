package com.octopus.affiliate.admin.web.controller;

import com.octopus.affiliate.admin.annotation.LoginRequired;
import com.octopus.affiliate.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
@LoginRequired
public class DashbordController {


    @GetMapping("")
    public Object info(User user) {

        Map<String, Integer> data = new HashMap<>();
        data.put("userTotal", 0);
        data.put("goodsTotal", 0);
        data.put("productTotal", 0);
        data.put("orderTotal", 0);

        return data;
    }

}
