package com.apress.prospring5.ch8;

import com.apress.prospring5.ch8.config.JpaConfig;
import com.apress.prospring5.ch8.service.SingerSummaryService;
import com.apress.prospring5.ch8.service.SingerSummaryUntypeImpl;
import com.apress.prospring5.ch8.view.SingerSummary;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by iuliana.cosmina on 4/29/17.
 */
public class SingerSummaryJPATest {

	private static Logger logger = LoggerFactory.getLogger(SingerSummaryJPATest.class);
	private GenericApplicationContext ctx;
	private SingerSummaryService singerSummaryService;
	private SingerSummaryUntypeImpl singerSummaryUntype;

	@Before
	public void setUp() {
		ctx = new AnnotationConfigApplicationContext(JpaConfig.class);
		singerSummaryService = ctx.getBean(SingerSummaryService.class);
		singerSummaryUntype = ctx.getBean(SingerSummaryUntypeImpl.class);
		assertNotNull(singerSummaryService);
		assertNotNull(singerSummaryUntype);
	}

	@Test
	public void testFindAll() {
		List<SingerSummary> singers = singerSummaryService.findAll();
		listSingerSummary(singers);
		assertEquals(2, singers.size());
	}

	@Test
	public void testFindAllUntype() {
		singerSummaryUntype.displayAllSingerSummary();
	}

	private static void listSingerSummary(List<SingerSummary> singers) {
		logger.info(" ---- Listing singers summary:");
		for (SingerSummary singer : singers) {
			logger.info(singer.toString());
		}
	}

	@After
	public void tearDown() {
		ctx.close();
	}
}
