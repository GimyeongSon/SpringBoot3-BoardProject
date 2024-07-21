package com.springbooot.boardproject.dto;

import com.springbooot.boardproject.entity.Article;
import lombok.Data;

@Data
public class ArticleForm {
    private String title;
    private String content;

    public Article toEntity(){
        return new Article(null, title, content);
    }
}
