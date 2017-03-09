package com.fererlab.controller;

import com.fererlab.model.Article;
import com.fererlab.repository.ArticleRepository;
import com.fererlab.security.annotation.CurrentUser;
import com.fererlab.security.model.AuthenticatedUser;
import org.apache.commons.logging.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = {"/", "/article"})
public class ArticleController {

    private Log log;
    private ArticleRepository repository;

    @Inject
    public ArticleController(Log log, ArticleRepository repository) {
        this.log = log;
        this.repository = repository;
    }

    @RequestMapping(value = {"/", "/article"})
    public ModelAndView index(@CurrentUser AuthenticatedUser user) {
//        Iterable<Article> articleEntities = service.findById(user.getId());
        Iterable<Article> articleEntities = repository.findAll();
        List<ArticleVM> articles = new ArrayList<>();
        articleEntities.forEach(ae -> articles.add(new ArticleVM(ae.getTitle())));
        ModelAndView mv = new ModelAndView("article/index");
        mv.addObject("articles", articles);
        return mv;
    }

}
