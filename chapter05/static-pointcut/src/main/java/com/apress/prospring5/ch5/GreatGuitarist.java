package com.apress.prospring5.ch5;

import com.apress.prospring5.ch2.common.Singer;

/**
 * Created by iuliana.cosmina on 4/2/17.
 */
public class GreatGuitarist implements Singer {

	@Override public void sing() {
		System.out.println("I shot the sheriff, \n" +
				"But I did not shoot the deputy");
	}
}
