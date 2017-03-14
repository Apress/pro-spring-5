package com.apress.prospring5.ch4.annotated;
/**
 * Created by iuliana.cosmina on 3/15/17.
 */
import com.apress.prospring5.ch2.decoupled.MessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("provider")
public class ConfigurableMessageProvider implements MessageProvider {

	private String message;

	public ConfigurableMessageProvider(	@Value("Love on the weekend")String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}
}
