package com.apress.prospring5.ch3.annotated;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by iuliana.cosmina on 2/20/17.
 */
@Component("singer")
@Scope("prototype")
public class Singer {
    private String lyric = "I played a quick game of chess with the salt and pepper shaker";

    public void sing() {
        // commented to avoid console pollution
        //System.out.println(lyric);
    }
}
