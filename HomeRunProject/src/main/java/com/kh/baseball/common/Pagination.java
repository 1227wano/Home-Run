package com.kh.baseball.common;

public class Pagination {
	
	public Pagination() {
		super();
	}

	public static PageInfo getPageInfo(String playerTeam, int listCount, int currentPage, int boardLimit, int pageLimit) {
		
		int maxPage = (int)(Math.ceil((double)listCount / boardLimit));
		int startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		int endPage = startPage + pageLimit - 1;
		if(endPage > maxPage) endPage = maxPage;
		
		return PageInfo.builder()
					   .listCount(listCount)
					   .currentPage(currentPage)
					   .boardLimit(boardLimit)
					   .pageLimit(pageLimit)
					   .maxPage(maxPage)
					   .startPage(startPage)
					   .endPage(endPage)
					   .build();
	}
	
	

}
