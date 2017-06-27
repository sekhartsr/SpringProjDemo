//package com.rbtsb.config;
//
//import org.kie.server.api.marshalling.MarshallingFormat;
//import org.kie.server.client.KieServicesConfiguration;
//import org.kie.server.client.KieServicesFactory;
//import org.kie.server.client.RuleServicesClient;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * Created by kent on 30/03/2016.
// */
//@Configuration
//public class KieConfig {
//
//    @Value("${bpms.url}")
//    private String url;
//    @Value("${bpms.username}")
//    private String username;
//    @Value("${bpms.password}")
//    private String password;
//
//    @Bean
//    public RuleServicesClient ruleServicesClient() {
//        KieServicesConfiguration config = KieServicesFactory.newRestConfiguration(url, username, password);
//        config.setMarshallingFormat(MarshallingFormat.XSTREAM);
//        return KieServicesFactory.newKieServicesClient(config).getServicesClient(RuleServicesClient.class);
//    }
//
////    @Bean
////    public KieServicesClient kieServicesClient() {
////        KieServicesConfiguration config = KieServicesFactory.newRestConfiguration(url, username, password);
////        config.setMarshallingFormat(MarshallingFormat.XSTREAM);
////        return KieServicesFactory.newKieServicesClient(config);
////    }
//
//}
