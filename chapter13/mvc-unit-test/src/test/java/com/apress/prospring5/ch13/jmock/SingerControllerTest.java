package com.apress.prospring5.ch13.jmock;

import com.apress.prospring5.ch13.SingerController;
import com.apress.prospring5.ch13.entities.Singer;
import com.apress.prospring5.ch13.entities.Singers;
import com.apress.prospring5.ch13.services.SingerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ExtendedModelMap;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import org.jmock.Expectations;
import org.jmock.Mockery;

public class SingerControllerTest {
    private final List<Singer> singers = new ArrayList<>();

    private Mockery mockery = new Mockery();

    private SingerService mockSingerService;
    private SingerController singerController;

    @Before
    public void initSingers() {
        Singer singer = new Singer();
        singer.setId(1l);
        singer.setFirstName("John");
        singer.setLastName("Mayer");
        singers.add(singer);
        mockSingerService = mockery.mock(SingerService.class);
        singerController = new SingerController();
        ReflectionTestUtils.setField(singerController, "singerService", mockSingerService);
    }

    @Test
    public void testList() throws Exception {
        mockery.checking(new Expectations() {{
            oneOf(mockSingerService).findAll();
            will(returnValue(singers));
        }});
        ExtendedModelMap uiModel = new ExtendedModelMap();

        uiModel.addAttribute("singers", singerController.listData());
        Singers modelSingers = (Singers) uiModel.get("singers");
        assertEquals(1, modelSingers.getSingers().size());
        mockery.assertIsSatisfied();
    }

    @Test
    public void testCreate() {
        final Singer newSinger = new Singer();
        newSinger.setId(999l);
        newSinger.setFirstName("Stevie");
        newSinger.setLastName("Vaughan");

        mockery.checking(new Expectations() {{
            oneOf(mockSingerService).save(newSinger);
            singers.add(newSinger);
            will(returnValue(newSinger));
        }});

        Singer singer = singerController.create(newSinger);
        assertEquals(Long.valueOf(999l), singer.getId());
        assertEquals("Stevie", singer.getFirstName());
        assertEquals("Vaughan", singer.getLastName());

        assertEquals(2, singers.size());
        mockery.assertIsSatisfied();
    }
}
