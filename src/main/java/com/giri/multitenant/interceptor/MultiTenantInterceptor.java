/*
 * 
 * In this class interceptor code  retrieves the tenant ID from the request header 
 * and filters URLs based on a list of allowed patterns:
 * In this example, the MultiTenantInterceptor class implements the HandlerInterceptor interface,
 *  which provides methods that are called before and after the request is processed by the controller. 
 *  The preHandle method is called before the request is processed, and it retrieves the tenant ID from the request 
 *  header and sets it in a TenantContext object, which is a simple class
 *  that stores the current tenant ID in a ThreadLocal variable.
 * 
 * 
 */
package com.giri.multitenant.interceptor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import com.giri.multitenant.multitenant.TenantContext;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MultiTenantInterceptor implements HandlerInterceptor {

    private List<String> allowedPatterns;
    // Create an AntPathMatcher instance
    AntPathMatcher matcher = new AntPathMatcher();

    public MultiTenantInterceptor(List<String> allowedPatterns) {
        this.allowedPatterns = allowedPatterns;
    }

    public MultiTenantInterceptor() {
        //this.allowedPatterns = allowedPatterns;
    }
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
      
    	String requestURI = request.getRequestURI();
    	
    	   if (isMaintUrl(requestURI)) {
               return true; // Skip checking tenant ID for Actuator URLs
           }
    	   
    	String tenantId = request.getHeader("X-Tenant-ID");
        System.out.println("tenantId " + tenantId); 
        
        if (tenantId == null || tenantId.isEmpty()) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return false;
        }

        if (!isUrlAllowed(request.getRequestURI())) {
        	System.out.println("Forbidden..."); 
            response.setStatus(HttpStatus.FORBIDDEN.value());
            return false;
        }

        System.out.println("setting current TenantId..."); 
        TenantContext.setCurrentTenant(tenantId);
        return true;
    }
    
    


    
    private boolean isUrlAllowed(String url) {
        for (String pattern : allowedPatterns) {
            if (matcher.match(pattern, url)) {
            	 System.out.println("url matched...");             	
                 return true;
            }
        }
        System.out.println("url not matched...");
        System.out.println("url " + url); 
        return false;
    }
    
    private boolean isMaintUrl(String url) {
        return url.startsWith("/actuator/**");
    }
    
}

