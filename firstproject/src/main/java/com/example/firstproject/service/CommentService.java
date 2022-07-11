package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId) {
//        //조회: 댓글 목록
//        List<Comment>comments = commentRepository.findByArticleId(articleId);
//        //변환: 엔티티->DTO
//        List<CommentDto> dtos = new ArrayList<>();
//        for(int i=0; i<comments.size();i++){
//            Comment c = comments.get(i);
//        //CommentDto dto = c.createDto(c); 내가 처음에 한거 ㅎㅎ;
//            CommentDto dto = CommentDto.createCommentDto(c);
//            dtos.add(dto);
//        }
//        //return:
//        return dtos;
        return commentRepository.findByArticleId(articleId)//조회: 댓글 목록
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
    }

    //create메소드는 DB를 건드리기 때문에 중간에 문제가 생겼을 경우 롤백을 제대로 시켜줘야함
    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {
        //게시글 조회 및 예외 처리
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("Fail to make comment: No article"));
        //댓글 엔티티 생성
        Comment comment = Comment.createComment(dto, article);
        //댓글 엔티티를 DB로 저장
        Comment created = commentRepository.save(comment);
        //DTO로 변경하여 반환
        return CommentDto.createCommentDto(created);
    }

    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        //댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No Such Comment"));
        //댓글 수정
        target.patch(dto);
        /*        target.patch(dto)*/
//        Comment target = Comment.updateComment(dto, comment);
        //DB로 갱신
        Comment updated = commentRepository.save(target);

        //댓글 엔티티를 DTO로 변환 및 반환
        return CommentDto.createCommentDto(updated);
    }

    @Transactional
    public CommentDto delete(Long id) {
        //댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("No Such Comment"));
        //댓글을 DB에서 삭제
        commentRepository.delete(target);
        //삭제 댓글을 DTO로 반환(target을 dto로 반환하는것)
        return CommentDto.createCommentDto(target);
    }
}
//List<Comment>
