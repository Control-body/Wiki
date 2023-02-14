package com.jiawa.wiki.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DocSaveReq {
    private Long id;
    @NotNull(message = "电子书ID不能为空")
    private Long ebookId;
    @NotNull(message = "父类名字不能为空")
    private Long parent;
    @NotNull(message = "名字不能为空")
    private String name;
    @NotNull(message = "排序不能为空")
    private Integer sort;
    private Integer viewCount;
    private Integer voteCount;
    @NotNull(message = "内容不能为空")
    private Integer content;
}