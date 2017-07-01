package com.nemanjam.ebook;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.nemanjam.ebook.interceptor.LogoutInterceptor;
import com.nemanjam.ebook.interceptor.SessionInterceptorAdmin;
import com.nemanjam.ebook.interceptor.SessionInterceptorSubscriber;

@Configuration
@EnableWebMvc
public class ApplicationWebMvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(new SessionInterceptorAdmin()).addPathPatterns(
				"/categoryupdate", "/categorymanage", "/categoryadd", "/categorydelete",
				"/usermanage", "/userupdate", "/useradd", "/userdelete",
				"/bookmanage", "/bookmanagecategory", "/bookupdate", "/bookupload", "/bookadd", "/bookdelete");

		registry.addInterceptor(new SessionInterceptorSubscriber()).addPathPatterns(
				"/bookdownload", "/logout", "/account", "/accountinfo", "/accountpassword");
		
		registry.addInterceptor(new LogoutInterceptor()).addPathPatterns("/logout");
		
	}
	  
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/resources/public/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
    
}