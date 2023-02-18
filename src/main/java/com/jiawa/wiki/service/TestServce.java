package com.jiawa.wiki.service;

import com.jiawa.wiki.mapper.TestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TestServce {
    @Resource
    //JDK自带的
    //@Autowired // 自动注入 Spring带的
    private TestMapper testMapper;
//    public List<Test> list(){
//        return testMapper.list();
//    }
}
