package com.swpu.uchain.community.service;

import com.swpu.uchain.community.entity.Article;

import java.util.List;

/**
 * @author baobao
 * @date 2019-8-29
 */
public interface ArticleService {

    /**
     * 通过id查找
     * @param id
     * @return
     */
    Article findById(Integer id);

    /**
     * 通过类型查找
     * @param type
     * @return
     */
    List<Article> findByType(String type);

    /**
     * 查找全部
     * @return
     */
    List<Article> findAll();

    /**
     * 通过id删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 更新
     * @param article
     */
    void update(Article article);

    /**
     * 增加
     * @param article
     */
    void add(Article article);

}
