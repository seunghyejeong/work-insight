package com.opencloudplatform.edm.domain;

public class PageHandler {
    private Integer nowPage;       // 현재 보고있는 페이지
    private Integer pageSize;   // 한 페이지에 보여줄 글의 갯수
    private int beginPage;  // 시작 페이지
    private int naviSize=10;   // navigation bar에 보여줄 페이지 갯수
    private int endPage;    // 끝 페이지
    private int totalPageCnt; // page의 총 갯수 (endPage와 같을 수 있음)
    private Integer totalPostCnt;   // 게시글의 총 갯수
    private boolean showPrev;
    private boolean showNext;

    public int getNowPage() {
        return nowPage;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getBeginPage() {
        return beginPage;
    }

    public void setBeginPage(int beginPage) {
        this.beginPage = beginPage;
    }

    public int getNaviSize() {
        return naviSize;
    }

    public void setNaviSize(int naviSize) {
        this.naviSize = naviSize;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public int getTotalPageCnt() {
        return totalPageCnt;
    }

    public void setTotalPageCnt(int totalPageCnt) {
        this.totalPageCnt = totalPageCnt;
    }

    public int getTotalPostCnt() {
        return totalPostCnt;
    }

    public void setTotalPostCnt(int totalPostCnt) {
        this.totalPostCnt = totalPostCnt;
    }

    public boolean isShowPrev() {
        return showPrev;
    }

    public void setShowPrev(boolean showPrev) {
        this.showPrev = showPrev;
    }

    public boolean isShowNext() {
        return showNext;
    }

    public void setShowNext(boolean showNext) {
        this.showNext = showNext;
    }

    public PageHandler(int totalPostCnt, int nowPage){
        this(totalPostCnt, nowPage, 10);
    }
    public PageHandler(int totalPostCnt, int nowPage, int pageSize){
        this.totalPostCnt = totalPostCnt;
        this.nowPage = nowPage;
        this.pageSize = pageSize;

        totalPageCnt = (int)Math.ceil(totalPostCnt / (double)pageSize);
        beginPage = (nowPage / 10 * 10)+1;
        endPage = Math.min(beginPage + naviSize -1 ,totalPageCnt);
        showPrev = beginPage != 1;
        showNext = endPage != totalPageCnt;
    }

    void print(){
        System.out.println("nowPage = " + nowPage);
        System.out.print(showPrev ? "[PREV]" : "");
        for (int i = beginPage; i <= endPage; i++) {
            System.out.print(i+" ");
        }
        System.out.println(showNext ? "[NEXT]" : "");
    }

    @Override
    public String toString() {
        return "PageHandler{" +
                "nowPage=" + nowPage +
                ", pageSize=" + pageSize +
                ", beginPage=" + beginPage +
                ", naviSize=" + naviSize +
                ", endPage=" + endPage +
                ", totalPageCnt=" + totalPageCnt +
                ", totalPostCnt=" + totalPostCnt +
                ", showPrev=" + showPrev +
                ", showNext=" + showNext +
                '}';
    }
}
