package com.example.demo.model;

/** じゃんけんの手の列挙 **/
public enum Hand{
	ROCK(0),
	SCISSORS(1),
	PAPER(2);
	private final int value;
	
	/** コンストラクタ **/
	private Hand(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public static Hand fromString(String hand) {
		switch(hand) {
			case "グー":
				return ROCK;
			case "チョキ":
				return SCISSORS;
			case "パー":
				return PAPER;
			default:
				return null;
		}
	}
}

