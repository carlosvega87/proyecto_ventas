package com.distribuida;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.distribuida") // fuerza a Spring a escanear todos los paquetes

public class VentasSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(VentasSpringApplication.class, args);
    }
}
