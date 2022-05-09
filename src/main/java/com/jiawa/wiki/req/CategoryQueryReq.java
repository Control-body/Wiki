package com.jiawa.wiki.req;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class CategoryQueryReq extends PageReq{
    private Long id;

    private String name;
}