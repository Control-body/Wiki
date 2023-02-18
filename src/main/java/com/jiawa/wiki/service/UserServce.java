package com.jiawa.wiki.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiawa.wiki.domain.User;
import com.jiawa.wiki.domain.UserExample;
import com.jiawa.wiki.exception.BusinessException;
import com.jiawa.wiki.exception.BusinessExceptionCode;
import com.jiawa.wiki.mapper.UserMapper;
import com.jiawa.wiki.req.UserQueryReq;
import com.jiawa.wiki.req.UserSaveReq;
import com.jiawa.wiki.resp.PageResp;
import com.jiawa.wiki.resp.UserQueryResp;
import com.jiawa.wiki.resp.UserResps;
import com.jiawa.wiki.utils.CopyUtil;
import com.jiawa.wiki.utils.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServce {
    private static final Logger Log= LoggerFactory.getLogger(UserServce.class);

    @Resource
    //JDK自带的
    //@Autowired // 自动注入 Spring带的
    private UserMapper userMapper;

    @Resource
    private SnowFlake snowFlake;
    public PageResp<UserQueryResp> list(UserQueryReq req){
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if(!ObjectUtils.isEmpty(req.getName())){
            criteria.andNameLike("%"+ req.getName()+"%%"); // 模糊匹配查询条件
        }
        PageHelper.startPage(req.getPage(), req.getSize());
        List<User> userList = userMapper.selectByExample(userExample);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        Log.info("总行数:{}",pageInfo.getTotal());
        Log.info("总页数:{}",pageInfo.getPages());

        List<UserQueryResp> respList = CopyUtil.copyList(userList, UserQueryResp.class);// 后面是泛型 里面的类
        PageResp<UserQueryResp> pageResp= new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(respList);
        return pageResp;
    }

    public UserResps all(UserQueryReq req) {

        List<User> userList = userMapper.selectByExample(null);
        List<UserQueryResp> respList = CopyUtil.copyList(userList, UserQueryResp.class);
        UserResps userResps = new UserResps();
        userResps.setList(respList);
        return userResps;
    }
    // 保存电子书  保存有两种 一种是 编辑更新 一种是直接 插入 区别就是 看有没有id 这个值
    public void save(UserSaveReq req) {
        User user=CopyUtil.copy(req,User.class);

        if(ObjectUtils.isEmpty(req.getId())){
            // 空的话 就是去新增值
            if(ObjectUtils.isEmpty(selectByLoginName(req.getLoginName()))){
                long l = snowFlake.nextId();
                //给设置值
                user.setId(l);
                userMapper.insert(user);
            }else{
//                用户名重复了 抛出异常
                throw new BusinessException(BusinessExceptionCode.USER_LOGIN_NAME_EXIST);
            }

        }else{
            //新增
            userMapper.updateByPrimaryKey(user);
        }

    }

    public void delete(Long id) {
        userMapper.deleteByPrimaryKey(id);
    }

//    查询是否有 用户名的用户
    public User  selectByLoginName(String LoginName){
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andLoginNameEqualTo(LoginName);
        List<User> users = userMapper.selectByExample(userExample);
        if (CollectionUtils.isEmpty(users)){
            return null;
        }else{
            return users.get(0);
        }
    }
}
