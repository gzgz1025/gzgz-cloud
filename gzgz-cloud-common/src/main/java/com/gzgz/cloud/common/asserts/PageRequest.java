package com.gzgz.cloud.common.asserts;

import io.swagger.annotations.ApiModelProperty;


/**
 * @ClassName: PageRequest
 * @Description:分页查询封装类
 * @Author: pzl
 * @CreateDate: 2020/11/18 15:56
 * @Version: 1.0
 */
public class PageRequest {
    private static final int DEFAULT_PAGE_SIZE = 20;
    @ApiModelProperty(
            value = "页码",
            example = "1"
    )
    private Integer page;
    @ApiModelProperty(
            value = "每页数量",
            example = "20"
    )
    private Integer pageSize;

    public PageRequest() {
    }

    public Integer getPage() {
        return this.page != null && this.page > 0 ? this.page : 1;
    }

    public Integer getPageSize() {
        if (this.pageSize != null && this.pageSize >= 1) {
            return this.pageSize > this.defaultPageSize() ? this.defaultPageSize() : this.pageSize;
        } else {
            return this.defaultPageSize();
        }
    }

    protected Integer defaultPageSize() {
        return 20;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String toString() {
        return "PageRequest(page=" + this.getPage() + ", pageSize=" + this.getPageSize() + ")";
    }
}

