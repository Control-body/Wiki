package com.jiawa.wiki.req;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PageReq {
    private int page;
    private int size;
}
