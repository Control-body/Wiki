package com.jiawa.wiki.controller;

import com.jiawa.wiki.req.UserLoginReq;
import com.jiawa.wiki.req.UserQueryReq;
import com.jiawa.wiki.req.UserSaveReq;
import com.jiawa.wiki.resp.*;
import com.jiawa.wiki.service.UserServce;
import com.jiawa.wiki.utils.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.concurrent.TimeUnit;

// 在controller层 不要出现真实的 dao实体类
@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger Log= LoggerFactory.getLogger(UserServce.class);
//    redis 的操作类
    @Resource
    private RedisTemplate redisTemplate;
//    使用雪花算法生成token
    @Resource
    private SnowFlake snowFlake;
    @Resource
    private UserServce userServce;
    @GetMapping("/list")
    public CommonResp list(@Valid UserQueryReq req){
        CommonResp<PageResp<UserQueryResp>> objectCommonResp = new CommonResp<>();
        PageResp<UserQueryResp> list = userServce.list(req);
        objectCommonResp.setContent(list);
        return objectCommonResp;
    }

    @GetMapping("/all")
    public CommonResp all(UserQueryReq req){
        CommonResp<UserResps> objectCommonResp = new CommonResp<>();
        UserResps list = userServce.all(req);
        objectCommonResp.setContent(list);
        return objectCommonResp;
    }

    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody UserSaveReq req){
//        将密码进行加密
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonResp objectCommonResp = new CommonResp<>();
        userServce.save(req);
        return objectCommonResp;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable(value = "id") Long id){
        CommonResp objectCommonResp = new CommonResp<>();
        System.out.println("删除来了");
        userServce.delete(id);
        return objectCommonResp;
    }

    @PostMapping("/login")
    public CommonResp login(@Valid @RequestBody UserLoginReq req){
        req.setPassword(DigestUtils.md5DigestAsHex( req.getPassword().getBytes()));
        CommonResp<UserLoginResp> resps = new CommonResp<>();
        UserLoginResp userLoginResp=userServce.login(req);
        Long token = snowFlake.nextId();
        Log.info("放入redis缓存{}" ,token);
//        放入reids
        redisTemplate.opsForValue().set(token.toString(), userLoginResp,3600*24, TimeUnit.SECONDS);
         userLoginResp.setToken(token.toString());
        resps.setContent(userLoginResp);
        return resps;
    }
    @GetMapping("/logout/{token}")
    public CommonResp logout(@PathVariable String token){
        CommonResp<Object> resp = new CommonResp<>();
        redisTemplate.delete(token);
        Log.info("从redis删除token:{}",token);
        return resp;

    }

}
