package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.model.Hand;

@Service
public class JudgeWinLoseService {
	/** 定数宣言 **/
	private static final int CONVERTNATURALNUM = 3;
	private static final int WIN = 1;
	private static final int LOSE = -1;
	private static final int DRAW = 0;
	
	public String judgeWinLose(Hand userHand, Hand cpuHand) {
		int userNum = userHand.getValue();	//ユーザーの手の置換
		int cpuNum = cpuHand.getValue();		//CPUの手の置換
		int judgeResult = judgeCulc(userNum, cpuNum);
		

		// TODO: convertHandが-1を返した場合（不正な入力）のチェックがない。
		//       IllegalArgumentExceptionをスローする等の防御的実装を検討する。

		//勝敗の判定ロジック（勝ちパターンの列挙、あいこ、それ以外を負け）
		if(judgeResult == WIN) {
			return "You Win";
		}else if(judgeResult == LOSE){
			return "You Lose";
		}else {
			return "Draw";
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
