package com.springboot.blog.payload;

import lombok.Data;

@Data
public class SignUpDTO {
    public String username;
    public String name;
    public String email;
    public String password;

}
