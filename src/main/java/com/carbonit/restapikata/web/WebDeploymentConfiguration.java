package com.carbonit.restapikata.web;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebDeploymentConfiguration {

    private Connector redirectConnector(int httpPort, int httpsPort) {
        final var connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
        connector.setPort(httpPort);
        connector.setScheme("http");
        connector.setRedirectPort(httpsPort);
        return connector;
    }


    @Bean
    TomcatServletWebServerFactory webServerFactory(ServerProperties serverProperties, @Value("${server.http.port}") int httpPort) {
        final var factory = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                final var securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                final var collection = new SecurityCollection();
                collection.addPattern("/*");

                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };
        factory.addAdditionalTomcatConnectors(redirectConnector(httpPort, serverProperties.getPort()));
        return factory;
    }
}
