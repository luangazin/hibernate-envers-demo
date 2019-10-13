package br.edu.gazin.envers.book;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.gazin.envers.author.Author;
import br.edu.gazin.envers.author.AuthorRepository;
import net.bytebuddy.utility.RandomString;

@RequestMapping("/v1")
@RestController
public class BookApi {

	@Autowired
	private BookRepository repository;

	@Autowired
	private AuthorRepository authorRepository;

	@GetMapping(value = "/books", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<List<Book>> getBooks() {
		List<Book> list = repository.findAll();

		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@PostMapping(value = "/books", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<Book> postBooks() {
		Optional<Author> optional = authorRepository.findAll().stream().findFirst();
		if (!optional.isPresent()) {
			Author author = new Author();
			author.setName("José da Silva");
			Author authorSaved = authorRepository.save(author);
			optional = Optional.<Author>ofNullable(authorSaved);
		}

		Book book = new Book();
		book.setName(RandomString.make(10));
		book.setAuthor(optional.get());
		Book saved = repository.save(book);

		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/books", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<Void> deleteBook() {
		Optional<Book> optional = repository.findAll().stream().findAny();
		if (optional.isPresent()) {
			repository.delete(optional.get());
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping(value = "/books/{book-id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<Book> putBooks(@PathVariable("book-id") UUID id) {
		 Optional<Book> optional = repository.findById(id);
		 if(!optional.isPresent()) 
			 new ResponseEntity<>(HttpStatus.NOT_FOUND);
		 Book book = optional.get();
		 book.setName("Altered - "+Integer.max(1, 9999999));
		 Book book2 = repository.save(book);
		 
		return new ResponseEntity<>(book2, HttpStatus.OK);
	}
}
