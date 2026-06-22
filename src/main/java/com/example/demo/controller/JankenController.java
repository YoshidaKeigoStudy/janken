package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Hand;
import com.example.demo.model.GameResult;
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
		/* じゃんけんの手の取得*/
		Hand userHand = Hand.fromString(hand);
		Hand cpuHand = jankenService.getCpuHand();
		/* セッションから現在のスコアを取得 */
		Integer nowScore = (Integer)session.getAttribute("score");
		/* 勝敗判定 */
		int judgeResultNum = judgeWinLoseService.judgeWinLose(userHand, cpuHand);
		/* スコア計算 */
		nowScore = calcScoreService.calcScore(nowScore, judgeResultNum);
		/* 現在のスコアをセット */
		session.setAttribute("score", nowScore);
		/* 得点にセット */
		setupScore(model, session);
		
		/* 勝敗判定の数値を文字列に変換 */
		String judgeResult = convertJudge(judgeResultNum);
		
		model.addAttribute("hand", hand);
		model.addAttribute("cpuHand", cpuHand);
		model.addAttribute("judgeResult", judgeResult);
		
		return "index";
	}
	
	//数値を文字列に変換
	public String convertJudge(int judgeResultNum) {
		if(judgeResultNum == GameResult.WIN.getValue()) {
			return "You Win";
		}else if(judgeResultNum == GameResult.LOSE.getValue()) {
			return "You Lose";
		}else {
			return "Draw";
		}
	}
	
	/* リセットボタン押下処理 */
	@PostMapping("/reset")
	public String reset(HttpSession session) {
		/* スコアを0に設定する */
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

