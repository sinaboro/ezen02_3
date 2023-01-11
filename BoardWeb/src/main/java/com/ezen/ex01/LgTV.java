package com.ezen.ex01;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("lg")
public class LgTV  implements TV{
	
//	@Autowired
//	@Qualifier("appleSpeaker")
	@Resource(name="sonySpeaker")
	private Speaker speaker;
	
	public LgTV() {
		System.out.println("LgTV 객체 생성");
	}
	@Override
	public void powerOn() {
		System.out.println("LgTV .......전원 on");
	}

	@Override
	public void powerOff() {
		System.out.println("LgTV .......전원 off");
	}

	@Override
	public void volumeUp() {
		speaker.volumeUp();
//		System.out.println("LgTV .......볼륨 up");
	}

	@Override
	public void volumeDown() {
		System.out.println("LgTV .......볼륨 down");
	}
	
}
