package ch.m295;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

public class ApplicationInitializer implements WebApplicationInitializer {
 
    @Override
    public void onStartup(ServletContext servletContext) 
      throws ServletException {
 
        AnnotationConfigWebApplicationContext context 
          = new AnnotationConfigWebApplicationContext();
 
        servletContext.addListener(new ContextLoaderListener(context));
        servletContext.setInitParameter(
          "contextConfigLocation", "ch.m295");
    }
    
}