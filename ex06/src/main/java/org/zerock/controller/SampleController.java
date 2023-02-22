package org.zerock.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.SampleVO;
import org.zerock.domain.Ticket;

import lombok.extern.log4j.Log4j;

@RestController  //@ResponseBody + @Controller
@RequestMapping("/sample")
@Log4j
public class SampleController {

	@GetMapping(value = "/getText", produces = "text/plain; charset=utf-8")
	public String getText() {
		log.info("Mime Type : " + MediaType.TEXT_PLAIN_VALUE);
		return "안녕하세요2";
	}
	
	@GetMapping(value = "/getSample", 
			produces = {MediaType.APPLICATION_XML_VALUE,
						MediaType.APPLICATION_JSON_VALUE})
	public SampleVO getSample() {
		return new SampleVO(112, "스타","로드");
	}
	
	@GetMapping(value = "/getList")
	public List<SampleVO> getList(){
		
		List<SampleVO> list = new ArrayList<SampleVO>();
		for(int i=10; i<=20; i++)
			list.add(new SampleVO(i, i+"First", i+"Last"));
		return list;
		
//		return IntStream.range(1, 10).
//				mapToObj(i -> new SampleVO(i, i+"First", i+"Last"))
//				.collect(Collectors.toList());
				
	}
	
	@GetMapping("/getMap")
	public Map<String, SampleVO> getMap(){
		Map<String, SampleVO> map = new HashMap<String, SampleVO>();
		
		map.put("first", new SampleVO(111,"그루트", "주니어"));
		return map;
	}
	
	@GetMapping(value = "/check", params = {"height", "weight"})
	public ResponseEntity<SampleVO> check(Double height, Double weight){
		SampleVO vo=  new SampleVO(0, ""+height, "" + weight);
		ResponseEntity<SampleVO> result = null;
		
		if(height<150)
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
		else
			result = ResponseEntity.status(HttpStatus.OK).body(vo);
			
		return result;
	}
	
	
	@GetMapping("/product/{cat}/{pid}")
	public String[] getPath(
		@PathVariable String cat,
		@PathVariable String pid){
			return new String[]{"category :" + cat, "productid :" + pid};
		}
	
	
	@PostMapping("/ticket")  //==> 객체가 전달.===> json변환
//	@ResponseBody
	public Ticket convert(@RequestBody Ticket ticket) {
		log.info("ticket=======> " + ticket);
		return ticket; 
	}
	
	
}


