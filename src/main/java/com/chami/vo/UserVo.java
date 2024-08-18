package com.chami.vo;

import lombok.Data;

import java.util.Date;

@Data
public class UserVo {
    private int userId;
    private String username;
    private String password;
    private String name;
    private String email;
    private String copNm;
    private String deptNm;
    private String role;
    private Date create_at;
    private Date updated_at;
    private char use_yn;
}

