package com.octopus.affiliate.admin.web.controller;

import com.octopus.affiliate.admin.annotation.LoginRequired;
import com.octopus.affiliate.admin.dao.UserDAO;
import com.octopus.affiliate.admin.db.model.AffiliateUser;
import com.octopus.affiliate.admin.service.UserService;
import com.octopus.affiliate.admin.utils.JacksonUtils;
import com.octopus.affiliate.admin.utils.ResponseUtils;
import com.octopus.affiliate.admin.utils.UserToken;
import com.octopus.affiliate.admin.utils.UserTokenUtils;
import com.octopus.affiliate.model.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserService userService;

    @GetMapping("/info")
    @LoginRequired
    public Object info(User user) {
        return user;
    }

    @GetMapping("/list")
    @LoginRequired
    public Object list(User user, @RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "limit", defaultValue = "10") int size) {

        List<AffiliateUser> userList = userService.querySelective(page - 1, size);
        int total = userService.countSelective();

        return ResponseUtils.ok(userList, total);
    }

    /*
     *  { username : value, password : value }
     */
    @PostMapping("/login")
    public Object login(@RequestBody String body) {
        String username = JacksonUtils.parseString(body, "username");
        String password = JacksonUtils.parseString(body, "password");

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return ResponseUtils.badArgument();
        }

        List<User> userList = userDAO.getUsersByEmail(username);
        Assert.state(userList.size() < 2, "同一个用户名存在两个账户");
        if (userList.size() == 0) {
            return ResponseUtils.badArgumentValue();
        }
        User user = userList.get(0);

        if (!StringUtils.equals(DigestUtils.md5Hex(password), user.getPassword())) {
            return ResponseUtils.fail(403, "账号密码不对");
        }

        Integer userId = user.getId();
        // token
        UserToken userToken = UserTokenUtils.generateToken(userId);

        return ResponseUtils.ok(userToken.getToken());
    }

    /*
     *
     */
    @PostMapping("/logout")
    public Object login(User user) {
        if (user == null) {
            return ResponseUtils.unlogin();
        }

        return ResponseUtils.ok();
    }
}
