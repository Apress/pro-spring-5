package com.apress.prospring5.ch4.config;

import com.apress.prospring5.ch4.Food;
import com.apress.prospring5.ch4.FoodProviderService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

/**
 * Created by iuliana.cosmina on 3/18/17.
 */
public class ProfileJavaConfigExample {

	public static void main(String... args) {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(
				KindergartenConfig.class,
				HighschoolConfig.class);

		FoodProviderService foodProviderService =
				ctx.getBean("foodProviderService", FoodProviderService.class);

		List<Food> lunchSet = foodProviderService.provideLunchSet();
		for (Food food : lunchSet) {
			System.out.println("Food: " + food.getName());
		}
		ctx.close();
	}
}
