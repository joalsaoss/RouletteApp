package com.example.RouletteApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import com.example.RouletteCommon.RouletteCommonApplication;
import com.example.RouletteData.RouletteDataApplication;

@SpringBootApplication
@EntityScan(basePackageClasses = {RouletteDataApplication.class})
@ComponentScan(basePackages = {"com.example.RouletteData","com.example.RouletteCommon","com.example"})
public class RouletteApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RouletteApiApplication.class, args);
	}

}
