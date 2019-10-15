package br.edu.gazin.envers.revision.repository;

import org.hibernate.envers.RevisionType;

public class AuditHistory<T> {
	private final T entity;
	private final Number revision;
	private final RevisionType revisionType;
	private final String username;

	public AuditHistory(T entity, Number revision, RevisionType revisionType, String username) {
		this.entity = entity;
		this.revision = revision;
		this.revisionType = revisionType;
		this.username = username;
	}

	public T getEntity() {
		return entity;
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
