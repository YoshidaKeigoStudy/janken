package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Hand;
import com.example.demo.service.JankenService;
import com.example.demo.service.JudgeWinLoseService;

@Controller
public class JankenController {
	//じゃんけんサービスクラス型の変数を定義
	@Autowired//DI注入アノテーション
	private JankenService jankenService;
	@Autowired
	private JudgeWinLoseService judgeWinLoseService;
	
	//初期表示
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	//画面から選択された手を受け取り、Modelに詰めて画面へ渡す
	@PostMapping("/play")
	public String play(@RequestParam("hand") String hand, Model model) {
		/** 変数宣言 **/
		Hand userHand = Hand.fromString(hand);
		Hand cpuHand;			//CPUの手
		String judgeResult;		//勝敗判定
		
		/** 値の注入 **/
		cpuHand = jankenService.getCpuHand();							//jankenServiceクラスのgetCpuHandメソッドからCPUの手を取得
		judgeResult = judgeWinLoseService.judgeWinLose(userHand, cpuHand);	//勝敗判定の値取得
		
		model.addAttribute("hand", hand);
		model.addAttribute("cpuHand", cpuHand);
		model.addAttribute("judgeResult", judgeResult);
		
		return "index";
	}
}

