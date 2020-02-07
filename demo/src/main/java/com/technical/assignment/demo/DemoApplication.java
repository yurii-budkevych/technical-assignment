package com.technical.assignment.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	/*

input: X-Y and R
return all trees in that radius

	Example of the expected output:
	```json
	{
		"red maple": 30,
		"American linden": 1,
		"London planetree": 3
	}
	```

	The service should use the data from the 3rd party API (https://data.cityofnewyork.us/Environment/2015-Street-Tree-Census-Tree-Data/uvpi-gqnh): `https://data.cityofnewyork.us/resource/nwxe-4ae8.json`

	 */
}
