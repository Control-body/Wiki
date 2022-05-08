package com.jiawa.wiki.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiawa.wiki.domain.Ebook;
import com.jiawa.wiki.domain.EbookExample;
import com.jiawa.wiki.mapper.EbookMapper;
import com.jiawa.wiki.req.EbookQueryReq;
import com.jiawa.wiki.req.EbookSaveReq;
import com.jiawa.wiki.resp.EbookQueryResp;
import com.jiawa.wiki.resp.EbookResps;
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
public class EbookServce {
    private static final Logger Log= LoggerFactory.getLogger(EbookServce.class);

    @Resource
    //JDK自带的
    //@Autowired // 自动注入 Spring带的
    private EbookMapper ebookMapper;

    @Resource
    private SnowFlake snowFlake;
    public PageResp<EbookQueryResp> list(EbookQueryReq req){
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        if(!ObjectUtils.isEmpty(req.getName()))
        criteria.andNameLike("%"+ req.getName()+"%%"); // 模糊匹配查询条件

        PageHelper.startPage(req.getPage(), req.getSize());
        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);
        PageInfo<Ebook> pageInfo = new PageInfo<>(ebookList);


        Log.info("总行数:{}",pageInfo.getTotal());
        Log.info("总页数:{}",pageInfo.getPages());

        List<EbookQueryResp> respList = CopyUtil.copyList(ebookList, EbookQueryResp.class);// 后面是泛型 里面的类
        PageResp<EbookQueryResp> pageResp= new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(respList);
        return pageResp;
    }

    public EbookResps all(EbookQueryReq req) {

        List<Ebook> ebookList = ebookMapper.selectByExample(null);
        List<EbookQueryResp> respList = CopyUtil.copyList(ebookList, EbookQueryResp.class);
        EbookResps ebookResps = new EbookResps();
        ebookResps.setList(respList);
        return ebookResps;
    }
    // 保存电子书  保存有两种 一种是 编辑更新 一种是直接 插入 区别就是 看有没有id 这个值
    public void save(EbookSaveReq req) {
        Ebook ebook=CopyUtil.copy(req,Ebook.class);

        if(ObjectUtils.isEmpty(req.getId())){
            // 空的话 就是去新增值
            long l = snowFlake.nextId();
            //给设置值
            ebook.setId(l);
            ebookMapper.insert(ebook);
        }else{
            //新增
            ebookMapper.updateByPrimaryKey(ebook);
        }

    }

    public void delete(Long id) {
        ebookMapper.deleteByPrimaryKey(id);
    }
}
