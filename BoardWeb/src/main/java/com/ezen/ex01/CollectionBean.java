package com.ezen.ex01;

import java.util.List;
import java.util.Set;

public class CollectionBean {
	
//	private List<String> addressList;
	private Set<String> addressList;

	public Set<String> getAddressList() {
		return addressList;
	}

	public void setAddressList(Set<String> addressList) {
		this.addressList = addressList;
	}

	public CollectionBean() {
		System.out.println("CollectionBean 생성자");
	}
//	public List<String> getAddressList() {
//		return addressList;
//	}
//
//	public void setAddressList(List<String> addressList) {
//		this.addressList = addressList;
//	}
	
	
	
}
