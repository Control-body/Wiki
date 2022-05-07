package com.jiawa.wiki.service;

import com.jiawa.wiki.domain.Ebook;
import com.jiawa.wiki.mapper.EbookMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookServce {
    @Resource
    //JDK自带的
    //@Autowired // 自动注入 Spring带的
    private EbookMapper ebookMapper;
    public List<Ebook> list(){
        return ebookMapper.selectByExample(null);
    }
}
