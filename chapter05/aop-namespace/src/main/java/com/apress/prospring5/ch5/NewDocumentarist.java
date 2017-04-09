package com.apress.prospring5.ch5;

import com.apress.prospring5.ch2.common.Guitar;

/**
 * Created by iuliana.cosmina on 4/9/17.
 */
public class NewDocumentarist extends Documentarist {

	@Override
	public void execute() {
		guitarist.sing();
		Guitar guitar = new Guitar();
		guitar.setBrand("Gibson");
		guitarist.sing(guitar);
		//guitarist.sing(new Guitar());
		guitarist.talk();
	}

}
