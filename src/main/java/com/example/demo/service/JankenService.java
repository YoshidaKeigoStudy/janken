package com.example.demo.service;
import org.springframework.stereotype.Service;
import java.util.Random;
import com.example.demo.model.Hand;

@Service
public class JankenService {
	/** 定数宣言 **/
	// TODO: JudgeWinLoseServiceにも同じ定数が重複定義されている。enumにまとめることを検討する。
	private static final int ROCK = 0;
	private static final int SCISSORS = 1;
	private static final int PAPER = 2;

	// TODO: Randomはメソッド呼び出しのたびにインスタンスが生成される。フィールドに持たせるほうが効率的。
	// 例: private final Random rand = new Random();

	//CPUの手を取得するメソッド
	public Hand getCpuHand() {
		/** 乱数生成 **/
		Random rand = new Random();
		int handNum = rand.nextInt(3);

		//CPUの手を取得
		Hand cpuHand = convertCpuHand(handNum);
		
		return cpuHand;
	}
	
	// TODO: このメソッドはgetCpuHand()の内部処理用なので、publicではなくprivateにすべき。
	// TODO: switchのdefaultがないため、想定外のhandNumが渡された場合にnullが返る。
	//       defaultでIllegalArgumentExceptionをスローする等の防御的実装を検討する。
	//乱数からCPUの手の文字列に変換するメソッド
	private Hand convertCpuHand(int handNum) {
		switch(handNum) {
		case ROCK:
			return Hand.ROCK;
		case SCISSORS:
			return Hand.SCISSORS;
		case PAPER:
			return Hand.PAPER;
		default:
			return null;
		}
	}
}
