package com.gzgz.cloud.authority.common.asserts;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @ClassName: PageList
 * @Description:分页返回封装类
 * @Author: pzl
 * @CreateDate: 2020/11/18 9:54
 * @Version: 1.0
 */
@ApiModel
public class PageList<E> {
    @ApiModelProperty("页码")
    protected int page;
    @ApiModelProperty("总记录数")
    protected long totalCount;
    @ApiModelProperty("记录列表")
    protected List<E> list;

    public PageList() {
    }

    /**
     * 将PageHelper分页后的list转为分页信息
     */
    public static <E> PageList<E> getPageList(int page, long totalCount, List<E> list) {
        PageList<E> pageList = new PageList();
        pageList.setPage(page);
        pageList.setTotalCount((long)((int)totalCount));
        pageList.setList(list);
        return pageList;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public void setList(List<E> list) {
        this.list = list;
    }

    public int getPage() {
        return this.page;
    }

    public long getTotalCount() {
        return this.totalCount;
    }

    public List<E> getList() {
        return this.list;
    }

    public String toString() {
        return "PageList(page=" + this.getPage() + ", totalCount=" + this.getTotalCount() + ", list=" + this.getList() + ")";
    }
}
