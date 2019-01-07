package com.xushuai.security.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.xushuai.security.pojo.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.BindingType;

@RestController
public class UserController {


    /**
     * 获取user对象
     *
     * @param id ID并且使用正则表达式限定只能为数字
     * @return User对象
     */
    @GetMapping("/user/{id:\\d+}")
    @JsonView(User.UserView.class)
    public ResponseEntity<User> getUser(@PathVariable String id) {
        User user = new User();
        user.setId(1);
        user.setUsername("baker-1");
        user.setPassword("abc");

        return ResponseEntity.ok(user);
    }


    @GetMapping("/user/username/{id:\\d+}")
    @JsonView(User.UsernameView.class)
    public ResponseEntity<User> getUsername(@PathVariable String id) {
        User user = new User();
        user.setId(2);
        user.setUsername("baker-2");
        user.setPassword("abc2");

        return ResponseEntity.ok(user);
    }

    @PostMapping("/user")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
        }
        user.setId(3);
        // 创建成功返回的状态码为:201
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
