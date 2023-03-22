package com.giri.multitenant.multitenant;

import org.hibernate.cfg.AvailableSettings;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
/*
  tenant ID is to be set when storing an entity and added to where clauses when loading an entity, we need something that provides a value for it. For this purpose,
 Hibernate requires a CurrentTenantIdentifierResolver to be implemented.
 */
@Component
public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver, HibernatePropertiesCustomizer {
    private static final String UNKNOWN = "unknown";

    @Override
    public String resolveCurrentTenantIdentifier() {
        System.out.println("TenantIdentifierResolver:resolveCurrentTenantIdentifier(): " + TenantContext.getCurrentTenant());
        return Optional.ofNullable(TenantContext.getCurrentTenant()).orElse(UNKNOWN);

    }

     @Override
    public boolean validateExistingCurrentSessions() {

       System.out.println("TenantIdentifierResolver:validateExistingCurrentSessions(): ");
        return true;
    }

    @Override
    public void customize(Map<String, Object> hibernateProperties) {
        System.out.println("TenantIdentifierResolver:customize(): ");
        hibernateProperties.put(AvailableSettings.MULTI_TENANT_IDENTIFIER_RESOLVER, this);
    }
}

/*
The MULTI_TENANT_IDENTIFIER_RESOLVER property is used to specify a tenant identifier resolver
for Hibernate's multi-tenancy feature. In a multi-tenant application, this resolver is
responsible for determining the current tenant identifier for each database interaction,
based on a configured strategy. By setting this property to this, we are specifying that
the current class instance should act as the tenant identifier resolver.

It only has a simple value for the currentTenant. In a real application,
you would either use a different scope (like request, for example) or
 get the value from some other bean that is appropriately scoped.

It implements HibernatePropertiesCustomizer to register itself with Hibernate.
In my opinion, this should not be necessary.
You can follow this Hibernate issue to see if the Hibernate team agrees.
 */