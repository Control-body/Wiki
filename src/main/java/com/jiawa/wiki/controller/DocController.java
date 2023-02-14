package com.jiawa.wiki.controller;

import com.jiawa.wiki.req.DocQueryReq;
import com.jiawa.wiki.req.DocSaveReq;
import com.jiawa.wiki.resp.DocQueryResp;
import com.jiawa.wiki.resp.CommonResp;
import com.jiawa.wiki.resp.PageResp;
import com.jiawa.wiki.service.DocService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

// 在controller层 不要出现真实的 dao实体类
@RestController
@RequestMapping("/doc")
public class DocController {
    @Resource
    private DocService docServce;

    @GetMapping("/all")
    public CommonResp all(){
        CommonResp<List<DocQueryResp>> objectCommonResp = new CommonResp<>();
        List list = docServce.all();
        objectCommonResp.setContent(list);
        return objectCommonResp;
    }
    @GetMapping("/list")
    public CommonResp list(@Valid DocQueryReq req){
        CommonResp<PageResp<DocQueryResp>> objectCommonResp = new CommonResp<>();
        PageResp<DocQueryResp> list = docServce.list(req);
        objectCommonResp.setContent(list);
        return objectCommonResp;
    }

    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody DocSaveReq req){
        CommonResp objectCommonResp = new CommonResp<>();
        docServce.save(req);
        return objectCommonResp;
    }

    @DeleteMapping("/delete/{idsStr}")
    public CommonResp delete(@PathVariable String idsStr){
        CommonResp objectCommonResp = new CommonResp<>();
        String[] split = idsStr.split(",");// 将参数转换成数组
        List<String> strings = Arrays.asList(split);
        docServce.delete(strings);
        return objectCommonResp;
    }

}
