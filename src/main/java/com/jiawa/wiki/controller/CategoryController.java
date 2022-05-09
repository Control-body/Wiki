package com.jiawa.wiki.controller;

import com.jiawa.wiki.req.CategoryQueryReq;
import com.jiawa.wiki.req.CategorySaveReq;
import com.jiawa.wiki.resp.CategoryQueryResp;
import com.jiawa.wiki.resp.CommonResp;
import com.jiawa.wiki.resp.PageResp;
import com.jiawa.wiki.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

// 在controller层 不要出现真实的 dao实体类
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Resource
    private CategoryService categoryServce;
    @GetMapping("/all")
    public CommonResp all(){
        CommonResp<List<CategoryQueryResp>> objectCommonResp = new CommonResp<>();
        List list = categoryServce.all();
        objectCommonResp.setContent(list);
        return objectCommonResp;
    }
    @GetMapping("/list")
    public CommonResp list(@Valid CategoryQueryReq req){
        CommonResp<PageResp<CategoryQueryResp>> objectCommonResp = new CommonResp<>();
        PageResp<CategoryQueryResp> list = categoryServce.list(req);
        objectCommonResp.setContent(list);
        return objectCommonResp;
    }

    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody CategorySaveReq req){
        CommonResp objectCommonResp = new CommonResp<>();
        categoryServce.save(req);
        return objectCommonResp;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable(value = "id") Long id){
        CommonResp objectCommonResp = new CommonResp<>();
        categoryServce.delete(id);
        return objectCommonResp;
    }

}
