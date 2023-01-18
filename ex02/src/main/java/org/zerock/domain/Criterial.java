package org.zerock.domain;

import lombok.Data;

@Data
public class Criterial {
	
	private int pageNum;
	private int amount;
	
	public Criterial() {
		this(1,10);
	}
	
	public Criterial(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
}
