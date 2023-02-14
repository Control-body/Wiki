package com.jiawa.wiki.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EbookSaveReq {
    private Long id;
    @NotNull(message = "姓名不能为空")
    private String name;
    @NotNull(message = "分类不能为空")
    private Long category1Id;
    @NotNull(message = "分类不能为空")
    private Long category2Id;
    @NotNull(message = "描述不能为空")
    private String description;

    private String cover;

    private Integer docCount;

    private Integer viewCount;

    private Integer voteCount;
}