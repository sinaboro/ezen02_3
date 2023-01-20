package org.zerock.domain;

import lombok.Data;

@Data
public class Criterial {
	
	private int pageNum;
	private int amount;
	
	private String type;    //작성자(W), 내용(C), 제목(T)  TCW -> T C W
	private String keyword;   // 검색한 글
	
	public Criterial() {
		this(1,10);
	}
	
	public Criterial(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	public String[] getTypeArr() {
		return type == null? new String[] {} : type.split("");
	}
	
}
