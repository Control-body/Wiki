package com.jiawa.wiki.controller;

import com.jiawa.wiki.req.EbookQueryReq;
import com.jiawa.wiki.req.EbookSaveReq;
import com.jiawa.wiki.resp.CommonResp;
import com.jiawa.wiki.resp.EbookQueryResp;
import com.jiawa.wiki.resp.EbookResps;
import com.jiawa.wiki.resp.PageResp;
import com.jiawa.wiki.service.EbookServce;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

// 在controller层 不要出现真实的 dao实体类
@RestController
@RequestMapping("/ebook")
public class EbookController {
    @Resource
    private EbookServce ebookServce;
    @GetMapping("/list")
    public CommonResp list(@Valid EbookQueryReq req){
        CommonResp<PageResp<EbookQueryResp>> objectCommonResp = new CommonResp<>();
        PageResp<EbookQueryResp> list = ebookServce.list(req);
        objectCommonResp.setContent(list);
        return objectCommonResp;
    }

    @GetMapping("/all")
    public CommonResp all(EbookQueryReq req){
        CommonResp<EbookResps> objectCommonResp = new CommonResp<>();
        EbookResps list = ebookServce.all(req);
        objectCommonResp.setContent(list);
        return objectCommonResp;
    }

    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody EbookSaveReq req){
        CommonResp objectCommonResp = new CommonResp<>();
        ebookServce.save(req);
        return objectCommonResp;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable(value = "id") Long id){
        CommonResp objectCommonResp = new CommonResp<>();
        ebookServce.delete(id);
        return objectCommonResp;
    }


}
