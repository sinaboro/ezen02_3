package com.ezen.ex01;

import java.util.List;
import java.util.Set;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class CollectionBeanClient {
	public static void main(String[] args) {
		AbstractApplicationContext factory = 
				new GenericXmlApplicationContext("applicationContext.xml");
		
		CollectionBean bean = (CollectionBean) factory.getBean("collectionBean");
		Set<String> lists =  bean.getAddressList();
		
		for (String str : lists) {
			System.out.println(str);
		}
		System.out.println("---------------");
		
		lists.stream().forEach(n->System.out.println(n));
	}
}
