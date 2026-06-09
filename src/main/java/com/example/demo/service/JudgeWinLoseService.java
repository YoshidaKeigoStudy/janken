package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.model.Hand;

@Service
public class JudgeWinLoseService {
	/** 定数宣言 **/
	private static final int ROCK = 0;
	private static final int SCISSORS = 1;
	private static final int PAPER = 2;
	private static final int CONVERTNATURALNUM = 3;
	private static final int WIN = 5;
	private static final int LOSE = 6;
	private static final int DRAW = 7;
	
	public String judgeWinLose(String userHand, String cpuHand) {
		int userNum = convertHand(userHand);	//ユーザーの手の置換
		int cpuNum = convertHand(cpuHand);		//CPUの手の置換
		int judge = judgeCulc(userNum, cpuNum);
		

		// TODO: convertHandが-1を返した場合（不正な入力）のチェックがない。
		//       IllegalArgumentExceptionをスローする等の防御的実装を検討する。

		//勝敗の判定ロジック（勝ちパターンの列挙、あいこ、それ以外を負け）
		if(judge == WIN) {
			return "You Win";
		}else if(judge == LOSE){
			return "You Lose";
		}else {
			return "Draw";
		}
		
	}
	
	// TODO: ROCK/SCISSORS/PAPERの定数がJankenServiceにも重複定義されている。enumにまとめることを検討する。
	//手の文字列をもとに計算用の数値をセット
	private int convertHand(String hand) {
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
	
	/** 勝敗判定を３パターンで行うための演算 **/
	private int judgeCulc(int user, int cpu) {
		int userMinusCpu;
		userMinusCpu = user - cpu + CONVERTNATURALNUM;
		if(userMinusCpu % 3 == 2) {
			return WIN;
		}else if(userMinusCpu % 3 == 1) {
			return LOSE;
		}else {
			return DRAW;
		}
		
		
		
	}
}
