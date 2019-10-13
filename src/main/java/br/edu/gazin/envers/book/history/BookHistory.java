package br.edu.gazin.envers.book.history;

import org.hibernate.envers.RevisionType;

import br.edu.gazin.envers.book.Book;

public class BookHistory {
	private final Book book;
	private final Number revision;
	private final RevisionType revisionType;

	public BookHistory(Book book, Number revision, RevisionType revisionType) {
		this.book = book;
		this.revision = revision;
		this.revisionType = revisionType;
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
}
