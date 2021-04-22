package com.hellyson.camel.Router;

import org.apache.camel.builder.RouteBuilder;

import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class RouterConfiguration extends RouteBuilder {

    @Autowired
    private Environment env;

    @Value("${camel.component.servlet.mapping.context-path}")
    private String contextPath;

    @Override
    public void configure() throws Exception {

        restConfiguration()
                .component("servlet")
                .bindingMode(RestBindingMode.json)
                .dataFormatProperty("prettyPrint", "true")
                .enableCORS(true)
                .port(env.getProperty("server.port", "8080"))
                .contextPath(contextPath.substring(0, contextPath.length() - 2))



                 .apiContextPath("/swagger") //swagger endpoint path
                .apiContextRouteId("swagger") //id of route providing the swagger endpoint

                //Swagger properties
                .contextPath("/api") //base.path swagger property; use the mapping path set for CamelServlet
                .apiProperty("api.title", "SWAGGER UI")
                .apiProperty("api.version", "1.0")
                .apiProperty("api.contact.name", "Hellyson Macedo")
                .apiProperty("api.contact.email", "hellysonjk2022@gmail.com")

                .apiProperty("host", "") //by default 0.0.0.0
                .apiProperty("port", "8080")
                .apiProperty("schemes", "");

       rest().get("/hello").produces(MediaType.APPLICATION_JSON_VALUE).route().setBody(constant("Iae"));

    }
}
