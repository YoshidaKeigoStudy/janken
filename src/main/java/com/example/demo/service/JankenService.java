package com.example.demo.service;
import org.springframework.stereotype.Service;
import java.util.Random;
import com.example.demo.model.Hand;

@Service
public class JankenService {
	private final Random rand = new Random();

	//CPUの手を取得するメソッド
	public Hand getCpuHand() {
		//CPUの手を取得
		Hand[] hands = Hand.values();	//enumの全てを配列で取得
		return hands[rand.nextInt(hands.length)];	//配列の長さ分幅を持った乱数を生成し、それに対応する手を返す
	}
}
