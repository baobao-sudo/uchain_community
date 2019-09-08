package com.swpu.uchain.community.dao;

import com.swpu.uchain.community.entity.Article;
import java.util.List;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer articleId);

    int insert(Article record);

    Article selectByPrimaryKey(Integer articleId);

    List<Article> selectAll();

    int updateByPrimaryKey(Article record);

    List<Article> selectByArticleType(String type);
}