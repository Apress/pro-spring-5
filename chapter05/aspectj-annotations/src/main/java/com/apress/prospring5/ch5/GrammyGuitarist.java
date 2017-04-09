package com.apress.prospring5.ch5;

import com.apress.prospring5.ch2.common.Guitar;
import com.apress.prospring5.ch2.common.Singer;
import org.springframework.stereotype.Component;

/**
 * Created by iuliana.cosmina on 4/9/17.
 */
@Component("johnMayer")
public class GrammyGuitarist implements Singer {

	@Override public void sing() {
		System.out.println("sing: Gravity is working against me\n" +
				"And gravity wants to bring me down");
	}

	public void sing(Guitar guitar) {
		System.out.println("play: " + guitar.play());
	}

	public void rest(){
		System.out.println("zzz");
	}

	public void talk(){
		System.out.println("talk");
	}
}