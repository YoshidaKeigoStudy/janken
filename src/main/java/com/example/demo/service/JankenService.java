package com.example.demo.service;
import org.springframework.stereotype.Service;

@Service
public class JankenService {
	public String cpuHand;
	public String cpuHand() {
		this.cpuHand = "CPUの手はグーです";
		return cpuHand;
	}
}
