package com.springbooot.boardproject.service;

import com.springbooot.boardproject.dto.CommentDto;
import com.springbooot.boardproject.entity.Comment;
import com.springbooot.boardproject.repository.ArticleRepository;
import com.springbooot.boardproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId){
        // 1. 댓글조회 2. 엔티티 -> DTO 변환 및 3. 결과 반환
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(CommentDto::createCommentDto)
                .collect(Collectors.toList());
    }
}
