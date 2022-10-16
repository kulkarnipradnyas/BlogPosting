package com.springboot.blog.payload;

import com.springboot.blog.entity.Post;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CommentDto {
    private long id;

    @NotEmpty(message = "Name should not be empty")
    private String name;

    @Email
    @NotEmpty(message = "Email should not be null or empty")
    private String email;

    @NotEmpty(message =  "body should not be null or empty")
    @Size(min=10, message = "Message should be minimum 10 size")
    private String body;
//    private Post post;

}
