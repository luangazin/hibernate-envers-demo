package br.edu.gazin.envers.book;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.edu.gazin.envers.author.Author;

@EntityListeners(AuditingEntityListener.class)
@Audited
@Entity
@Table(name = "\"Book\"", schema = "envers_schema")
public class Book implements Serializable {
	private static final long serialVersionUID = -8198205199378186112L;

	@Id
	@GeneratedValue
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id;

	@Column(name = "name", length = 60, nullable = true)
	private String name;

	@NotAudited
	@ManyToOne
	@JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
	private Author author;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

}
