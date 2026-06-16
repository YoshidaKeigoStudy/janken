package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class CalcScoreService{
	private static final int WIN = 1;
	private static final int LOSE = -1;
	private static final int DRAW = 0;
	
	private int score;
	
	public int calcScore(int judgeResultNum) {
		if(judgeResultNum == WIN) {
			score++;
			}
		return score;
	}
}