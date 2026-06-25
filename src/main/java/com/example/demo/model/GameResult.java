package com.example.demo.model;

public enum GameResult{
	WIN("You Win", 2),
	LOSE("You Lose", 1),
	DRAW("Draw", 0);
	private String message;
	private int result;
	
	private GameResult(String message, int result) {
		this.message = message;
		this.result = result;
	}
	
	public String getMessage() {
		return message;
	}
	public int getResult() {
		return result;
	}
	
	public static GameResult getByResult(int result) {
		for(GameResult gameResult : GameResult.values()) {
			if(gameResult.getResult() == result) {
				return gameResult;
			}
		}
		return null;
	}
}