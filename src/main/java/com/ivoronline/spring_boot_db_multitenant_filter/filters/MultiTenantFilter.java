package com.ivoronline.spring_boot_db_multitenant_filter.filters;

import com.ivoronline.spring_boot_db_multitenant_filter.config.TenantIdentifierResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Order(1)
@Component
public class MultiTenantFilter extends OncePerRequestFilter {

    //PROPERTIES
    @Autowired TenantIdentifierResolver tenantIdentifierResolver;

    //=========================================================================================================
    // DO FILTER INTERNAL
    //=========================================================================================================
    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        //SET TENANT
        String schema = request.getParameter("schema");
        tenantIdentifierResolver.setCurrentTenant(schema);
        chain.doFilter(request, response);

    }

}
