package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String play(@RequestParam("hand") String hand, RedirectAttributes redirectAttributes, HttpSession session) {
		/* じゃんけんの手の取得*/
		Hand userHand = Hand.valueOf(hand);
		Hand cpuHand = jankenService.getCpuHand();
		/* セッションから現在のスコアを取得 */
		int nowScore = getSessionAttribute(session, "score");
		/* 勝敗判定 */
		GameResult result = judgeWinLoseService.judgeWinLose(userHand, cpuHand);
		/* スコア計算 */
		nowScore = calcScoreService.calcScore(nowScore, result);
		/* 現在のスコアをセット */
		session.setAttribute("score", nowScore);
		
		redirectAttributes.addFlashAttribute("hand", userHand.getLabel());
		redirectAttributes.addFlashAttribute("cpuHand", cpuHand.getLabel());
		redirectAttributes.addFlashAttribute("judgeResult", result.getMessage());
		
		return "redirect:/";
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
		int nowScore = getSessionAttribute(session, "score");
		model.addAttribute("score", nowScore);
	}
	
	/* セッションから特定の値を取り出し、nullチェックをする */
	private int getSessionAttribute(HttpSession session, String name) {
		//sessionが持つscoreを取得
		Integer sessionAttribute = (Integer)session.getAttribute(name);
		//もし今の値がnullであれば初期表示のスコアを0にする
		if(sessionAttribute == null) {
			return 0;
		}
		return sessionAttribute;
	}
}

