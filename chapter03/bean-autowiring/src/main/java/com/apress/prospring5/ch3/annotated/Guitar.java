package com.apress.prospring5.ch3.annotated;

import org.springframework.stereotype.Component;

/**
 * Created by iuliana.cosmina on 2/22/17.
 */
@Component("gopher")
public class Guitar {

	public void sing(){
		System.out.println("Cm Eb Fm Ab Bb");
	}
}
