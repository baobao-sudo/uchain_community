package com.swpu.uchain.community.service.impl;

import com.swpu.uchain.community.dao.ArticleMapper;
import com.swpu.uchain.community.entity.Article;
import com.swpu.uchain.community.enums.ResultEnum;
import com.swpu.uchain.community.exception.GlobalException;
import com.swpu.uchain.community.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public Article findById(Integer id) {
        return articleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Article> findByType(String type) {
        return articleMapper.selectByArticleType(type);
    }

    @Override
    public List<Article> findAll() {
        return articleMapper.selectAll();
    }

    @Override
    public void deleteById(Integer id) {
        if(articleMapper.selectByPrimaryKey(id) == null){
            log.error("该id文章不存在 id={}",id);
            throw new GlobalException(ResultEnum.ARTICLE_NOT_EXIST);
        }
        articleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Article article) {
        articleMapper.updateByPrimaryKey(article);
    }

    @Override
    public void add(Article article) {
        articleMapper.insert(article);
    }
}
