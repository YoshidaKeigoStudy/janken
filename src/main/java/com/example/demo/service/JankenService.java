package com.example.demo.service;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class JankenService {
	public String cpuHand() {
		/** 変数宣言 **/
		Random rand = new Random();
		int handNum = rand.nextInt(3);
		String cpuHand = null;
		
		/** メソッド呼び出し **/
		cpuHand = setCpuHand(handNum);
		
		return cpuHand;
	}
	
	//乱数からCPUの手を生成するメソッド
	public String setCpuHand(int handNum) {
		String cpuHand = null;
		switch(handNum) {
		case 0:
			cpuHand = "グー";
			break;
		case 1:
			cpuHand = "チョキ";
			break;
		case 2:
			cpuHand = "パー";
			break;
		}
		return cpuHand;
	}
}
