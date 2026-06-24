package com.example.demo.model;

/** じゃんけんの手の列挙 **/
public enum Hand{
	ROCK("グー"),
	SCISSORS("チョキ"),
	PAPER("パー");
	private String label;
	
	/** コンストラクタ **/
	private Hand(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
}

