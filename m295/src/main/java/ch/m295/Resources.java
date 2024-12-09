package ch.m295;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/resources")
public class Resources extends Application {
    public Set<Class<?>> getClasses() {
        return new HashSet<Class<?>>(
          Arrays.asList(StringService.class, ArbeitgeberService.class, AuthentificationFilter.class));
    }
}
