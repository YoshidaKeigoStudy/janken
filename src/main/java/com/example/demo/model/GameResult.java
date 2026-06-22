package com.example.demo.model;

public enum GameResult{
	WIN(1),
	LOSE(-1),
	DRAW(0);
	private final int value;
	
	//コンストラクタ
	private GameResult(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}