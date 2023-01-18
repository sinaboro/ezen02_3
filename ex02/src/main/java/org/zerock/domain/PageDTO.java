package org.zerock.domain;

import lombok.Data;

@Data
public class PageDTO {
	
	private int startPage;
	private int endPage;
	private boolean prev, next;
	
	private int total;
	private Criterial cri;
	
	public PageDTO(Criterial cri, int total) {
		this.cri = cri;    //pageNum(내가 보는 페이지=>13), amount(화면 나오는 데이타 수 => 10)
		this.total =total;  //전체 데이타 수( 152)
		
		this.endPage = (int)(Math.ceil(cri.getPageNum()/10.0)) * 10;
		this.startPage = this.endPage - 9;
		
		int realEnd = (int)(Math.ceil(total*1.0)/cri.getAmount());
		
		if(realEnd < endPage) {
			endPage = realEnd;
		}
		
		this.prev = this.startPage>1;
		this.next = this.endPage < realEnd;
				
	}
	
}
