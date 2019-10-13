package br.edu.gazin.envers.revision;

import org.hibernate.envers.RevisionType;

public class AuditQueryResult<T> {

    private final T entity;
    private final RevisionInfo revision;
    private final RevisionType type;

    public AuditQueryResult(T entity, RevisionInfo revision, RevisionType type) {
        this.entity = entity;
        this.revision = revision;
        this.type = type;
    }

    public T getEntity() {
        return entity;
    }

    public RevisionInfo getRevision() {
        return revision;
    }

    public RevisionType getType() {
        return type;
    }

}
