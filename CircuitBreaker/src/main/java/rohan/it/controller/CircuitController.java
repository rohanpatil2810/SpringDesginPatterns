package rohan.it.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class CircuitController {

	@GetMapping("/getMsg")
	@CircuitBreaker(fallbackMethod = "getDb", name = "CircuitBreaker")
	public String getRedis() {
		System.out.println("Redis");
		int i=10/0;
		return "Redis";
	}
	
	public String getDb(Throwable t) {
		System.out.println("DB");
		return "DB";
	}
}
