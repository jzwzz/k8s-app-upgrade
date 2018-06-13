package com.jzwzz.k8sdemo.k8sappupgrade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class K8sAppUpgradeApplication {

	public static void main(String[] args) {
		SpringApplication.run(K8sAppUpgradeApplication.class, args);
	}
}
