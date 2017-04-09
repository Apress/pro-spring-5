package com.apress.prospring5.ch5;

import com.apress.prospring5.ch2.common.Singer;

/**
 * Created by iuliana.cosmina on 4/2/17.
 */
public class GoodGuitarist implements Singer {

	@Override public void sing() {
		System.out.println("Who says I can't be free \n" +
				"From all of the things that I used to be");
	}
}
