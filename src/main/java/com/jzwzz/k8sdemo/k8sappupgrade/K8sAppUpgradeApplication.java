package com.jzwzz.k8sdemo.k8sappupgrade;

import ch.sbb.esta.openshift.gracefullshutdown.GracefulshutdownSpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class K8sAppUpgradeApplication {

    public static void main(String[] args) {

//        SpringApplication.run(K8sAppUpgradeApplication.class, args);
        GracefulshutdownSpringApplication.run(K8sAppUpgradeApplication.class, args);
    }

}
