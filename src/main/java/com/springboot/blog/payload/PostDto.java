package com.springboot.blog.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class PostDto {

    private long id;
    @NotEmpty
    @Size(min = 10 ,message = "post description should have minimum 10 characters")
    private String description;
    @NotEmpty
    @Size(min = 2, message = "post title should have minimum 2 characters")
    private String title;
    private String content;
    private Set<CommentDto> comments;
}
