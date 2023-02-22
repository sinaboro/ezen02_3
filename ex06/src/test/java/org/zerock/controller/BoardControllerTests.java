package org.zerock.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.log4j.Log4j;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	               "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@Log4j
public class BoardControllerTests {
	
	@Autowired
	private WebApplicationContext ctx;
	
	private MockMvc  mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void testList() throws Exception {
		log.info(
				mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
						.andReturn()
						.getModelAndView()
						.getModelMap()
				);
	}

	@Test
	public void testList2() throws Exception {
		log.info(
				mockMvc.perform(MockMvcRequestBuilders.get("/board/list")
				.param("pagaNum", "2")
				.param("amount", "15"))
				.andReturn()
				.getModelAndView()
				.getModelMap()
				);
	}

	@Test
	public void testResiter() throws Exception {
		log.info(
				mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
				.param("title", "테스트 새글")
				.param("content", "테스트 새글 내용")
				.param("writer", "user00"))
				.andReturn()
				.getModelAndView()
				.getModelMap()
				);
	}

	@Test
	public void testGet() throws Exception {
		log.info(
				mockMvc.perform(MockMvcRequestBuilders.get("/board/get")
						.param("bno", "102"))
				.andReturn()
				.getModelAndView()
				.getModelMap()
				);
	}

	@Test
	public void testModify() throws Exception {
		log.info(
				mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
						.param("bno", "102")
						.param("title", "수정한 제목")
						.param("content", "수정한 내용")
						.param("writer", "user11"))
				.andReturn()
				.getModelAndView()
				.getModelMap()
				);
	}

	@Test
	public void testRemove() throws Exception {
		log.info(
				mockMvc.perform(MockMvcRequestBuilders.post("/board/remove")
						.param("bno", "101"))
				.andReturn()
				.getModelAndView()
				.getModelMap()
				);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
