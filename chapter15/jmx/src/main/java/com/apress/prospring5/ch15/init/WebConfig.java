package com.apress.prospring5.ch15.init;

import com.apress.prospring5.ch15.AppStatistics;
import com.apress.prospring5.ch15.AppStatisticsImpl;
import com.apress.prospring5.ch15.jmx.CustomStatistics;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.oxm.castor.CastorMarshaller;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.persistence.EntityManagerFactory;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by iuliana.cosmina on 6/5/17.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.apress.prospring5.ch15"})
public class WebConfig implements WebMvcConfigurer {

	@Autowired ApplicationContext ctx;

	/**
	 * Setting the MappingJackson2HttpMessageConverter and configuring it
	 *
	 * @return
	 */
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
		mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper());
		return mappingJackson2HttpMessageConverter;
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objMapper = new ObjectMapper();
		objMapper.enable(SerializationFeature.INDENT_OUTPUT);
		objMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		objMapper.setDateFormat(df);
		return objMapper;
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(mappingJackson2HttpMessageConverter());
		converters.add(singerMessageConverter());
	}

	@Bean MarshallingHttpMessageConverter singerMessageConverter() {
		MarshallingHttpMessageConverter mc = new MarshallingHttpMessageConverter();
		mc.setMarshaller(castorMarshaller());
		mc.setUnmarshaller(castorMarshaller());
		List<MediaType> mediaTypes = new ArrayList<>();
		MediaType mt = new MediaType("application", "xml");
		mediaTypes.add(mt);
		mc.setSupportedMediaTypes(mediaTypes);
		return mc;
	}

	@Bean CastorMarshaller castorMarshaller() {
		CastorMarshaller castorMarshaller = new CastorMarshaller();
		castorMarshaller.setMappingLocation(ctx.getResource("classpath:spring/oxm-mapping.xml"));
		return castorMarshaller;
	}
	// JMX beans

	@Bean AppStatistics appStatisticsBean() {
		return new AppStatisticsImpl();
	}

	@Bean CustomStatistics statisticsBean() {
		return new CustomStatistics();
	}

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Bean SessionFactory sessionFactory() {
		return entityManagerFactory.unwrap(SessionFactory.class);
	}

	@Bean
	MBeanExporter jmxExporter() {
		MBeanExporter exporter = new MBeanExporter();
		Map<String, Object> beans = new HashMap<>();
		beans.put("bean:name=ProSpring5SingerApp", appStatisticsBean());
		beans.put("bean:name=ProSpring5SingerApp-hibernate", statisticsBean());
		exporter.setBeans(beans);
		return exporter;
	}
}
