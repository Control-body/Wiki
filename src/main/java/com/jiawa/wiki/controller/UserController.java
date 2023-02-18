package com.jiawa.wiki.controller;

import com.jiawa.wiki.req.UserQueryReq;
import com.jiawa.wiki.req.UserSaveReq;
import com.jiawa.wiki.resp.CommonResp;
import com.jiawa.wiki.resp.PageResp;
import com.jiawa.wiki.resp.UserQueryResp;
import com.jiawa.wiki.resp.UserResps;
import com.jiawa.wiki.service.UserServce;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

// 在controller层 不要出现真实的 dao实体类
@RestController
@RequestMapping("/user")
public class UserController {
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


}
