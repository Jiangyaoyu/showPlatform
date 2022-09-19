package cn.edu.nxu.pojo;

import lombok.Data;

import java.util.Map;

@Data
public class PageInfo {
    private int currentPage;//当前页
    private int pageSize;//每页大小
    private int totalPage;//总页数
    private int totalCount;//总条数
    private int prePage;//上一页
    private int nextPage;//下一页
    private Map data;//数据

    public PageInfo(int currentPage, int pageSize, int totalCount, Map data) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalPage = totalPage;
        this.totalCount = totalCount;
        this.data = data;
        this.totalPage = totalCount % pageSize ==0 ? totalCount/pageSize : totalCount / pageSize +1;
        this.prePage = currentPage -1 >=1? currentPage -1 :1;
        this.nextPage = currentPage +1 <= this.totalPage ? currentPage +1 : this.totalPage;
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", totalPage=" + totalPage +
                ", totalCount=" + totalCount +
                ", prePage=" + prePage +
                ", nextPage=" + nextPage +
                ", data=" + data +
                '}';
    }
}
