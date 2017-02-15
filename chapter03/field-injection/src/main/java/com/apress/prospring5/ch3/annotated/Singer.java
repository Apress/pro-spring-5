package com.apress.prospring5.ch3.annotated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by iuliana.cosmina on 2/15/17.
 */
@Service("singer")
public class Singer {

	@Autowired
	private Inspiration inspirationBean;

	public void sing() {
		System.out.println("... " + inspirationBean.getLyric());
	}
}
