package com.giri.multitenant.multitenant;

public final class TenantContext {
    private TenantContext() {
    }

    private static final ThreadLocal<String> currentTenant =  new InheritableThreadLocal<>();


    public static String getCurrentTenant() {
    	 System.out.println("retrived  current TenantId is.." + currentTenant.get());
        return currentTenant.get();
    }

    public static void setCurrentTenant(String tenant) {
    	System.out.println("setting current TenantId is.." + tenant);
    	currentTenant.set(tenant);
     
    }

    public static void clear() {
    	currentTenant.remove();
    }
}