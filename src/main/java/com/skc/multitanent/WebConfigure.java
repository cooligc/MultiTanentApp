/**
 * 
 */
package com.skc.multitanent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author chaudhsi
 *
 */
@Configuration
public class WebConfigure implements WebMvcConfigurer {

	@Autowired
	MultitanentInterceptor interceptor;
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		WebMvcConfigurer.super.addInterceptors(registry);
		registry.addInterceptor(interceptor);
	}
}
