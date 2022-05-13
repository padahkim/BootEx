package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j//로깅을 위한 @
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        log.info(form.toString());

        //1. Dto를 변환
        Article article = form.toEntity();
        log.info(article.toString());

        //2. Repository에게 Entity를 DB안에 저장하게 함
         Article saved = articleRepository.save(article);
         log.info(saved.toString());
        return"";
    }
    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id = " + id);

        // 1: get data from id
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // 2: enroll data to model
        model.addAttribute("article", articleEntity);
        // 3: showing view page
        return "articles/show";
    }
}
