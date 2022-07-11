package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest //리파지토리를 테스트 하는것이므로, JPA와 연동한 테스트
class CommentRepositoryTest {
    @Autowired CommentRepository commentRepository;

    //DiaplayName 이 테스트를 어떻게 보여줄거냐(테스트 결과에 보여줄 이름)
    @Test
    //테스트 결과에 보여줄 이름
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {
        /* Case 1: 4번 게시글의 모든 댓글 조회*/
        {
            // 입력 데이터 준비
                Long articleId = 4L;
            // 실제 수행
               List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 예상하기
            Article article = new Article(4L, "당신의 인생영화는","댓글ㄱ");
            Comment a = new Comment(1L,article,"Park","굿 윌 헌팅");
            Comment b = new Comment(2L,article,"Kim","아이 엠 샘");
            Comment c = new Comment(3L,article,"Choi","쇼생크탈출");

            List<Comment> expected = Arrays.asList(a,b,c);

            // 검증

            assertEquals(expected.toString(), comments.toString(),"4번 글의 모든 댓글을 출력!");
        }
    }

    /* Case 2: 1번 게시글의 모든 댓글 조회 (빈 상태로 만들어질것임 1번글을 댓글이 없음)*/

    /* Case 3: 9번 게시글의 모든 댓글 조회 (빈 상태로 만들어질것임 1번글을 댓글이 없음)*/

    /* Case 4: 9999번 게시글의 모든 댓글 조회 (빈 상태로 만들어질것임 1번글을 댓글이 없음)*/

    /* Case 5: -1번 게시글의 모든 댓글 조회 (빈 상태로 만들어질것임 1번글을 댓글이 없음)*/
    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {
        /* Case 1: "Park"의 모든 댓글 조회*/{

            //입력 데이터 준비
            String nickname="Park";
            // 실제 수행
              List<Comment> comments  = commentRepository.findByNickname(nickname);
            // 예상하기

            // 검증

            /* Case 2: "Kim"의 모든 댓글 조회*/
            /* Case 3: null의 모든 댓글 조회*/
            /* Case 4: ""의 모든 댓글 조회*/
            /* Case 5: "i"의 모든 댓글 조회*/

        }
    }
}