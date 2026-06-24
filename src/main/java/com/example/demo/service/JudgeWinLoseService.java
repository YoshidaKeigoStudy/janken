package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.model.Hand;
import com.example.demo.model.GameResult;

@Service
public class JudgeWinLoseService {
	/** 定数宣言 **/
	private static final int RESULT_PATTERN_NUM = 3;
	
	public GameResult judgeWinLose(Hand userHand, Hand cpuHand) {
		/** 渡されてきた手に対応する値(value)を取得 **/
		int userNum = userHand.ordinal();
		int cpuNum = cpuHand.ordinal();
		int result = (userNum - cpuNum + RESULT_PATTERN_NUM) % RESULT_PATTERN_NUM;	//ユーザの手とCPUの手の差を算出し、自然数に変換
		
		if(result == 2) {
			return GameResult.WIN;
		}else if(result == 1) {
			return GameResult.LOSE;
		}else {
			return GameResult.DRAW;
		}
	}
}
