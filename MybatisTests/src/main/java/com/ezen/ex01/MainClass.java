package com.ezen.ex01;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MainClass {

	public static void main(String[] args) {
		
		String resource = "com/ezen/ex01/mybatis-config.xml";
		InputStream inputStream =null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = 
					new SqlSessionFactoryBuilder().build(inputStream);
			
			SqlSession session = sqlSessionFactory.openSession();
			BlogMapper mapper = session.getMapper(BlogMapper.class);
			
			BoardVO vo = mapper.selectOne(2222);
			System.out.println(vo);
			
			vo.setId(4444);
			vo.setName("이예찬");
			vo.setPhone("000-1111-2222");
			vo.setAddress("거주불명");
			
			mapper.insert(vo);
			System.out.println("result : ");
			
			vo = mapper.selectOne(4444);
			System.out.println(vo);
			
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
