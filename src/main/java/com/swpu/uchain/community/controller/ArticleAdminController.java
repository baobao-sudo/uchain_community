package com.swpu.uchain.community.controller;

import com.swpu.uchain.community.entity.Article;
import com.swpu.uchain.community.exception.GlobalException;
import com.swpu.uchain.community.service.ArticleService;
import com.swpu.uchain.community.util.ResultVOUtil;
import com.swpu.uchain.community.vo.ArticleVO;
import com.swpu.uchain.community.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/admin/article")
public class ArticleAdminController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/list")
    public ResultVO list(){
        List<Article> articleList = articleService.findAll();
        ArticleVO articleVO = new ArticleVO();
        List<Article> home = new ArrayList<>();
        List<Article> direction = new ArrayList<>();
        List<Article> achievement = new ArrayList<>();
        for(Article article:articleList){
            if("home".equals(article.getArticleType())){
                home.add(article);
            }else if("achievement".equals(article.getArticleType())){
                achievement.add(article);
            }else {
                direction.add(article);
            }
        }
        articleVO.setHome(home);
        articleVO.setAchievement(achievement);
        articleVO.setDirection(direction);
        return ResultVOUtil.success(articleVO);
    }

    @PostMapping("/delete")
    public ResultVO delete(@RequestParam("id") Integer id){
        try {
            articleService.deleteById(id);
        }catch (GlobalException e){
            return ResultVOUtil.error(e.getResultEnum());
        }
        return ResultVOUtil.success();
    }

    @PostMapping("/add")
    public ResultVO add(@RequestBody Article article){
        articleService.add(article);
        return ResultVOUtil.success();
    }

    @PostMapping("/change")
    public ResultVO change(@RequestBody Article article){
        articleService.update(article);
        return ResultVOUtil.success();
    }

}
