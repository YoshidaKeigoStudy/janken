package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class JankenController {
	
	//初期表示
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	//画面から選択された手を受け取り、Modelに詰めて画面へ渡す
	@PostMapping("/play")
	public String play(@RequestParam("hand") String hand, Model model) {
		model.addAttribute("hand", hand);
		
		return "index";
	}
}

