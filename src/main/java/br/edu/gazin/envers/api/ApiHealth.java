package br.edu.gazin.envers.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiHealth {

	@GetMapping(value = "/health", produces = MediaType.TEXT_PLAIN_VALUE)
	public String custom() {
		return "working";
	}
}
