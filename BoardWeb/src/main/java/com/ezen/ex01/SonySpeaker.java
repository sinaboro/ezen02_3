package com.ezen.ex01;

import org.springframework.stereotype.Component;

@Component
public class SonySpeaker implements Speaker{
	public SonySpeaker() {
		System.out.println("소니생성자 스피커..");
	}
	
	public void volumeUp() {
		System.out.println("소니 볼륨 up");
	}
	public void volumeDown() {
		System.out.println("볼륨 Down");
	}
}
