/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jea6.services;

import io.swagger.jaxrs.config.BeanConfig;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/webapi")
public class ApplicationConfig extends Application {  
    
    public ApplicationConfig() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.5.18");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/kwetter/webapi");
        beanConfig.setResourcePackage("rest");
        beanConfig.setPrettyPrint(true);
        //beanConfig.setScan(true);
    }
    
    
//    
//    @Override
//    public Set<Class<?>> getClasses() {
//        Set<Class<?>> resources = new HashSet();
//
//        //resources.add(FirstResource.class);
//        //resources.add(SecondResource.class);
//        //...
////        resources.add(StudentResource.class);
////        resources.add(StudentRestWsResponse.class);
//
//        resources.add(io.swagger.jaxrs.listing.ApiListingResource.class);
//        resources.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
//
//        return resources;
//    }
}
    
    
 