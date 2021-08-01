package com.example.demo2;

import org.apache.camel.CamelContext;
import org.apache.camel.Component;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.servlet.ServletComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.rest.RestBindingMode;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
public class Camel {

    @PostConstruct
    public void initi(){
        CamelContext context = new DefaultCamelContext();
       /* Component stream = new StreamComponent();
        context.addComponent("stream",stream);
        */
        Component servlet = new ServletComponent();
        context.addComponent("servlet",servlet);

        try {

            context.addRoutes(new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    /*TESTE APACHE CAMEL

                    from("timer://foo?delay=2000")
                            .setBody()
                            .simple("This is a test message")
                            .to("stream:out");
                    */

                    restConfiguration()
                            .component("servlet")
                            .bindingMode(RestBindingMode.json);

                    rest().get("/hi").produces("application/json").route().setBody(constant("Hello World!"));

                }
            });

            context.start();

        }catch (Exception e){
          e.printStackTrace();
        }



    }

}