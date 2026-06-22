package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.model.Hand;
import com.example.demo.model.GameResult;

@Service
public class JudgeWinLoseService {
	/** 定数宣言 **/
	private static final int CONVERT_NATURAL_NUM = 3;
	
	public int judgeWinLose(Hand userHand, Hand cpuHand) {
		/** 渡されてきた手に対応する値(value)を取得 **/
		int userNum = userHand.getValue();
		int cpuNum = cpuHand.getValue();
		int userMinusCpu = userNum - cpuNum + CONVERT_NATURAL_NUM;	//ユーザの手とCPUの手の差を算出し、自然数に変換
		
		if(userMinusCpu % 3 == 2) {
			return GameResult.WIN.getValue();
		}else if(userMinusCpu % 3 == 1) {
			return GameResult.LOSE.getValue();
		}else {
			return GameResult.DRAW.getValue();
		}
	}
}
