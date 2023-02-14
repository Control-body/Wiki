package com.jiawa.wiki.req;

import lombok.Data;

@Data
public class DocQueryReq extends PageReq{
    private Long id;
    private String name;
    @Override
    public String toString() {
        return "DocQueryReq{}"+super.toString();
    }
}