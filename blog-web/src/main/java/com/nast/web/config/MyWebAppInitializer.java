package com.nast.web.config;

import com.nast.web.config.security.MyWebSecurityConfiguration;
import org.h2.server.web.WebServlet;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    public void onStartup(ServletContext container) {
        XmlWebApplicationContext rootContext = new XmlWebApplicationContext();
        rootContext.setConfigLocation("/WEB-INF/config/root-context.xml");
        // Manage the lifecycle of the root application context
        container.addListener(new ContextLoaderListener(rootContext));

        DispatcherServlet dispatcherServlet = new DispatcherServlet(rootContext);
        // throw NoHandlerFoundException to controller ExceptionHandler.class. Used for <error-page> analogue
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);

        ServletRegistration.Dynamic dispatcher =
                container.addServlet("dispatcher", dispatcherServlet);
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/main/*");

        registerH2(container);

        FilterRegistration.Dynamic encodingFilter = container
                .addFilter("encoding-filter", new CharacterEncodingFilter());
        encodingFilter.setInitParameter("encoding", "UTF-8");
        encodingFilter.setInitParameter("forceEncoding", "true");
        encodingFilter.addMappingForUrlPatterns(null, true, "/*");
    }

    private void registerH2(ServletContext container) {
        org.h2.server.web.WebServlet h2Servlet = new WebServlet();
        ServletRegistration.Dynamic h2 =
                container.addServlet("h2Console", h2Servlet);
        h2.setLoadOnStartup(1);
        h2.addMapping("/console/*");
    }

    @Override
    protected String[] getServletMappings() {
        return new String[0];
    }

    /**
     * added to load spring security filter in root context (created in onStartup())
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{MyWebSecurityConfiguration.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }
}
