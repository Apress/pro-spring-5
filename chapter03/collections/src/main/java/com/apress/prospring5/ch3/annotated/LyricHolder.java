package com.apress.prospring5.ch3.annotated;

import com.apress.prospring5.ch3.ContentHolder;
import org.springframework.stereotype.Service;

/**
 * Created by iuliana.cosmina on 2/19/17.
 */
@Service("lyricHolder")
public class LyricHolder implements ContentHolder{

	private String value = "'You be the DJ, I'll be the driver'";

	@Override public String toString() {
		return "LyricHolder: { " + value + "}";
	}
}
