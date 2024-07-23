package com.springbooot.boardproject.service;

import com.springbooot.boardproject.dto.ArticleForm;
import com.springbooot.boardproject.entity.Article;
import com.springbooot.boardproject.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class ArticleService {
    private ArticleRepository articleRepository;

    public List<Article> index(){
        return articleRepository.findAll();
    }

    public Article show(Long id){
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        // 1. DTO -> 엔티티 변환하기
        Article articleEntity = dto.toEntity();
        // 2. 타깃 조회하기
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        // 3. 잘못된 요청 처리하기
        if(target == null || id != articleEntity.getId()){
            log.info("잘못된 요청! id: {}, article: {}", id, articleEntity.toString());
            return null;
        }
        // 일부 데이터만 수정시 null로 되는것을 막기위해서
        target.fetch(articleEntity);
        Article update = articleRepository.save(target);
        return update;
    }

    public Article delete(Long id) {
        // 1. 대상 찾기
        Article article = articleRepository.findById(id).orElse(null);
        // 2. 잘못된 요청 처리하기
        if(article == null || id != article.getId()){
            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
            return null;
        }
        // 3. 대상 삭제하기
        return article;
    }
}
