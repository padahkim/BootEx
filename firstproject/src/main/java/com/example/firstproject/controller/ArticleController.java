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

import java.util.List;

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
        return "redirect:/articles/" + saved.getId();
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

    @GetMapping("/articles")
    public String index(Model model){
        // 1: bring all articles
        List<Article> articleEntityList = articleRepository.findAll();
        // 2: sending bringing Articles bundle
        model.addAttribute("articleList", articleEntityList);
        // 3: setting view page
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        // bringing edit data
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // enroll data to model
        model.addAttribute("article", articleEntity);
        // view page setting
        return"articles/edit";
    }
    @PostMapping("/articles/update")
    public String update(ArticleForm form){
        log.info(form.toString());

        // 1: DTO -> Entity
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());

        // 2: Entity (save)-> DB
        // 2-1: DB에서 기존 데이터를 가져온다
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        // 2-2: 기존 데이터가 있다면! 값을 갱신한다!
        if(target!=null){
            articleRepository.save(articleEntity);  //엔티티가 DB로 갱신
        }

        // 3: redirect to edit result page
        return "redirect:/articles/" + articleEntity.getId();
    }
}
