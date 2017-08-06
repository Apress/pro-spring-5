package com.apress.prospring5.ch18.test;

import org.junit.experimental.ParallelComputer;
import org.junit.jupiter.api.Test;
import org.junit.runner.Computer;
import org.junit.runner.JUnitCore;
/**
 * Created by iuliana.cosmina on 8/6/17.
 */

public class ParallelTests {

	@Test
	void executeTwoInParallel() {
		final Class<?>[] classes = {
				IntegrationOneTest.class, IntegrationTwoTest.class
		};

		JUnitCore.runClasses(new ParallelComputer(true, true), classes);
	}

	@Test
	void executeTwoLinear() {
		final Class<?>[] classes = {
				IntegrationOneTest.class, IntegrationTwoTest.class
		};

		JUnitCore.runClasses(new Computer(), classes);
	}

}
