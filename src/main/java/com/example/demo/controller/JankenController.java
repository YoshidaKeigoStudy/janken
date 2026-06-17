package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Hand;
import com.example.demo.service.CalcScoreService;
import com.example.demo.service.JankenService;
import com.example.demo.service.JudgeWinLoseService;

import jakarta.servlet.http.HttpSession;

@Controller
public class JankenController {
	//じゃんけんサービスクラス型の変数を定義
	@Autowired//DI注入アノテーション
	private JankenService jankenService;
	@Autowired
	private JudgeWinLoseService judgeWinLoseService;
	@Autowired
	private CalcScoreService calcScoreService;
	
	/** 定数宣言 **/
	private static final int WIN = 1;
	private static final int LOSE = -1;
	private static final int DRAW = 0;
	
	//初期表示
	@GetMapping("/")
	public String index(Model model, HttpSession session) {
		//sessionが持つscoreを取得
		setupScore(model, session);
		return "index";
	}
	
	//画面から選択された手を受け取り、Modelに詰めて画面へ渡す
	@PostMapping("/play")
	public String play(@RequestParam("hand") String hand, Model model, HttpSession session) {
		Hand userHand = Hand.fromString(hand);		//ユーザの手を取得（String型handを、fromStringメソッドでHand型に変換して取得）
		Hand cpuHand = jankenService.getCpuHand();	//CPUの手を取得（getCpuHandメソッドでCPUの手を取得）
		Integer nowScore = (Integer)session.getAttribute("score");	//セッションから現在のscoreを取得
		int judgeResultNum = judgeWinLoseService.judgeWinLose(userHand, cpuHand);	//勝敗判定（ユーザーとCPUの手を渡して判定）
		nowScore = calcScoreService.calcScore(nowScore, judgeResultNum);	//勝敗結果の値を渡して得点を取得
		session.setAttribute("score", nowScore);
		setupScore(model, session);
		
		String judgeResult = convertJudge(judgeResultNum);	//数値を文字列に変換
		
		model.addAttribute("hand", hand);
		model.addAttribute("cpuHand", cpuHand);
		model.addAttribute("judgeResult", judgeResult);
		
		
		return "index";
	}
	
	//数値を文字列に変換
	public String convertJudge(int judgeResultNum) {
		if(judgeResultNum == WIN) {
			return "You Win";
		}else if(judgeResultNum == LOSE) {
			return "You Lose";
		}else {
			return "Draw";
		}
	}
	
	@PostMapping("/reset")
	public String reset(HttpSession session) {
		session.setAttribute("score", 0);
		return "redirect:/";
	}
	
	//今のscoreを取得し、null（初回）であればnowScoreを0に、そうでなければ現在のスコアを登録
	private void setupScore(Model model, HttpSession session) {
		//sessionが持つscoreを取得
		Integer nowScore = (Integer)session.getAttribute("score");
		//もし今のスコアがnullであれば初期表示のスコアを0にする
		if(nowScore == null) {
			nowScore = 0;
			session.setAttribute("score", nowScore);
		}
		model.addAttribute("score", nowScore);
	}
}

