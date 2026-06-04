package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class JudgeWinLoseService {
	/** 定数宣言 **/
	private static final int ROCK = 0;
	private static final int SCISSORS = 1;
	private static final int PAPER = 2;
	
	public String judgeWinLose(String userHand, String cpuHand) {
		int userNum = convertHand(userHand);	//ユーザーの手の置換
		int cpuNum = convertHand(cpuHand);		//CPUの手の置換
		
		//勝敗の判定ロジック（勝ちパターンの列挙、あいこ、それ以外を負け）
		if((userNum == ROCK && cpuNum == SCISSORS) || (userNum == SCISSORS && cpuNum == PAPER) || (userNum == PAPER && cpuNum == ROCK)) {
			return "You Win";
		}else if(userNum == cpuNum){
			return "Draw";
		}else {
			return "You Lose";
		}
	}
	
	//手の文字列をもとに計算用の数値をセット
	public int convertHand(String hand) {
		switch(hand) {
		case "グー":
			return ROCK;
		case "チョキ":
			return SCISSORS;
		case "パー":
			return PAPER;
		default:
			return -1;
		}
	}
}
