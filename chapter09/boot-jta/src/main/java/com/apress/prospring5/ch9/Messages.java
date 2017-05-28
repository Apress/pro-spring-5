package com.apress.prospring5.ch9;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by iuliana.cosmina on 5/28/17.
 */
@Component
public class Messages {
	private static Logger logger = LoggerFactory.getLogger(Messages.class);

	@JmsListener(destination="singers")
	public void onMessage(String content){
		logger.info("--> Received content: " + content);
	}
}
