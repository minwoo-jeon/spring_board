package com.myportfolio.web.domain;

public class PageHandlerDTO {

//    int page; //현재 페이지
//
//    int pageSize; //한 페이지의 크기

    private SearchConditionDTO sc;

    int totalCnt; //총 게시물 갯수
    int naviSize = 10; //페이지 내비게이션의 크기
    int totalPage; //전체 페이지의 갯수

    int beginPage; //내비게이션의 첫번쨰 페이지
    int endPage; //내비게이션의 마지막 페이지
    boolean showPrev; // 이전 페이지로 이동하는 링크를 보여줄것인지의 여부
    boolean showNext; //다음 페이지로 이동하는 링크를 보여줄것인지 여부


    public PageHandlerDTO() {}

    public PageHandlerDTO(int totalCnt , SearchConditionDTO sc) {
        this.totalCnt = totalCnt;
        this.sc = sc;
        doPaging(totalCnt, sc);
    }


    public void doPaging(int totalCnt, SearchConditionDTO sc) {
        this.totalCnt = totalCnt;

        totalPage = (int) Math.ceil(totalCnt / (double) sc.getPageSize());
        beginPage = (sc.getPage() - 1) / naviSize * naviSize + 1;
        endPage = Math.min(beginPage + naviSize - 1, totalPage);
        showPrev = beginPage != 1;
        showNext = endPage != totalPage;
    }

    void print() {
        System.out.println("page = " + sc.getPage());
        System.out.print(showPrev ? "[PREV]" : "");
        for (int i = beginPage; i <= endPage; i++) {
            System.out.print(i + " ");
        }
        System.out.println(showNext ? " [NEXT]" : "");
    }


    public int getTotalCnt() {
        return totalCnt;
    }

    public void setTotalCnt(int totalCnt) {
        this.totalCnt = totalCnt;
    }

    public SearchConditionDTO getSc() {
        return sc;
    }

    public void setSc(SearchConditionDTO sc) {
        this.sc = sc;
    }

    public int getNaviSize() {
        return naviSize;
    }

    public void setNaviSize(int naviSize) {
        this.naviSize = naviSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }


    public int getBeginPage() {
        return beginPage;
    }

    public void setBeginPage(int beginPage) {
        this.beginPage = beginPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
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

    @Override
    public String toString() {
        return "PageHandler{" +
                "sc=" + sc +
                ", totalCnt=" + totalCnt +
                ", naviSize=" + naviSize +
                ", totalPage=" + totalPage +
                ", beginPage=" + beginPage +
                ", endPage=" + endPage +
                ", showPrev=" + showPrev +
                ", showNext=" + showNext +
                '}';
    }
}