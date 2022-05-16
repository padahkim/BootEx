package com.example.firstproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



@Entity //DB해당 객체를 인식 가능
@ToString
@AllArgsConstructor
@NoArgsConstructor//Default 생성자추가
@Getter
public class Article {

    @Id// id값을 넣어줌
    @GeneratedValue // 1,2,3....자동 생성
    private Long id;
    @Column
    private String title;
    @Column
    private String content;
}
