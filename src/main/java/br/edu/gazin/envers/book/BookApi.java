package br.edu.gazin.envers.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Strings;

import net.bytebuddy.utility.RandomString;

@RequestMapping("/v1")
@RestController
public class BookApi {

	@Autowired
	private BookRepository repository;

	@GetMapping(value = "/books", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<List<Book>> getBooks() {
		List<Book> list = repository.findAll();

		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@PostMapping(value = "/books", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<Book> postBooks() {

		Book book = new Book();
		book.setName(RandomString.make(10));
		Book saved = repository.save(book);

		return new ResponseEntity<>(saved, HttpStatus.OK);
	}
}
