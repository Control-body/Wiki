package com.jiawa.wiki.controller;

import com.jiawa.wiki.req.EbookReq;
import com.jiawa.wiki.resp.CommonResp;
import com.jiawa.wiki.resp.EbookResp;
import com.jiawa.wiki.service.EbookServce;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
// 在controller层 不要出现真实的 dao实体类
@RestController
@RequestMapping("/ebook")
public class EbookController {
    @Resource
    private EbookServce ebookServce;
    @GetMapping("/list")
    public CommonResp list(EbookReq req){
        CommonResp<List<EbookResp>> objectCommonResp = new CommonResp<>();
        List<EbookResp> list = ebookServce.list(req);
        objectCommonResp.setContent(list);
        return objectCommonResp;
    }
}
