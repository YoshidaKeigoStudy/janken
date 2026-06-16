package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class ScoreResetService{
	public int scoreReset(int score) {
		int resetScore;
		score = 0;
		resetScore = score;
		
		return resetScore;
	}
}