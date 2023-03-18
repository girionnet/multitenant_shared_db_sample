
/*
 * In this example, the WebMvcConfig class implements the WebMvcConfigurer interface,
 *  which provides methods for configuring Spring MVC. The addInterceptors method 
 *  is used to add the MultiTenantInterceptor to the interceptor registry,
 *  with the list of allowed patterns.
 */
package com.giri.multitenant.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.giri.multitenant.interceptor.MultiTenantInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private List<String> allowedPatterns = Arrays.asList("/api/**");

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MultiTenantInterceptor(allowedPatterns));
    }
}
