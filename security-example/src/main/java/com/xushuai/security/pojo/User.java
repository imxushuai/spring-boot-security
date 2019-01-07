package com.xushuai.security.pojo;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class User {

    public interface UsernameView {
    }

    public interface UserView extends UsernameView {
    }

    private Integer id;

    @NotNull
    @JsonView(UsernameView.class)
    private String username;

    @NotNull(message = "密码不能为空")
    @JsonView(UserView.class)
    private String password;
}
