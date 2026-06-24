package com.example.demo.model;

public enum GameResult{
	WIN("You Win"),
	LOSE("You Lose"),
	DRAW("Draw");
	private String message;
	
	private GameResult(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}