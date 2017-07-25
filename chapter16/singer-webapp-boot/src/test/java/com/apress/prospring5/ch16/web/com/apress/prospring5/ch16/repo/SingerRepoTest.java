package com.apress.prospring5.ch16.web.com.apress.prospring5.ch16.repo;

import com.apress.prospring5.ch16.entities.Singer;
import com.apress.prospring5.ch16.repos.SingerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

/**
 * Created by iuliana.cosmina on 7/23/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SingerRepoTest {

	private SingerRepository singerRepository;

	@Autowired
	public void setSingerRepository(SingerRepository singerRepository) {
		this.singerRepository = singerRepository;
	}

	@Test
	public void testSaveSinger(){
		Singer singer = new Singer();
		singer.setFirstName("Angus");
		singer.setLastName("Young");
		singer.setBirthDate(new Date(
				(new GregorianCalendar(1991, 2, 17)).getTime().getTime()));
		singerRepository.save(singer);

		//get all aingers, list should only have one
		Iterable<Singer> singers = singerRepository.findAll();
		int count = 0;

		for(Singer s : singers){
			count++;
		}
		assertEquals(count, 15);
	}
}
