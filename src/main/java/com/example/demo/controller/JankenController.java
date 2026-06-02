package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.JankenService;

@Controller
public class JankenController {
	//じゃんけんサービスクラス型の変数定義
	//JankenService jankenService = new JankenService();
	@Autowired
	private JankenService jankenService;
	
	//初期表示
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	//画面から選択された手を受け取り、Modelに詰めて画面へ渡す
	@PostMapping("/play")
	public String play(@RequestParam("hand") String hand, Model model) {
		String cpuHand;
		model.addAttribute("hand", hand);
		cpuHand = jankenService.cpuHand();
		model.addAttribute("cpuHand", cpuHand);
		return "index";
	}
}

