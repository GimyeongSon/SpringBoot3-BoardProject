package com.springbooot.boardproject.api;

import com.springbooot.boardproject.dto.ArticleForm;
import com.springbooot.boardproject.entity.Article;
import com.springbooot.boardproject.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
public class ArticleApiController {
    private final ArticleRepository articleRepository;

    //GET
    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleRepository.findAll();
    }
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){
        return articleRepository.findById(id).orElse(null);
    }
    //POST
    @PostMapping("/api/articles") // rest api 에서는 requestbody로 받아야 dto가 저장된다.
    public Article create(@RequestBody ArticleForm dto){
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }

    //PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto){
        // 1. DTO -> 엔티티 변환하기
        Article articleEntity = dto.toEntity();
        // 2. 타깃 조회하기
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        // 3. 잘못된 요청 처리하기
        if(target == null || id != articleEntity.getId()){
            log.info("잘못된 요청! id: {}, article: {}", id, articleEntity.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        // 일부 데이터만 수정시 null로 되는것을 막기위해서
        target.fetch(articleEntity);
        Article update = articleRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(update);
    }

    //DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        // 1. 대상 찾기
        Article article = articleRepository.findById(id).orElse(null);
        // 2. 잘못된 요청 처리하기
        if(article == null || id != article.getId()){
            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        // 3. 대상 삭제하기
        articleRepository.delete(article);
        // body에 보낼 값이없다면 body(null)이나 build()를 반환한다.
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
