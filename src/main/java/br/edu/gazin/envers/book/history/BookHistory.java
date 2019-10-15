package br.edu.gazin.envers.book.history;

import org.hibernate.envers.RevisionType;

import br.edu.gazin.envers.book.Book;

public class BookHistory {
	private final Book book;
	private final Number revision;
	private final RevisionType revisionType;
	private final String username;

	public BookHistory(Book book, Number revision, RevisionType revisionType, String username) {
		this.book = book;
		this.revision = revision;
		this.revisionType = revisionType;
		this.username = username;
	}

	public Book getBook() {
		return book;
	}

	public Number getRevision() {
		return revision;
	}

	public RevisionType getRevisionType() {
		return revisionType;
	}

	public String getUsername() {
		return username;
	}
}
