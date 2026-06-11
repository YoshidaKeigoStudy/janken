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
		/** 渡されてきた手に対応する値(value)を取得 **/
		int userNum = userHand.getValue();
		int cpuNum = cpuHand.getValue();
		int judgeResult = judgeCalc(userNum, cpuNum);	//ユーザとCPUの数値をもとに勝敗を産出

		//勝敗結果に応じて文字列を返す
		if(judgeResult == WIN) {
			return "You Win";
		}else if(judgeResult == LOSE){
			return "You Lose";
		}else {
			return "Draw";
		}
	}
	
	/** 勝敗判定を３パターンで行うための演算 **/
	private int judgeCalc(int user, int cpu) {
		int userMinusCpu = user - cpu + CONVERTNATURALNUM;	//ユーザの手とCPUの手の差を算出し、自然数に変換
		
		if(userMinusCpu % 3 == 2) {
			return WIN;
		}else if(userMinusCpu % 3 == 1) {
			return LOSE;
		}else {
			return DRAW;
		}
	}
}
