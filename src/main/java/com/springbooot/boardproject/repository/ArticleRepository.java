package com.springbooot.boardproject.repository;

import com.springbooot.boardproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
}
