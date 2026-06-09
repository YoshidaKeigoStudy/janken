package com.example.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JudgeWinLoseServiceTest {

	private JudgeWinLoseService judgeWinLoseService;

	@BeforeEach
	void setUp() {
		judgeWinLoseService = new JudgeWinLoseService();
	}

	/** judgeWinLose()のテスト **/

	// ユーザー勝利パターン
	@Test
	void グーはチョキに勝つ() {
		assertEquals("You Win", judgeWinLoseService.judgeWinLose("グー", "チョキ"));
	}

	@Test
	void チョキはパーに勝つ() {
		assertEquals("You Win", judgeWinLoseService.judgeWinLose("チョキ", "パー"));
	}

	@Test
	void パーはグーに勝つ() {
		assertEquals("You Win", judgeWinLoseService.judgeWinLose("パー", "グー"));
	}

	// ユーザー敗北パターン
	@Test
	void グーはパーに負ける() {
		assertEquals("You Lose", judgeWinLoseService.judgeWinLose("グー", "パー"));
	}

	@Test
	void チョキはグーに負ける() {
		assertEquals("You Lose", judgeWinLoseService.judgeWinLose("チョキ", "グー"));
	}

	@Test
	void パーはチョキに負ける() {
		assertEquals("You Lose", judgeWinLoseService.judgeWinLose("パー", "チョキ"));
	}

	// あいこパターン
	@Test
	void グー同士はあいこ() {
		assertEquals("Draw", judgeWinLoseService.judgeWinLose("グー", "グー"));
	}

	@Test
	void チョキ同士はあいこ() {
		assertEquals("Draw", judgeWinLoseService.judgeWinLose("チョキ", "チョキ"));
	}

	@Test
	void パー同士はあいこ() {
		assertEquals("Draw", judgeWinLoseService.judgeWinLose("パー", "パー"));
	}

}
