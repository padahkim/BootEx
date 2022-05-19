package com.example.firstproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;


@Entity //DB해당 객체를 인식 가능(해당 클래스로 테이블을 만든다)
@ToString
@AllArgsConstructor
@NoArgsConstructor//Default 생성자추가
@Getter
public class Article {

    @Id// id값을 넣어줌
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 1,2,3....자동 생성
    private Long id;
    @Column
    private String title;
    @Column
    private String content;

    public void patch(Article article) {
        if(article.title != null)
            this.title = article.title;
        if(article.content != null)
            this.content = article.content;
    }
}
