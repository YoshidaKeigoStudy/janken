package com.example.demo.service;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class JankenService {
	/** 定数宣言 **/
	private static final int ROCK = 0;
	private static final int SCISSORS = 1;
	private static final int PAPER = 2;
	
	//CPUの手を取得するメソッド
	public String getCpuHand() {
		/** 乱数生成 **/
		Random rand = new Random();
		int handNum = rand.nextInt(3);

		//CPUの手の文字列を取得
		String cpuHand = convertCpuHand(handNum);
		
		return cpuHand;
	}
	
	//乱数からCPUの手の文字列に変換するメソッド
	public String convertCpuHand(int handNum) {
		String cpuHand = null;
		switch(handNum) {
		case ROCK:
			cpuHand = "グー";
			break;
		case SCISSORS:
			cpuHand = "チョキ";
			break;
		case PAPER:
			cpuHand = "パー";
			break;
		}
		return cpuHand;
	}
}
