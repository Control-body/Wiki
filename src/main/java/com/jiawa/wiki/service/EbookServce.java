package com.jiawa.wiki.service;

import com.jiawa.wiki.domain.Ebook;
import com.jiawa.wiki.domain.EbookExample;
import com.jiawa.wiki.mapper.EbookMapper;
import com.jiawa.wiki.req.EbookReq;
import com.jiawa.wiki.resp.EbookResp;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class EbookServce {
    @Resource
    //JDK自带的
    //@Autowired // 自动注入 Spring带的
    private EbookMapper ebookMapper;
    public List<EbookResp> list(EbookReq req){
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        criteria.andNameLike("%"+ req.getName()+"%%"); // 模糊匹配查询条件
        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);
        List<EbookResp> respList=new ArrayList<>();
        for(Ebook ebook: ebookList){
            EbookResp ebookResp = new EbookResp();
            BeanUtils.copyProperties(ebook,ebookResp);
         //   ebookResp.setId(123L);
            respList.add(ebookResp);

        }
        return respList;
    }
}
