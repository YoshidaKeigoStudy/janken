package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.JankenService;
import com.example.demo.service.WinLoseJudgeService;

@Controller
public class JankenController {
	//じゃんけんサービスクラス型の変数を定義
	@Autowired//DI注入アノテーション
	private JankenService jankenService;
	@Autowired
	private WinLoseJudgeService winLoseJudgeService;
	
	//初期表示
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	//画面から選択された手を受け取り、Modelに詰めて画面へ渡す
	@PostMapping("/play")
	public String play(@RequestParam("hand") String hand, Model model) {
		/** 変数宣言 **/
		String cpuHand;		//CPUの手
		String judge;		//勝敗判定
		
		/** 値の注入 **/
		cpuHand = jankenService.cpuHand();	//jankenServiceクラスのcpuHandメソッドからCPUの手を取得
		judge = winLoseJudgeService.winLoseJudge(hand, cpuHand);		//勝敗判定の値取得
		
		//画面に渡す値を詰める
		model.addAttribute("hand", hand);
		model.addAttribute("cpuHand", cpuHand);
		model.addAttribute("judgeResult", judge);
		
		return "index";
	}
}

