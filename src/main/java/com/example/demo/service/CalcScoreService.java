package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.model.GameResult;

@Service
public class CalcScoreService{
	private static final int WIN_POINT = 1;	//加算する得点
	
	public int calcScore(int nowScore, GameResult result) {
		if(result == GameResult.WIN) {
			nowScore += WIN_POINT;
		}
		return nowScore;
	}
}