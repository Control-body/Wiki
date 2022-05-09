package com.jiawa.wiki.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiawa.wiki.domain.Category;
import com.jiawa.wiki.domain.CategoryExample;
import com.jiawa.wiki.mapper.CategoryMapper;
import com.jiawa.wiki.req.CategoryQueryReq;
import com.jiawa.wiki.req.CategorySaveReq;
import com.jiawa.wiki.resp.CategoryQueryResp;
import com.jiawa.wiki.resp.PageResp;
import com.jiawa.wiki.utils.CopyUtil;
import com.jiawa.wiki.utils.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryService {
    private static final Logger Log= LoggerFactory.getLogger(CategoryService.class);

    @Resource
    //JDK自带的
    //@Autowired // 自动注入 Spring带的
    private CategoryMapper categoryMapper;

    @Resource
    private SnowFlake snowFlake;
    public PageResp<CategoryQueryResp> list(CategoryQueryReq req){
        CategoryExample categoryExample = new CategoryExample();
        CategoryExample.Criteria criteria = categoryExample.createCriteria();
        PageHelper.startPage(req.getPage(), req.getSize());

        categoryExample.setOrderByClause("sort asc");  // 排序
        if(!ObjectUtils.isEmpty(req.getName()))
            criteria.andNameLike("%"+ req.getName()+"%%"); // 模糊匹配查询条件

        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);
        PageInfo<Category> pageInfo = new PageInfo<>(categoryList);


        Log.info("总行数:{}",pageInfo.getTotal());
        Log.info("总页数:{}",pageInfo.getPages());

        List<CategoryQueryResp> respList = CopyUtil.copyList(categoryList, CategoryQueryResp.class);// 后面是泛型 里面的类
        PageResp<CategoryQueryResp> pageResp= new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(respList);
        return pageResp;
    }
    // 保存电子书  保存有两种 一种是 编辑更新 一种是直接 插入 区别就是 看有没有id 这个值
    public void save(CategorySaveReq req) {
        Category category=CopyUtil.copy(req,Category.class);

        if(ObjectUtils.isEmpty(req.getId())){
            // 空的话 就是去新增值
            long l = snowFlake.nextId();
            //给设置值
            category.setId(l);
            categoryMapper.insert(category);
        }else{
            //新增
            categoryMapper.updateByPrimaryKey(category);
        }

    }

    public void delete(Long id) {
        categoryMapper.deleteByPrimaryKey(id);
    }

    public List<CategoryQueryResp> all(){
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.setOrderByClause("sort asc");
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);
        List<CategoryQueryResp> respList = CopyUtil.copyList(categoryList, CategoryQueryResp.class);// 后面是泛型 里面的类
        return respList;
    }
}
