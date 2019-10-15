package br.edu.gazin.envers.revision.api;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.gazin.envers.author.Author;
import br.edu.gazin.envers.revision.repository.AuditHistory;
import br.edu.gazin.envers.revision.repository.AuditHistoryRepository;

@RequestMapping("/v1/history")
@RestController
public class AuditGenericApi {

	@Autowired
	private AuditHistoryRepository<Author> repository;

	@GetMapping(value = "/author/{book-id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<List<AuditHistory<Author>>> getBooks(@PathVariable("book-id") UUID id) {
		PageRequest request = PageRequest.of(0, 4);
		List<AuditHistory<Author>> list = repository.listEntityRevisions(id, request, Author.class);

		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
}
