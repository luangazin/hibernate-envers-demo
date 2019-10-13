package br.edu.gazin.envers.book.history;

import java.util.List;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v1/history")
@RestController
public class BookHistoryApi {
	
	@Autowired
	private BookHistoryRepository repository;

	@GetMapping(value = "/books/{book-id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<List<BookHistory>> getBooks(@PathVariable("book-id") UUID id) {
		List<BookHistory> list = repository.listBookRevisions(id);
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
}
