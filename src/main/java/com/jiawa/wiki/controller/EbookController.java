package com.jiawa.wiki.controller;

import com.jiawa.wiki.domain.Ebook;
import com.jiawa.wiki.resp.CommonResp;
import com.jiawa.wiki.service.EbookServce;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/ebook")
public class EbookController {
    @Resource
    private EbookServce ebookServce;
    @GetMapping("/list")
    public CommonResp list(){
        CommonResp<List<Ebook>> objectCommonResp = new CommonResp<>();
        List<Ebook> list = ebookServce.list();
        objectCommonResp.setContent(list);
        return objectCommonResp;
    }
}
