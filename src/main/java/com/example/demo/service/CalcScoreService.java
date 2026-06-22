package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.model.GameResult;

@Service
public class CalcScoreService{
	private static final int WIN_POINT = 1;	//加算する得点
	
	public int calcScore(Integer nowScore, int judgeResultNum) {
		if(judgeResultNum == GameResult.WIN.getValue()) {
			nowScore += WIN_POINT;
			}
		return nowScore;
	}
}