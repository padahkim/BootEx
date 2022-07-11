package com.example.firstproject.entity;

import com.example.firstproject.dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // 해당 댓글 엔티티 여러개가, 하나의 Article에 연관된다!
    @JoinColumn(name = "article_id")
    private Article article;

    @Column
    private String nickname;
    @Column
    private String body;


//    CommentDto를 Comment로 변환하는 메소드(createComment)를 Comment 클래스에 만드셨는데
//
//    Dto 클래스에서 만들어도 상관은 없을까요 ?
//
//    //Dto 클래스
//    class CommentDto{
//        /*
//          해당 Dto 클래스 객체에는 JSON으로 요청받은 데이터가 들어있음
//         */
//
//        public Comment toEntity(Article article){
//            // 예외 처리 코드 추가
//
//            return Comment.builder()
//                    .nickname(this.nickname)
//                    .body(this.body)
//                    .article(article)
//                    .build();
//        }
//    }
//
//    // 호출
//    Comment comment = requestDto.toEntity(article);
//
//    이런 식으로 CommentDto에 toEntity()라는 메소드를 만들어서 Comment 클래스 타입으로 변환하도록 해도 상관은 없는거죠 ?
//    네, 두 방법 모두 가능합니다.
//
//    개인적으로는
//    다음과 같이 클래스 메소드 호출로
//    변환하는걸 선호합니다.
//    Comment comment = Comment.createEntity(commentDto);



    // 클래스 메소드를 통한 엔티티 생성
    public static Comment createComment(CommentDto dto, Article article) {
        //예외 발생
        if (dto.getId() != null)//이거 살짝 이해가 안됨...댓글의 id가 있다는 말은 이미 존재하는 댓글이니까 create가 안된다는 말 같음
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다");
        if (dto.getArticleId() != article.getId())
            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못되었습니다");
        //엔티티 생성 및 반환
        return new Comment(
                dto.getId(),
                article,
                dto.getNickname(),
                dto.getBody()
        );
    }

    //수정을 위한 인스턴스 메서드
    public void patch(CommentDto dto) {
        //예외 발생this.id는 controller @pathVariable로 받아온 id를
        //service로 보내 commentRepository에서 가져온 entity가 가지고 있는 id값 = 즉 url 에서 던진id
            if(this.id!=dto.getId())
                throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id가 입력 되었습니다.");
        //객체를 갱신
            if(dto.getNickname() != null)
                this.nickname=dto.getNickname();
            if(dto.getBody() != null)
                this.body=dto.getBody();

    }
}