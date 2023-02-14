package com.jiawa.wiki.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiawa.wiki.domain.Content;
import com.jiawa.wiki.domain.Doc;
import com.jiawa.wiki.domain.DocExample;
import com.jiawa.wiki.mapper.ContentMapper;
import com.jiawa.wiki.mapper.DocMapper;
import com.jiawa.wiki.req.DocQueryReq;
import com.jiawa.wiki.req.DocSaveReq;
import com.jiawa.wiki.resp.DocQueryResp;
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
public class DocService {
    private static final Logger Log= LoggerFactory.getLogger(DocService.class);

    @Resource
    //JDK自带的
    //@Autowired // 自动注入 Spring带的
    private DocMapper docMapper;
    /*当插入内容的时候,两个表同时插入*/
    @Resource
    private ContentMapper contentMapper;
    @Resource
    private SnowFlake snowFlake;
    public PageResp<DocQueryResp> list(DocQueryReq req){
        DocExample docExample = new DocExample();
        DocExample.Criteria criteria = docExample.createCriteria();
        PageHelper.startPage(req.getPage(), req.getSize());

        docExample.setOrderByClause("sort asc");  // 排序
        if(!ObjectUtils.isEmpty(req.getName()))
            criteria.andNameLike("%"+ req.getName()+"%%"); // 模糊匹配查询条件
        List<Doc> docList = docMapper.selectByExample(docExample);
        PageInfo<Doc> pageInfo = new PageInfo<>(docList);


        Log.info("总行数:{}",pageInfo.getTotal());
        Log.info("总页数:{}",pageInfo.getPages());

        List<DocQueryResp> respList = CopyUtil.copyList(docList, DocQueryResp.class);// 后面是泛型 里面的类
        PageResp<DocQueryResp> pageResp= new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(respList);
        return pageResp;
    }
    // 保存电子书  保存有两种 一种是 编辑更新 一种是直接 插入 区别就是 看有没有id 这个值
    public void save(DocSaveReq req) {
        Doc doc=CopyUtil.copy(req,Doc.class);
        Content content = CopyUtil.copy(req, Content.class);
        if(ObjectUtils.isEmpty(req.getId())){
            // 空的话 就是去新增值
            long snownum = snowFlake.nextId();
            //给设置值
            doc.setId(snownum);
            docMapper.insert(doc);

            content.setId(snownum);
            contentMapper.insert(content);

        }else{
            // 修改
            docMapper.updateByPrimaryKey(doc);

            contentMapper.updateByPrimaryKeyWithBLOBs(content);
        }


    }

    public void delete(Long id) {
        docMapper.deleteByPrimaryKey(id);
    }
    public void delete(List<String> ids) {
        DocExample docExample = new DocExample();// 创建条件对象
        DocExample.Criteria criteria = docExample.createCriteria(); // 为对象添加 具体的条件
        criteria.andIdIn(ids);
        docMapper.deleteByExample(docExample);
    }

    public List<DocQueryResp> all(){
        DocExample docExample = new DocExample();
        docExample.setOrderByClause("sort asc");
        List<Doc> docList = docMapper.selectByExample(docExample);
        List<DocQueryResp> respList = CopyUtil.copyList(docList, DocQueryResp.class);// 后面是泛型 里面的类
        return respList;
    }
}
