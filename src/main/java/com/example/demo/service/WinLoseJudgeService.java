package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class WinLoseJudgeService {
	public String winLoseJudge(String userHand, String cpuHand) {
		/** 変数宣言 **/
		int userNum = 0;	//ユーザーの手の置換
		int cpuNum = 0;		//CPUの手の置換
		
		/** 手の文字列を数値に置換 **/
		userNum = setHand(userHand);	//ユーザーの手の置換
		cpuNum = setHand(cpuHand);	//CPUの手の置換
		
		//勝敗の判定ロジック（勝ちパターンの列挙、あいこ、それ以外を負け）
		if((userNum == 0 && cpuNum == 1) || (userNum == 1 && cpuNum == 2) || (userNum == 2 && cpuNum == 0)) {
			return "You Win";
		}else if(userNum == cpuNum){
			return "Draw";
		}else {
			return "You Lose";
		}
		
	}
	
	//手の文字列をもとに計算用の数値をセット
	public int setHand(String hand) {
		int handNum = 0;
		switch(hand) {
		case "グー":
			handNum = 0;
			break;
		case "チョキ":
			handNum = 1;
			break;
		case "パー":
			handNum = 2;
			break;
		}
		return handNum;
	}
}
