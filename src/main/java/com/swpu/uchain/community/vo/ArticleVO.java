package com.swpu.uchain.community.vo;

import com.swpu.uchain.community.entity.Article;
import lombok.Data;

import java.util.List;

/**
 * @author baobao
 * @date 2019-8-29
 */
@Data
public class ArticleVO {

    private List<Article> home;

    private List<Article> direction;

    private List<Article> achievement;
}
