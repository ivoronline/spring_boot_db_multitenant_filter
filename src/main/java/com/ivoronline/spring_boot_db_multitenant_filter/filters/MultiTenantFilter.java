package com.ivoronline.spring_boot_db_multitenant_filter.filters;

import com.ivoronline.spring_boot_db_multitenant_filter.config.TenantIdentifierResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerMapping;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

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

        Map<String, String> pathParameters = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

        //SET TENANT
        String schema = request.getParameter("schema");
        tenantIdentifierResolver.setCurrentTenant(schema);
        chain.doFilter(request, response);

    }

}
